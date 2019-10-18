package Elevator;

import Elevator.Box.AbstractBox;
import Elevator.Box.CanvasBox;
import Elevator.Box.InterfaceBox;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class MainWindow {
    private static List<AbstractBox> boxes;
    public static int maxFloor; //TODO Pour le bien de tous laissons le en static, il embete personne
    public MainWindow(HBox root){

        MainWindow.maxFloor = 10;
        boxes = new ArrayList<AbstractBox>();
        boxes.add(new CanvasBox("Elevator",0));
        boxes.add(new InterfaceBox("In elevator",0));
        boxes.add(new CanvasBox("in and out elevator",1));
        boxes.add(new InterfaceBox("out of elevator",1));
        ((CanvasBox) boxes.get(0)).getDrawer().draw(0.0);
        //((ButtonsBox)boxes.get(1)).setFloorInteriorChoice();
        ((CanvasBox) boxes.get(2)).getDrawer().draw(0);
        //((ButtonsBox)boxes.get(3)).setFloorButtons();

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
        //((ButtonsBox)boxes.get(1)).setFloorInteriorChoice();
    }

    public static void updateElevatorState(int state){((CanvasBox) boxes.get(2)).getDrawer().draw(state);}



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