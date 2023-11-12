package christmas.view;

import christmas.message.EventMessage;

public class OutputView {
    public void printStart(){
        System.out.println(EventMessage.START_MESSAGE);
    }

    public void printEvent(int date){
        System.out.printf(EventMessage.EVENT_MESSAGE, date);
        System.out.println();
    }
}
