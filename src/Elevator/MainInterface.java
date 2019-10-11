package Elevator;

import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MainInterface {
    private static List<Box> boxs; //TODO "boxes" non ?
    static int maxFloor; //TODO private et getter
    public MainInterface(Stage stage){
        MainInterface.maxFloor = 10;
        boxs = new ArrayList<Box>();
        boxs.add(new CanvasBox("Elevator",stage.getWidth()/4,stage.getHeight(), 0 ,0));
        boxs.add(new Box("In elevator",stage.getWidth()/4,stage.getHeight(), stage.getWidth()/4*1, 0));
        boxs.add(new CanvasBox("in and out elevator",stage.getWidth()/4,stage.getHeight(), stage.getWidth()/4*2, 0));
        boxs.add(new ButtonsBox("out of elevator",stage.getWidth()/4,stage.getHeight(), stage.getWidth()/4*3, 0));
        ((CanvasBox) boxs.get(0)).drawElevatorInterface(0);
        ((CanvasBox) boxs.get(2)).drawStateDisplay(0);
        ((ButtonsBox)boxs.get(3)).setFloorButtons();
    }

    public static void updateElevatorFloor(double floor){
        ((CanvasBox) boxs.get(0)).drawElevatorInterface(floor);
    }

    public static void updateElevatorState(int state){((CanvasBox) boxs.get(0)).drawElevatorInterface(state);};



    public List<Box> getBoxs() {
        return boxs;
    }
}
