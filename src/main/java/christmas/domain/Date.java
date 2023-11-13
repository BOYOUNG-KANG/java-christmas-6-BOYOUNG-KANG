package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;

public class Date {
    private static final int DISCOUNT_PRICE = 2023;
    private static final int CHRISTMAS_DATE = 25;
    private LocalDate date;

    public Date(int date) {
        this.date = LocalDate.of(2023, 12, date);
    }
    public void discount(EnumMap<Menu, Integer> orderInfo, EnumMap<Results, Integer> results){
        dayOfWeekDiscount(orderInfo, results);
        specialDiscount(results);
        christmasDiscount(results);
    }
    private void dayOfWeekDiscount(EnumMap<Menu, Integer> orderInfo, EnumMap<Results, Integer> results){
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (dayOfWeek.equals(DayOfWeek.FRIDAY) || dayOfWeek.equals(DayOfWeek.SATURDAY)){
            int discount = countBenefittedMenus(orderInfo, Category.MAIN) * DISCOUNT_PRICE;
            results.put(Results.WEEKEND_DISCOUNT, -discount);
        }
        if (!dayOfWeek.equals(DayOfWeek.FRIDAY) && !dayOfWeek.equals(DayOfWeek.SATURDAY)){
            int discount = countBenefittedMenus(orderInfo, Category.DESSERT) * DISCOUNT_PRICE;
            results.put(Results.WEEKDAY_DISCOUNT, -discount);
        }
    }
    private int countBenefittedMenus(EnumMap<Menu, Integer> orderInfo, Category category){
        return orderInfo.entrySet().stream()
                .filter(entry -> entry.getKey().getCategory() == category)
                .mapToInt(Map.Entry::getValue)
                .sum();
    }
    private void specialDiscount(EnumMap<Results, Integer> results){
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (dayOfWeek.equals(DayOfWeek.SUNDAY) || date.getDayOfMonth() == CHRISTMAS_DATE){
            results.put(Results.SPECIAL_DISCOUNT, -1000);
        }
    }
    private void christmasDiscount(EnumMap<Results, Integer> results){
        int dayOfMonth = date.getDayOfMonth();
        if (dayOfMonth <= CHRISTMAS_DATE) {
            int discount = 1000 + 100 * (dayOfMonth - 1);
            results.put(Results.CHRISTMAS_DISCOUNT, -discount);
        }
    }
}