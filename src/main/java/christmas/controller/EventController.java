package christmas.controller;

import christmas.domain.Menu;
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

    public void start(){
        outputView.printStart();
        int date = getDate();
        EnumMap<Menu, Integer> menu = getMenu();
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
