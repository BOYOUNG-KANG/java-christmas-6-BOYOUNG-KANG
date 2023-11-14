package christmas.domain;

import java.util.EnumMap;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PaymentTest {
    @DisplayName("초코케이크 1개와 바비큐립 2개를 선택할 경우 지불금액은 123000원이다.")
    @Test
    void 총주문금액(){
        EnumMap<Menu, Integer> menu = new EnumMap<>(Menu.class);
        menu.put(Menu.CHOCO_CAKE, 1);
        menu.put(Menu.BBQ_RIBS, 2);

        Payment payment = new Payment(menu);
        Assertions.assertThat(payment.getPayment()).isEqualTo(123000);
    }

    @DisplayName("총주문 금액이 12만원 이상일 경우, 샴페인 1개가 증정된다.")
    @Test
    void 샴페인_증정(){
        EnumMap<Results, Integer> results = setUpResults();
        EnumMap<Menu, Integer> menu = new EnumMap<>(Menu.class);
        menu.put(Menu.CHOCO_CAKE, 1);
        menu.put(Menu.BBQ_RIBS, 3);

        assertPresentResult(menu, results);
    }

    private static void assertPresentResult(EnumMap<Menu, Integer> menu, EnumMap<Results, Integer> results) {
        Payment payment = new Payment(menu);
        payment.present(results);
        Menu.CHAMPAGNE.getPrice();
        Assertions.assertThat(results.get(Results.PRESENT)).isEqualTo(-Menu.CHAMPAGNE.getPrice());
    }
    private static EnumMap<Results, Integer> setUpResults() {
        EnumMap<Results, Integer> results = new EnumMap<>(Results.class);
        for (Results result : Results.values()) {
            results.put(result, 0);
        }
        return results;
    }
}