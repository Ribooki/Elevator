package Elevator;

public class Elevator {
    private int actualFloor;
    private int maxFloor;

    public Elevator(int maxFloor) {
        this.actualFloor = 0;
        this.maxFloor = maxFloor;
    }

    public int getActualFloor() {
        return actualFloor;
    }

    public void nextFloor(){
        actualFloor++;
    }

    public void previousFloor(){
        actualFloor--;
    }
}
