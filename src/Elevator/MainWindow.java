package Elevator;

import Elevator.Box.AbstractBox;
import Elevator.Box.CanvasBox;
import Elevator.Box.InterfaceBox;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class MainWindow {
    private static List<AbstractBox> boxes;
    public static int maxFloor;
    public MainWindow(HBox root){

        MainWindow.maxFloor = 10;
        boxes = new ArrayList<AbstractBox>();
        boxes.add(new CanvasBox("Elevator",0));
        boxes.add(new InterfaceBox("In elevator",0));
        boxes.add(new CanvasBox("in and out elevator",1));
        boxes.add(new InterfaceBox("out of elevator",1));
        ((CanvasBox) boxes.get(0)).getDrawer().draw(0.0);
        ((CanvasBox) boxes.get(2)).getDrawer().draw(0);
        ((InterfaceBox)boxes.get(1)).getInterfacePanel().updateInterface((int)Math.round(0));
        ((InterfaceBox)boxes.get(3)).getInterfacePanel().updateInterface((int)Math.round(0));
        resizeBoxes(root.getWidth(), root.getMinHeight());
        root.widthProperty().addListener((arg0, arg1, arg2) -> {
            root.setMinWidth(arg2.doubleValue());
            root.setPrefWidth(arg2.doubleValue());
            resizeBoxes(arg2.doubleValue(), root.getHeight());
        });

        root.heightProperty().addListener((arg0, arg1, arg2) -> {
            root.setMinHeight(arg2.doubleValue());
            root.setPrefHeight(arg2.doubleValue());
            resizeBoxes(root.getWidth(), arg2.doubleValue());
        });
    }

    public static void updateElevatorFloor(double floor){
        ((CanvasBox) boxes.get(0)).getDrawer().draw(floor);
        ((InterfaceBox)boxes.get(1)).getInterfacePanel().updateInterface((int)Math.round(floor));
        ((InterfaceBox)boxes.get(3)).getInterfacePanel().updateInterface((int)Math.round(floor));
    }

    public static void updateElevatorState(int state){
        ((CanvasBox) boxes.get(2)).getDrawer().draw(state);
    }

    public static void updateIndoorInterface(){
        ((InterfaceBox)boxes.get(1)).getInterfacePanel().updateInterface(-1);
    }

    public static void updateOutdoorInterface(){
        ((InterfaceBox)boxes.get(3)).getInterfacePanel().updateInterface(-1);
    }

    public List<AbstractBox> getBoxes() {
        return boxes;
    }

    public void resizeBoxes(double width, double height){
        double widthRatio = width/boxes.size();
        for(int i=0; i<boxes.size(); i++){
            boxes.get(i).resize(widthRatio, height);
        }
    }
}
