package christmas.controller;

import christmas.domain.Date;
import christmas.domain.Menu;
import christmas.domain.Payment;
import christmas.util.Validator;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.EnumMap;
import java.util.Map;

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

        Payment payment = new Payment(menu);
        Date date = new Date(reservationDate);
        outputView.printOriginPayment(payment.getPayment());
        int priceOfGift = payment.presentChampagne();
        outputView.printPresentChampagne(priceOfGift);
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
