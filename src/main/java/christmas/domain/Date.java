package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.EnumMap;

public class Date {
    private static final int DISCOUNT_PRICE = 2023;
    private static final int CHRISTMAS_DATE = 25;
    private LocalDate date;

    public Date(int date) {
        this.date = LocalDate.of(2023, 12, date);
    }
    public int discount(int discount, EnumMap<Category, Integer> orderInfo){
        return dayOfWeekDiscount(discount, orderInfo);
    }
    private int dayOfWeekDiscount(int discount, EnumMap<Category, Integer> orderInfo){
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (dayOfWeek.equals(DayOfWeek.FRIDAY) || dayOfWeek.equals(DayOfWeek.SATURDAY)){
            discount += orderInfo.get(Category.MAIN) * DISCOUNT_PRICE;
        }
        if (!dayOfWeek.equals(DayOfWeek.FRIDAY) && !dayOfWeek.equals(DayOfWeek.SATURDAY)){
            discount += orderInfo.get(Category.DESSERT) * DISCOUNT_PRICE;
        }
        return specialDiscount(discount);
    }
    private int specialDiscount(int discount){
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (dayOfWeek.equals(DayOfWeek.SUNDAY) || date.getDayOfMonth() == CHRISTMAS_DATE){
            discount += 1000;
        }
        return christmasDiscount(discount);
    }
    private int christmasDiscount(int discount){
        int dayOfMonth = date.getDayOfMonth();
        if (dayOfMonth <= CHRISTMAS_DATE) {
            discount += (1000 + 100 * (dayOfMonth - 1));
        }
        return discount;
    }
}
