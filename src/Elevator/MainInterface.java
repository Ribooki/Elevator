package Elevator;

import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MainInterface {
    private static List<Box> boxes;
    static int maxFloor; //TODO Pour le bien de tous laissons le en static, il embete personne
    public MainInterface(HBox root){

        MainInterface.maxFloor = 10;
        boxes = new ArrayList<Box>();
        boxes.add(new CanvasBox("Elevator",0));
        boxes.add(new ButtonsBox("In elevator"));
        boxes.add(new CanvasBox("in and out elevator",1));
        boxes.add(new ButtonsBox("out of elevator"));
        ((CanvasBox) boxes.get(0)).getDrawer().draw(0.0);
        ((ButtonsBox)boxes.get(1)).setFloorInteriorChoice();
        ((CanvasBox) boxes.get(2)).getDrawer().draw(0);
        ((ButtonsBox)boxes.get(3)).setFloorButtons();

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
    }

    public static void updateElevatorState(int state){((CanvasBox) boxes.get(2)).getDrawer().draw(state);}



    public List<Box> getBoxes() {
        return boxes;
    }

    public void resizeBoxes(double width, double height){
        double widthRatio = width/boxes.size();
        for(int i=0; i<boxes.size(); i++){
            boxes.get(i).resize(widthRatio, height);
        }
    }
}
