package christmas.controller;

import christmas.domain.Badge;
import christmas.domain.EventCalculator;
import christmas.domain.Menu;
import christmas.domain.Event;
import christmas.dto.EventResultsDto;
import christmas.util.Validator;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.EnumMap;

public class EventController {
    InputView inputView;
    OutputView outputView;
    Validator validator;

    public EventController(InputView inputView,
            OutputView outputView,
            Validator validator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.validator = validator;
    }

    public void progress(){
        outputView.printStart();
        int date = getDate();
        EnumMap<Menu, Integer> menu = getMenu();

        EventCalculator eventCalculator = new EventCalculator(date, menu);
        EventResultsDto eventResultsDto = eventCalculator.progress();

        Badge badge = Badge.get(-getTotalDiscount(eventResultsDto.eventResults()));

        printResults(date, menu, eventCalculator.getPayment(), eventResultsDto, badge);
    }

    private void printResults(int date, EnumMap<Menu, Integer> menu, int payment,
            EventResultsDto eventResultsDto, Badge badge) {
        outputView.printEvent(date);
        outputView.printOrder(menu);
        outputView.printResults(payment, badge, eventResultsDto);
    }

    private static int getTotalDiscount(EnumMap<Event, Integer> results) {
        return results.get(Event.CHRISTMAS_DISCOUNT)
                + results.get(Event.WEEKDAY_DISCOUNT)
                + results.get(Event.WEEKEND_DISCOUNT)
                + results.get(Event.SPECIAL_DISCOUNT)
                + results.get(Event.PRESENT);
    }


    private int getDate(){
        try {
            return validator.validate(inputView.getDate());
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return getDate();
        }
    }
    private EnumMap<Menu, Integer> getMenu(){
        try {
            return validator.validate(inputView.getMenu());
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return getMenu();
        }
    }
}