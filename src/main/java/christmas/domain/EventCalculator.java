package christmas.domain;

import christmas.dto.EventResultsDto;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;

public class EventCalculator {
    private static final int DISCOUNT_PRICE = 2023;
    private static final int CHRISTMAS_DATE = 25;
    private LocalDate date;
    private int payment;
    private EnumMap<Menu, Integer> menu;

    public EventCalculator(int date, EnumMap<Menu, Integer> menu) {
        this.date = LocalDate.of(2023, 12, date);
        this.payment = getPayment(menu);
        this.menu = menu;
    }
    private static int getPayment(EnumMap<Menu, Integer> menuInfo) {
        return menuInfo.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }
    public EventResultsDto progress(){
        EnumMap<Event, Integer> updateEventResults = setUpEventResults();
        dayOfWeekDiscount(updateEventResults);
        specialDiscount(updateEventResults);
        christmasDiscount(updateEventResults);
        presentChampagne(updateEventResults);
        return new EventResultsDto(updateEventResults);
    }
    private static EnumMap<Event, Integer> setUpEventResults() {
        EnumMap<Event, Integer> results = new EnumMap<>(Event.class);
        for (Event result : Event.values()) {
            results.put(result, 0);
        }
        return results;
    }
    private void dayOfWeekDiscount(EnumMap<Event, Integer> results){
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (dayOfWeek.equals(DayOfWeek.FRIDAY) || dayOfWeek.equals(DayOfWeek.SATURDAY)){
            int discount = countBenefittedMenus(Category.MAIN) * DISCOUNT_PRICE;
            results.put(Event.WEEKEND_DISCOUNT, -discount);
        }
        if (!dayOfWeek.equals(DayOfWeek.FRIDAY) && !dayOfWeek.equals(DayOfWeek.SATURDAY)){
            int discount = countBenefittedMenus(Category.DESSERT) * DISCOUNT_PRICE;
            results.put(Event.WEEKDAY_DISCOUNT, -discount);
        }
    }
    private int countBenefittedMenus(Category category){
        return menu.entrySet().stream()
                .filter(entry -> entry.getKey().getCategory() == category)
                .mapToInt(Map.Entry::getValue)
                .sum();
    }
    private void specialDiscount(EnumMap<Event, Integer> results){
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (dayOfWeek.equals(DayOfWeek.SUNDAY) || date.getDayOfMonth() == CHRISTMAS_DATE){
            results.put(Event.SPECIAL_DISCOUNT, -1000);
        }
    }
    private void christmasDiscount(EnumMap<Event, Integer> results){
        int dayOfMonth = date.getDayOfMonth();
        if (dayOfMonth <= CHRISTMAS_DATE) {
            int discount = 1000 + 100 * (dayOfMonth - 1);
            results.put(Event.CHRISTMAS_DISCOUNT, -discount);
        }
    }
    private void presentChampagne(EnumMap<Event, Integer> results) {
        if (payment >= 120000) {
            results.put(Event.PRESENT, -Menu.CHAMPAGNE.getPrice());
        }
    }

    public int getPayment() {
        return payment;
    }
}