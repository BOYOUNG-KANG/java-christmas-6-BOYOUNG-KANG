package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.message.EventMessage;
import christmas.message.ValidateErrorMessage;

public class InputView {

    public int getDate(){
        try {
            System.out.println(EventMessage.DATE_INPUT_MESSAGE);
            return Integer.parseInt(Console.readLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ValidateErrorMessage.DATE_ERROR.getMessage());
        }
    }

}
