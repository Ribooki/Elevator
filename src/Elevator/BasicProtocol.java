package Elevator;

import java.util.ArrayList;

public class BasicProtocol extends Protocol{

    public BasicProtocol(Elevator elevator) {
        super(elevator);
    }

    @Override
    public void update() {
        if(getWaitingCalls().size() == 0){

        }
    }
}
