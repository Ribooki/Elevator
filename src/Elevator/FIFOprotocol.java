package Elevator;

import java.util.LinkedList;

public class FIFOprotocol extends Thread{
    private volatile static Elevator elevator = new Elevator(10);
    private volatile static LinkedList<Double> waitingCalls = new LinkedList<Double>();

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

    public synchronized static void testAscend(){
        clearWaitingCalls();
        callFrom(9.0, -1);
    }

    public synchronized static void testGoDown(){
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

    private void updateDirection(){
        if(elevator.getState() != 2 && elevator.getState() != 0){
            if(waitingCalls.getFirst() > elevator.getActualFloor()){
                ascend();
            }
            else if(waitingCalls.getFirst() < elevator.getActualFloor()){
                goDown();
            }
        }
    }

    private void update(){
        synchronized (waitingCalls) {
            if (!waitingCalls.isEmpty() && elevator.getActualFloor() == waitingCalls.getFirst()) {
                waitingCalls.removeFirst();
                System.out.println("Arrêt à l'étage " + (int)elevator.getActualFloor() + " (2s)");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(waitingCalls.isEmpty()){
                    stopElevator();
                }
            }
            updateDirection();
        }
    }

    public void run(){
        synchronized (waitingCalls){
            clearWaitingCalls();
        }
        while (true) {
            update();
            switch (elevator.getState()) {
                case -1:
                    elevator.previousStep();
                    MainInterface.updateElevatorFloor(elevator.getActualFloor());
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 0:
                    break;
                case 1:
                    elevator.nextStep();
                    MainInterface.updateElevatorFloor(elevator.getActualFloor());
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    break;
                default:
                    break;
            }
        }
    }

}
