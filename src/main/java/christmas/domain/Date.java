package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.EnumMap;

public class Date {
    private static final int DISCOUNT_PRICE = 2023;

    private LocalDate date;

    public Date(int date) {
        int discount = 0;
        this.date = LocalDate.of(2023, 12, date);
    }
    private void dayOfWeekDiscount(int discount, EnumMap<Category, Integer> orderInfo){
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (dayOfWeek.equals(DayOfWeek.FRIDAY) || dayOfWeek.equals(DayOfWeek.SATURDAY)){
            discount += orderInfo.get(Category.MAIN) * DISCOUNT_PRICE;
        }
        if (!dayOfWeek.equals(DayOfWeek.FRIDAY) && !dayOfWeek.equals(DayOfWeek.SATURDAY)){
            discount += orderInfo.get(Category.DESSERT) * DISCOUNT_PRICE;
        }
    }
}
