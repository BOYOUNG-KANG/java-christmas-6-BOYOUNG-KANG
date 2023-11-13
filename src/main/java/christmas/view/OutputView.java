package christmas.view;

import christmas.domain.Menu;
import christmas.domain.Results;
import christmas.dto.ResultsResponse;
import christmas.message.EventMessage;
import java.text.DecimalFormat;
import java.util.EnumMap;
import java.util.Map;

public class OutputView {
    private static final String NOTHING = "없음";

    public void printStart(){
        System.out.println(EventMessage.START_MESSAGE);
    }

    public void printEvent(int date){
        System.out.printf(EventMessage.EVENT_MESSAGE, date);
    }
    public void printOrder(EnumMap<Menu, Integer> menu){
        System.out.println("\n\n" + EventMessage.MENU);
        for (Map.Entry<Menu, Integer> entry : menu.entrySet()) {
            System.out.println(entry.getKey().getName() + " " + entry.getValue() + "개");
        }
    }
    public void printResult(ResultsResponse response){
        printOriginPayment(response.payment());
        printPresent(response.results().get(Results.PRESENT));
        printBenefits(response);
        printDiscountPrice(response);
        printExpectedPayment(response);
    }

    private static void printExpectedPayment(ResultsResponse response) {
        System.out.println("\n" + EventMessage.EXPECTED_PAYMENT);
        System.out.println(formatPrice(response.payment() + response.totalDiscount()) + "원");
    }

    private static void printDiscountPrice(ResultsResponse response) {
        System.out.println("\n" + EventMessage.BENEFIT_PRIZE);
        System.out.println(formatPrice(response.totalDiscount() + response.results().get(Results.PRESENT)) + "원");
    }

    private static void printBenefits(ResultsResponse response) {
        System.out.println("\n" + EventMessage.BENEFIT);
        for (Results result : Results.values()) {
            printBenefit(response.results().get(result), result.getName());
        }
        if (response.totalDiscount() == 0) {
            System.out.println(NOTHING);
        }
    }

    private static void printBenefit(int discount, String message) {
        if (discount != 0) {
            System.out.print(message + ": " + formatPrice(discount) + "원\n");
        }
    }

    private static void printPresent(int present) {
        System.out.println("\n" + EventMessage.PRESENT);
        if (present == 0) {
            System.out.println(NOTHING);
        }
        if (-present == Menu.CHAMPAGNE.getPrice()) {
            System.out.println("샴페인 1개");
        }
    }

    private void printOriginPayment(int payment) {
        System.out.println("\n" + EventMessage.PAYMENT);
        System.out.println(formatPrice(payment) + "원");
    }

    private static String formatPrice(int price) {
        return new DecimalFormat("###,###").format(price);
    }
}