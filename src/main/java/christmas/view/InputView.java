package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Menu;
import christmas.message.EventMessage;
import christmas.message.ValidateErrorMessage;
import java.util.EnumMap;

public class InputView {

    public int getDate(){
        try {
            System.out.println(EventMessage.DATE_INPUT_MESSAGE);
            return Integer.parseInt(Console.readLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ValidateErrorMessage.DATE_ERROR.getMessage());
        }
    }
    public EnumMap<Menu, Integer> getMenu(){
        try {
            System.out.println(EventMessage.MENU_INPUT_MESSAGE);
            return convertToMap(Console.readLine());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ValidateErrorMessage.MENU_ERROR.getMessage());
        }
    }
    private EnumMap<Menu, Integer> convertToMap(String input) {
        EnumMap<Menu, Integer> menuInput = new EnumMap<>(Menu.class);
        for (String menuItem : input.split(",")) {
            String[] parts = menuItem.split("-");
            validateMenu(parts.length != 2);
            String menuName = parts[0].trim();
            int count = Integer.parseInt(parts[1].trim());
            validateMenu(count < 1);
            Menu menu = findMenuByName(menuInput, menuName);
            menuInput.put(menu, count);
        }
        return menuInput;
    }

    private Menu findMenuByName(EnumMap<Menu, Integer> menuInput, String name) {
        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(name)) {
                validateMenu(menuInput.containsKey(menu));
                return menu;
            }
        }
        throw new IllegalArgumentException(ValidateErrorMessage.MENU_ERROR.getMessage());
    }

    private static void validateMenu(boolean menuInput) {
        if (menuInput) {
            throw new IllegalArgumentException(ValidateErrorMessage.MENU_ERROR.getMessage());
        }
    }
}