package Elevator;

import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MainInterface {
    private static List<Box> boxes;
    static int maxFloor; //TODO Pour le bien de tous laissons le en static, il embete personne
    public MainInterface(Stage stage){
        MainInterface.maxFloor = 10;
        boxes = new ArrayList<Box>();
        boxes.add(new CanvasBox("Elevator",stage.getWidth()/4,stage.getHeight(), 0 ,0));
        boxes.add(new Box("In elevator",stage.getWidth()/4,stage.getHeight(), stage.getWidth()/4*1, 0));
        boxes.add(new CanvasBox("in and out elevator",stage.getWidth()/4,stage.getHeight(), stage.getWidth()/4*2, 0));
        boxes.add(new ButtonsBox("out of elevator",stage.getWidth()/4,stage.getHeight(), stage.getWidth()/4*3, 0));
        ((CanvasBox) boxes.get(0)).drawElevatorInterface(0);
        ((CanvasBox) boxes.get(2)).drawStateDisplay(0);
        ((ButtonsBox)boxes.get(3)).setFloorButtons();
    }

    public static void updateElevatorFloor(double floor){
        ((CanvasBox) boxes.get(0)).drawElevatorInterface(floor);
    }

    public static void updateElevatorState(int state){((CanvasBox) boxes.get(2)).drawStateDisplay(state);};



    public List<Box> getBoxes() {
        return boxes;
    }
}
