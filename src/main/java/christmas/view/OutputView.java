package christmas.view;

import christmas.domain.Badge;
import christmas.domain.Menu;
import christmas.domain.Event;
import christmas.dto.EventResultsDto;
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
    public void printResults(int payment, Badge badge, EventResultsDto eventResultsDto){
        printResult(EventMessage.PAYMENT, formatPrice(payment) + "원");
        printPresent(eventResultsDto.eventResults().get(Event.PRESENT));
        printBenefits(eventResultsDto);
        printResult(EventMessage.BENEFIT_PRIZE,
                formatPrice(eventResultsDto.totalDiscount()) + "원");
        printResult(EventMessage.EXPECTED_PAYMENT,
                formatPrice(payment + eventResultsDto.totalDiscount() - eventResultsDto.eventResults().get(Event.PRESENT)) + "원");
        printResult(EventMessage.BADGE, badge.getName());
    }

    private static void printResult(String message, String result) {
        System.out.println("\n" + message);
        System.out.println(result);
    }

    private static void printBenefits(EventResultsDto eventResultsDto) {
        System.out.println("\n" + EventMessage.BENEFIT);
        for (Event result : Event.values()) {
            printBenefit(eventResultsDto.eventResults().get(result), result.getName());
        }
        if (eventResultsDto.totalDiscount() == 0) {
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

    private static String formatPrice(int price) {
        return new DecimalFormat("###,###").format(price);
    }
}