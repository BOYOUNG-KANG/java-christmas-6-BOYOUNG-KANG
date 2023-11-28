package christmas.util;

import christmas.domain.Menu;
import christmas.message.ValidateErrorMessage;
import java.util.EnumMap;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ValidatorTest {
    Validator validator = new Validator();
    @DisplayName("날짜 입력값이 1부터 31가 아닌 경우 예외가 발생한다.")
    @Test
    void 날짜_예외처리(){
        Assertions.assertThatThrownBy(() -> validator.validate(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ValidateErrorMessage.DATE_ERROR.getMessage());
    }
    @DisplayName("메뉴 입력값이 음료만 있는 경우 예외가 발생한다.")
    @Test
    void 메뉴_음료만있는경우_예외처리(){
        EnumMap<Menu, Integer> menu = new EnumMap<>(Menu.class);
        menu.put(Menu.ZERO_COKE, 1);
        menu.put(Menu.RED_WINE, 2);
        Assertions.assertThatThrownBy(()->validator.validate(menu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ValidateErrorMessage.MENU_CATEGORY_ERROR.getMessage());
    }
    @DisplayName("메뉴 입력값이 21개 이상일 경우 예외가 발생한다.")
    @Test
    void 메뉴수_21개이상_예외처리(){
        EnumMap<Menu, Integer> menu = new EnumMap<>(Menu.class);
        menu.put(Menu.MUSHROOM_SOUP, 10);
        menu.put(Menu.T_BONE_STEAK, 10);
        menu.put(Menu.ZERO_COKE, 1);
        menu.put(Menu.RED_WINE, 2);
        Assertions.assertThatThrownBy(()->validator.validate(menu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ValidateErrorMessage.MENU_COUNT_ERROR.getMessage());
    }
}