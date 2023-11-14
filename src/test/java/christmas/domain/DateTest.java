package christmas.domain;

import java.util.EnumMap;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DateTest {

    @DisplayName("크리스마스 기간이 다가올수록 할인 금액이 100원씩 증가한다.")
    @Test
    void 크리스마스_할인(){
        EnumMap<Results, Integer> results = setUpResults();
        EnumMap<Menu, Integer> menu = new EnumMap<>(Menu.class);
        menu.put(Menu.CHOCO_CAKE, 1);
        menu.put(Menu.BBQ_RIBS, 1);

        assertDiscountResult(25, menu, results, Results.CHRISTMAS_DISCOUNT, 3400);
    }

    @DisplayName("25일이 지나면 크리스마스 디데이 할인이 적용되지 않는다.")
    @Test
    void 크리스마스_할인2(){
        EnumMap<Results, Integer> results = setUpResults();
        EnumMap<Menu, Integer> menu = new EnumMap<>(Menu.class);
        menu.put(Menu.CHOCO_CAKE, 1);
        menu.put(Menu.BBQ_RIBS, 1);

        assertDiscountResult(27, menu, results, Results.CHRISTMAS_DISCOUNT, 0);
    }


    @DisplayName("평일 디저트 메뉴가 1개인 경우, 2023원 할인된다.")
    @Test
    void 평일_할인(){
        EnumMap<Results, Integer> results = setUpResults();
        EnumMap<Menu, Integer> menu = new EnumMap<>(Menu.class);
        menu.put(Menu.CHOCO_CAKE, 1);
        menu.put(Menu.BBQ_RIBS, 1);

        assertDiscountResult(4, menu, results, Results.WEEKDAY_DISCOUNT, 2023);
    }

    @DisplayName("주말 메인 메뉴가 2개인 경우, 4046원 할인된다.")
    @Test
    void 주말_할인(){
        EnumMap<Results, Integer> results = setUpResults();
        EnumMap<Menu, Integer> menu = new EnumMap<>(Menu.class);
        menu.put(Menu.T_BONE_STEAK, 1);
        menu.put(Menu.BBQ_RIBS, 1);

        assertDiscountResult(1, menu, results, Results.WEEKEND_DISCOUNT, 4046);
    }

    @DisplayName("특별 날짜인 경우 총주문금액에 1000원이 할인된다.")
    @Test
    void 특별_할인(){
        EnumMap<Results, Integer> results = setUpResults();
        EnumMap<Menu, Integer> menu = new EnumMap<>(Menu.class);
        menu.put(Menu.T_BONE_STEAK, 1);
        menu.put(Menu.BBQ_RIBS, 1);

        assertDiscountResult(10, menu, results, Results.SPECIAL_DISCOUNT, 1000);
    }


    private static void assertDiscountResult(int date, EnumMap<Menu, Integer> menu,
            EnumMap<Results, Integer> results, Results weekdayDiscount, int x) {
        new Date(date).discount(menu, results);
        Assertions.assertThat(results.get(weekdayDiscount)).isEqualTo(-x);
    }

    private static EnumMap<Results, Integer> setUpResults() {
        EnumMap<Results, Integer> results = new EnumMap<>(Results.class);
        for (Results result : Results.values()) {
            results.put(result, 0);
        }
        return results;
    }
}