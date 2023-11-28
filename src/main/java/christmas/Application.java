package christmas;

import christmas.controller.EventController;
import christmas.util.Validator;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        EventController eventController = new EventController(
                new InputView(), new OutputView(), new Validator());
        eventController.progress();
    }
}
