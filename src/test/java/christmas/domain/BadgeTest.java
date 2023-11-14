package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BadgeTest {

    @DisplayName("총혜택 금액이 5천원 미만일 경우 NOTHING이 나온다.")
    @Test
    void 총혜택금액_5천원미만(){
        assertBadge(4000, Badge.NOTHING);
    }

    @DisplayName("총혜택 금액이 5천원 이상일 경우 별이 나온다.")
    @Test
    void 총혜택금액_5천원이상(){
        assertBadge(5400, Badge.STAR);
    }

    @DisplayName("총혜택 금액이 1만원 이상일 경우 트리가 나온다.")
    @Test
    void 총혜택금액_1만원이상(){
        assertBadge(11400, Badge.TREE);
    }

    @DisplayName("총혜택 금액이 2만원 이상일 경우 산타가 나온다.")
    @Test
    void 총혜택금액_2만원이상(){
        assertBadge(20000, Badge.SANTA);
    }

    private static void assertBadge(int totalDiscount, Badge nothing) {
        Badge badge = Badge.get(totalDiscount);
        Assertions.assertThat(badge).isEqualTo(nothing);
    }
}