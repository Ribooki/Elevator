package Elevator;

public class Elevator {
    private int actualFloor;
    private int maxFloor;
    private int state; // 0-stop, 1-ascend, -1-go down

    public Elevator(int maxFloor) {
        this.actualFloor = 0;
        this.maxFloor = maxFloor;
        this.state = 0;
    }

    public int getActualFloor() {
        return actualFloor;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void nextFloor(){
        actualFloor++;
    }

    public void previousFloor(){
        actualFloor--;
    }
}
