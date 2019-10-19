package Elevator.Controller;

import Elevator.Button.Buttons;
import Elevator.Elevator;
import Elevator.MainWindow;

public class BasicProtocol extends Thread{
    private volatile static Elevator elevator = new Elevator(10);
    private volatile static double[] waitingCalls = new double[elevator.getMaxFloor()];
    private volatile static int[] waitingCallsInOut = new int[elevator.getMaxFloor()]; //0-no call,-1-call down out, 1-call up out, 2-call in
    private volatile static boolean active = true;

    public static void setActive(boolean active) {
        BasicProtocol.active = active;
        if(active) {
            updateDirection();
            MainWindow.updateElevatorFloor(elevator.getActualFloor());
        }
    }

    public static boolean isOnFloor(){
        return (elevator.getActualFloor()%1 == 0);
    }

    public static int[] getWaitingCallsInOut() {
        return waitingCallsInOut;
    }

    public static void callFrom(double floor, int direction) {
        synchronized (waitingCalls) {
            if (waitingCalls[(int) floor] == 0) {
                waitingCalls[(int) floor] = direction;
                waitingCallsInOut[(int) floor]=direction;
                if(elevator.getState() == 5){
                    if(floor > elevator.getActualFloor()){
                        ascend();
                    }
                    else if(floor < elevator.getActualFloor()){
                        goDown();
                    }
                    else{
                        stopElevator();
                    }
                }
                MainWindow.updateOutdoorInterface();
            } else {
                System.out.println("Call already saved for this floor");
            }
        }
    }

    public static synchronized void callFromInside(double floor){
        synchronized (waitingCalls){
            int direction = 0;
            if(floor > elevator.getActualFloor())
                direction = 1;
            else if(floor < elevator.getActualFloor())
                direction = -1;

            if (waitingCalls[(int) floor] == 0) {
                waitingCalls[(int) floor] = direction;
                if(direction != 0)
                    waitingCallsInOut[(int) floor] = 2;
                System.out.println("call from floor: " + floor + " received");
                MainWindow.updateIndoorInterface();
            } else {
                System.out.println("call from floor " + floor + " already received");
            }


            if(elevator.getState() == 5){
                if(floor > elevator.getActualFloor()){
                    ascend();
                }
                else if(floor < elevator.getActualFloor()){
                    goDown();
                }
                else{
                    stopElevator();
                }
            }
        }
    }

    private static void clearWaitingCalls(){
        synchronized (waitingCalls) {
            for (int i = 0; i < elevator.getMaxFloor(); i++) {
                waitingCalls[i] = 0;
                waitingCallsInOut[i]=0;
            }
        }
    }

    public static synchronized void stopNextFloor() {
        double k = 0;
        if (elevator.getState() == 1 && elevator.getActualFloor()<(elevator.getMaxFloor()-1)) {
            k = (int) elevator.getActualFloor() + 2;
            elevator.setState(3);
        }
        else if (elevator.getState() == -1 && elevator.getActualFloor()>1) {
            k = (int) elevator.getActualFloor() - 1;
            elevator.setState(4);
        }
        else {
            k = (int) elevator.getActualFloor();
            elevator.setState(4);
        }
        clearWaitingCalls();
        callFrom(k, elevator.getState());
    }

    public static synchronized void emergencyStop() {
        if(elevator.getState() == 2){
            elevator.setState(5);
            clearWaitingCalls();
            System.out.println("The elevator exits the emergency stop");
        }
        else{
            elevator.setState(2);
            System.out.println("The elevator stops in emergency");
        }
    }

    public static synchronized void testAscend(){
        clearWaitingCalls();
        callFrom(9.0, -1);
    }

    public static synchronized void testGoDown(){
        clearWaitingCalls();
        callFrom(0.0, 1);

    }

    private static synchronized void ascend(){
        elevator.setState(1);
        System.out.println("The elevator go up");
    }

    private static synchronized void goDown(){
        elevator.setState(-1);
        System.out.println("The elevator go down");
    }

    private static synchronized void stopElevator(){
        elevator.setState(0);
        System.out.println("The elevator stop");
    }

    private static void stopThisFloor(){
        if((elevator.getActualFloor() % 1) == 0){
            int k = (int) elevator.getActualFloor();
            if(elevator.getState() != 2){
                synchronized (waitingCalls) {
                    if (elevator.getState() != 0 && waitingCalls[k] == elevator.getState()) {
                        stopHere(k);
                    }
                    else if(elevator.getState() == -1 && !isLowerStop()){
                        if (waitingCalls[k] == 1){
                            stopHere(k);
                        }
                    }
                    else if(elevator.getState() == 1 && !isUpperStop()){
                        if (waitingCalls[k] == -1){
                            stopHere(k);
                        }
                    }
                }
            }
            updateDirection();
        }
    }

    private static void stopHere(int k){
        waitingCalls[k] = 0;
        waitingCallsInOut[k]=0;
        System.out.println("Stop to floor " + (int)elevator.getActualFloor() + " (2s)");
        updateDirection();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static boolean isLowerStop(){
        synchronized (waitingCalls) {
            for (int i = (int) elevator.getActualFloor()-1; i >= 0; i--) {
                if (waitingCalls[i] != 0)
                    return true;
            }
        }
        return false;
    }

    private static boolean isUpperStop(){
        synchronized (waitingCalls) {
            for (int i = (int) elevator.getActualFloor()+1; i < elevator.getMaxFloor(); i++) {
                if (waitingCalls[i] != 0)
                    return true;
            }
        }
        return false;
    }

    private static void updateDirection(){
        switch (elevator.getState()){
            case -1:
                if(!isLowerStop()){
                    if(isUpperStop())
                        ascend();
                    else
                        stopElevator();
                }
                break;
            case 0:
                if(isUpperStop())
                    ascend();
                else if(isLowerStop())
                    goDown();
                break;
            case 1:
                if(!isUpperStop()){
                    if(isLowerStop())
                        goDown();
                    else
                        stopElevator();
                }
                break;
            case 2:
                //Do nothing when emergency stop
                break;
            case 3:
                if(!isUpperStop() && !isLowerStop()){
                    stopElevator();
                }
                break;
            case 4:
                if(!isLowerStop() && !isUpperStop()){
                    stopElevator();
                }
                break;
            default:
                break;
        }
    }

    public void run(){
        synchronized (waitingCalls){
            clearWaitingCalls();
        }
        while (true) {
            if(active) {
                stopThisFloor();
                switch (elevator.getState()) {
                    case -1: //goDown
                        elevator.previousStep();
                        MainWindow.updateElevatorFloor(elevator.getActualFloor());
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 0: //stop
                        break;
                    case 1: // goUp
                        elevator.nextStep();
                        MainWindow.updateElevatorFloor(elevator.getActualFloor());
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2: // emergencyStop
                        break;
                    case 3: // upBraking
                        elevator.nextStep();
                        MainWindow.updateElevatorFloor(elevator.getActualFloor());
                        try {
                            Thread.sleep(400);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 4: // downBraking
                        elevator.previousStep();
                        MainWindow.updateElevatorFloor(elevator.getActualFloor());
                        try {
                            Thread.sleep(400);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        break;
                }
                stopThisFloor();
            }
        }
    }

}
