package christmas.message;

public enum ValidateErrorMessage {
    DATE_ERROR("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    MENU_ERROR("유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private final String message;

    ValidateErrorMessage(String message) {
        this.message = "[ERROR] " + message;
    }
    public String getMessage() {
        return message;
    }
}
