package Elevator.Controller;

import Elevator.Elevator;
import Elevator.MainWindow;

import java.util.LinkedList;

public class FIFOprotocol extends Thread{
    private volatile static Elevator elevator = new Elevator(10);
    private volatile static LinkedList<Double> waitingCalls = new LinkedList<Double>();
    private volatile static boolean active = false;

    public static void setActive(boolean active) {
        FIFOprotocol.active = active;
        if(active) {
            updateDirection();
            MainWindow.updateElevatorFloor(elevator.getActualFloor());
        }
    }

    public static boolean isOnFloor(){
        return (elevator.getActualFloor()%1 == 0);
    }

    public static void callFrom(double floor, int direction) {
        synchronized (waitingCalls) {
            if (!waitingCalls.contains(floor)) {
                waitingCalls.add(floor);
            } else {
                System.out.println("Un appel depuis cet étage est dégà enregistré.");
            }
        }
    }

    private static void clearWaitingCalls(){
        synchronized (waitingCalls) {
            waitingCalls.clear();
        }
    }

    public synchronized static void stopNextFloor() {
        double k = 0;
        if (elevator.getState() == 1)
            k = (int)elevator.getActualFloor()+2;
        else if (elevator.getState() == -1)
            k = (int)elevator.getActualFloor()-1;
        clearWaitingCalls();
        callFrom(k, elevator.getState());
    }

    public synchronized static void emergencyStop() {
        if(elevator.getState() == 2){
            elevator.setState(0);
            System.out.println("L'ascenseur sort de l'arrêt urgence");
        }
        else{
            elevator.setState(2);
            System.out.println("L'ascenseur s'arrête en urgence");
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

    public synchronized static void ascend(){
        elevator.setState(1);
        System.out.println("L'ascenseur monte");
    }

    public synchronized static void goDown(){
        elevator.setState(-1);
        System.out.println("L'ascenseur descend");
    }

    public synchronized static void stopElevator(){
        elevator.setState(0);
        System.out.println("L'ascenseur s'arrête");
    }

    private static void updateDirection(){
        if(elevator.getState() != 2 && waitingCalls.size() != 0){
            if(waitingCalls.getFirst() > elevator.getActualFloor()){
                ascend();
            }
            else if(waitingCalls.getFirst() < elevator.getActualFloor()){
                goDown();
            }
            else{
                stopElevator();
            }
        }
        else if(elevator.getState() != 2 && elevator.getState() != 0){
            stopElevator();
        }
    }

    private static void stopThisFloor(){
        if((elevator.getActualFloor() % 1) == 0){
            int k = (int) elevator.getActualFloor();
            if(elevator.getState() != 2){
                synchronized (waitingCalls) {
                    if (elevator.getState() != 0 && waitingCalls.getFirst() == k) {
                        stopHere();
                    }
                }
            }
            updateDirection();
        }
    }

    private static void stopHere(){
        waitingCalls.removeFirst();
        System.out.println("Arrêt à l'étage " + (int)elevator.getActualFloor() + " (2s)");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
