package christmas.util;

import christmas.domain.Category;
import christmas.domain.Menu;
import christmas.message.ValidateErrorMessage;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;

public class Validator {
    private static final int FIRST_DAY = 1;
    private static final int LAST_DAY = 31;
    private static final int MAX_NUMBER_OF_MENUS = 20;
    public int validate(int date){
        if (FIRST_DAY > date || date > LAST_DAY) {
            throw new IllegalArgumentException(ValidateErrorMessage.DATE_ERROR.getMessage());
        }
        return date;
    }
    public EnumMap<Menu, Integer> validate(EnumMap<Menu, Integer> menuInput) {
        int totalCount = 0;
        HashSet<Category> categories = new HashSet<>();
        for (Map.Entry<Menu, Integer> menu : menuInput.entrySet()) {
            totalCount += menu.getValue();
            categories.add(menu.getKey().getCategory());
        }
        validateCategories(categories);
        validateTotalCount(totalCount);
        return menuInput;
    }
    private static void validateTotalCount(int totalCount) {
        if (totalCount > MAX_NUMBER_OF_MENUS) {
            throw new IllegalArgumentException(ValidateErrorMessage.MENU_COUNT_ERROR.getMessage());
        }
    }
    private static void validateCategories(HashSet<Category> categories) {
        if (categories.contains(Category.DRINK) && categories.size() == 1) {
            throw new IllegalArgumentException(ValidateErrorMessage.MENU_CATEGORY_ERROR.getMessage());
        }
    }
}
