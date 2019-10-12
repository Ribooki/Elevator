package Elevator;

public class BasicProtocol extends Thread{
    private volatile static Elevator elevator = new Elevator(10);
    private volatile static double[] waitingCalls = new double[elevator.getMaxFloor()];

    public static void callFrom(double floor, int direction) {
        synchronized (waitingCalls) {
            if (waitingCalls[(int) floor] == 0) {
                waitingCalls[(int) floor] = direction;
            } else {
                System.out.println("Un appel depuis cet étage est dégà enregistré.");
            }
        }
    }

    private static void clearWaitingCalls(){
        synchronized (waitingCalls) {
            for (int i = 0; i < elevator.getMaxFloor(); i++) {
                waitingCalls[i] = 0;
            }
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

    public void stopThisFloor(){
        if((elevator.getActualFloor() % 1) == 0){
            int k = (int) elevator.getActualFloor();
            if(elevator.getState() != 2){
                synchronized (waitingCalls) {
                    if (elevator.getState() != 0 && waitingCalls[k] == elevator.getState()) {
                        waitingCalls[k] = 0;
                        System.out.println("Arrêt à l'étage " + (int)elevator.getActualFloor() + " (2s)");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private boolean isLowerStop(){
        synchronized (waitingCalls) {
            for (int i = (int) elevator.getActualFloor(); i >= 0; i--) {
                if (waitingCalls[i] != 0)
                    return true;
            }
        }
        return false;
    }

    private boolean isUpperStop(){
        synchronized (waitingCalls) {
            for (int i = (int) elevator.getActualFloor()+1; i < elevator.getMaxFloor(); i++) {
                if (waitingCalls[i] != 0)
                    return true;
            }
        }
        return false;
    }

    private void updateDirection(){
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
            default:
                break;
        }
    }

    public void run(){
        synchronized (waitingCalls){
            clearWaitingCalls();
        }
        while (true) {
            stopThisFloor();
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
            stopThisFloor();
            updateDirection();
        }
    }

}
