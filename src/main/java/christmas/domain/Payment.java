package christmas.domain;

import java.util.EnumMap;

public class Payment {
    private int payment;

    public Payment(EnumMap<Menu, Integer> menuInfo) {
        this.payment = getPayment(menuInfo);
    }
    private static int getPayment(EnumMap<Menu, Integer> menuInfo) {
        return menuInfo.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }
    public void present(EnumMap<Results, Integer> results){
        presentChampagne(results);
    }
    private void presentChampagne(EnumMap<Results, Integer> results) {
        if (payment >= 120000) {
            results.put(Results.PRESENT, -Menu.CHAMPAGNE.getPrice());
        }
    }

    public int getPayment() {
        return payment;
    }
}