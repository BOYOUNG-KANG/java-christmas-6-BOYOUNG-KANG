package christmas.domain;

import christmas.dto.EventResultsDto;
import java.util.EnumMap;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EventCalculatorTest {

    @DisplayName("초코케이크 1개와 바비큐립 2개를 선택할 경우 지불금액은 123000원이다.")
    @Test
    void 총주문금액(){
        EnumMap<Menu, Integer> menu = new EnumMap<>(Menu.class);
        menu.put(Menu.CHOCO_CAKE, 1);
        menu.put(Menu.BBQ_RIBS, 2);

        EventCalculator eventCalculator = new EventCalculator(3, menu);
        Assertions.assertThat(eventCalculator.getPayment()).isEqualTo(123000);
    }

    @DisplayName("크리스마스 기간이 다가올수록 할인 금액이 100원씩 증가한다.")
    @Test
    void 크리스마스_할인(){
        EnumMap<Menu, Integer> menu = new EnumMap<>(Menu.class);
        menu.put(Menu.CHOCO_CAKE, 1);
        menu.put(Menu.BBQ_RIBS, 1);

        assertDiscountResult(25, menu, Event.CHRISTMAS_DISCOUNT, 3400);
    }

    @DisplayName("25일이 지나면 크리스마스 디데이 할인이 적용되지 않는다.")
    @Test
    void 크리스마스_할인2(){
        EnumMap<Menu, Integer> menu = new EnumMap<>(Menu.class);
        menu.put(Menu.CHOCO_CAKE, 1);
        menu.put(Menu.BBQ_RIBS, 1);

        assertDiscountResult(27, menu, Event.CHRISTMAS_DISCOUNT, 0);
    }


    @DisplayName("평일 디저트 메뉴가 1개인 경우, 2023원 할인된다.")
    @Test
    void 평일_할인(){
        EnumMap<Menu, Integer> menu = new EnumMap<>(Menu.class);
        menu.put(Menu.CHOCO_CAKE, 1);
        menu.put(Menu.BBQ_RIBS, 1);

        assertDiscountResult(4, menu, Event.WEEKDAY_DISCOUNT, 2023);
    }

    @DisplayName("주말 메인 메뉴가 2개인 경우, 4046원 할인된다.")
    @Test
    void 주말_할인(){
        EnumMap<Menu, Integer> menu = new EnumMap<>(Menu.class);
        menu.put(Menu.T_BONE_STEAK, 1);
        menu.put(Menu.BBQ_RIBS, 1);

        assertDiscountResult(1, menu, Event.WEEKEND_DISCOUNT, 4046);
    }

    @DisplayName("특별 날짜인 경우 총주문금액에 1000원이 할인된다.")
    @Test
    void 특별_할인(){
        EnumMap<Menu, Integer> menu = new EnumMap<>(Menu.class);
        menu.put(Menu.T_BONE_STEAK, 1);
        menu.put(Menu.BBQ_RIBS, 1);

        assertDiscountResult(10, menu, Event.SPECIAL_DISCOUNT, 1000);
    }

    @DisplayName("총주문 금액이 12만원 이상일 경우, 샴페인 1개가 증정된다.")
    @Test
    void 샴페인_증정(){
        EnumMap<Menu, Integer> menu = new EnumMap<>(Menu.class);
        menu.put(Menu.CHOCO_CAKE, 1);
        menu.put(Menu.BBQ_RIBS, 3);

        assertDiscountResult(1, menu, Event.PRESENT, 25000);
    }


    private static void assertDiscountResult(int date, EnumMap<Menu, Integer> menu,
            Event weekdayDiscount, int x) {
        EventResultsDto eventResultsDto = new EventCalculator(date, menu).progress();
        Assertions.assertThat(eventResultsDto.eventResults().get(weekdayDiscount)).isEqualTo(-x);
    }
}