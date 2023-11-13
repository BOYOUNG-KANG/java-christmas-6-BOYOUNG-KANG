package christmas.controller;

import christmas.domain.Date;
import christmas.domain.Menu;
import christmas.domain.Payment;
import christmas.domain.Results;
import christmas.dto.ResultsResponse;
import christmas.util.Validator;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.EnumMap;

public class EventController {
    InputView inputView;
    OutputView outputView;
    Validator validator;

    public EventController(InputView inputView, OutputView outputView, Validator validator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.validator = validator;
    }

    public void progress(){
        outputView.printStart();
        int reservationDate = getDate();
        EnumMap<Menu, Integer> menu = getMenu();

        outputView.printEvent(reservationDate);
        outputView.printOrder(menu);

        EnumMap<Results, Integer> results = setUpResults();

        Payment payment = new Payment(menu);
        Date date = new Date(reservationDate);
        date.discount(menu, results);
        payment.present(results);

        outputView.printResult(new ResultsResponse(payment.getPayment(), results));
    }

    private static EnumMap<Results, Integer> setUpResults() {
        EnumMap<Results, Integer> results = new EnumMap<>(Results.class);
        results.put(Results.CHRISTMAS_DISCOUNT, 0);
        results.put(Results.WEEKDAY_DISCOUNT, 0);
        results.put(Results.WEEKEND_DISCOUNT, 0);
        results.put(Results.SPECIAL_DISCOUNT, 0);
        results.put(Results.PRESENT, 0);
        return results;
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
