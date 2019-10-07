package Elevator;

import java.util.LinkedList;

public abstract class Protocol{
    private Elevator elevator;
    private LinkedList<Double> waitingCalls = new LinkedList<>();

    public Protocol(Elevator elevator) {
        this.elevator = elevator;
    }

    public LinkedList<Double> getWaitingCalls() {
        return waitingCalls;
    }

    public void callFrom(double floor, int direction){
        this.waitingCalls.add(direction*floor);
    }

    public void stopNextFloor(){

    }

    public void stopUrgence(){

    }

    public abstract void update();

}
