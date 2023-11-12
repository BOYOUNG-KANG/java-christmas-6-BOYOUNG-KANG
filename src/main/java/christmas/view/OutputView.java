package christmas.view;

import christmas.domain.Menu;
import christmas.message.EventMessage;
import java.text.DecimalFormat;
import java.util.EnumMap;
import java.util.Map;

public class OutputView {
    

    public void printStart(){
        System.out.println(EventMessage.START_MESSAGE);
    }

    public void printEvent(int date){
        System.out.printf(EventMessage.EVENT_MESSAGE, date);
    }
    public void printOrder(EnumMap<Menu, Integer> menu){
        System.out.println("\n\n" + EventMessage.MENU);
        for (Map.Entry<Menu, Integer> entry : menu.entrySet()) {
            System.out.println(entry.getKey().getName() + " " + entry.getValue() + "개");
        }
    }
    public void printOriginPayment(int payment){
        System.out.println("\n" + EventMessage.PAYMENT);
        System.out.println(new DecimalFormat("###,###").format(payment) + "원");
    }

}
