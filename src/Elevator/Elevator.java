package Elevator;

public class Elevator {
    private double actualFloor;
    private int maxFloor;
    private int state; // 0-stop, 1-ascend, -1-go down, 2-emergencyStop, 3-unBraking, 4-downBraking

    public Elevator(int maxFloor) {
        this.actualFloor = 0;
        this.maxFloor = maxFloor;
        this.state = 0;
    }

    public double getActualFloor() {
        return actualFloor;
    }

    public int getState() {
        return state;
    }

    public int getMaxFloor() {
        return maxFloor;
    }

    public void setState(int state) {
        this.state = state;
        MainInterface.updateElevatorState(state);
    }

    public void nextStep(){
        actualFloor = actualFloor + 0.1;
        actualFloor = Math.round(actualFloor*10)/10.0;
    }

    public void previousStep(){
        actualFloor = actualFloor - 0.1;
        actualFloor = Math.round(actualFloor*10)/10.0;
    }
}
