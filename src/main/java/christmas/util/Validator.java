package christmas.util;

import christmas.message.ValidateErrorMessage;

public class Validator {
    private static final int FIRST_DAY = 1;
    private static final int LAST_DAY = 31;
    public int validate(int date){
        if (FIRST_DAY > date || date > LAST_DAY) {
            throw new IllegalArgumentException(ValidateErrorMessage.DATE_ERROR.getMessage());
        }
        return date;
    }
}
