package christmas.domain;

import java.util.EnumMap;
import java.util.Map;

public class Payment {
    private int payment;

    public Payment(EnumMap<Menu, Integer> menuInfo) {
        this.payment = getPayment(menuInfo);
    }
    private static int getPayment(EnumMap<Menu, Integer> menuInfo) {
        int payment = 0;
        for (Map.Entry<Menu, Integer> menu : menuInfo.entrySet()) {
            payment += menu.getKey().getPrice() * menu.getValue();
        }
        return payment;
    }
    public int presentChampagne() {
        int discount = 0;
        if (payment >= 120000) {
            discount += Menu.CHAMPAGNE.getPrice();
        }
        return discount;
    }

    public int getPayment() {
        return payment;
    }
}