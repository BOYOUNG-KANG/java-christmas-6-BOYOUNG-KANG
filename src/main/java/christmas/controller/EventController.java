package christmas.controller;

import christmas.domain.Badge;
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

        EnumMap<Results, Integer> results = setUpResults();
        Payment payment = new Payment(menu);
        new Date(reservationDate).discount(menu, results);
        payment.present(results);
        printResults(reservationDate, menu, results, payment);
    }

    private void printResults(int reservationDate, EnumMap<Menu, Integer> menu,
            EnumMap<Results, Integer> results, Payment payment) {
        outputView.printEvent(reservationDate);
        outputView.printOrder(menu);
        outputView.printResults(
                new ResultsResponse(payment.getPayment(), results,
                        Badge.get(getTotalDiscount(results)))
        );
    }

    private static int getTotalDiscount(EnumMap<Results, Integer> results) {
        return results.get(Results.CHRISTMAS_DISCOUNT)
                + results.get(Results.WEEKDAY_DISCOUNT)
                + results.get(Results.WEEKEND_DISCOUNT)
                + results.get(Results.SPECIAL_DISCOUNT)
                + results.get(Results.PRESENT);
    }

    private static EnumMap<Results, Integer> setUpResults() {
        EnumMap<Results, Integer> results = new EnumMap<>(Results.class);
        for (Results result : Results.values()) {
            results.put(result, 0);
        }
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