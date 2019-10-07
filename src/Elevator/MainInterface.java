package Elevator;

import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MainInterface {
    private List<Box> boxs;
    static int maxFloor;
    public MainInterface(Stage stage){
        MainInterface.maxFloor = 10;
        boxs = new ArrayList<Box>();
        boxs.add(new CanvasBox("elevator",stage.getWidth()/4,stage.getHeight(), 0 ,0));
        boxs.add(new Box("in",stage.getWidth()/4,stage.getHeight(), stage.getWidth()/4*1, 0));
        boxs.add(new CanvasBox("the two",stage.getWidth()/4,stage.getHeight(), stage.getWidth()/4*2, 0));
        boxs.add(new ButtonsBox("out",stage.getWidth()/4,stage.getHeight(), stage.getWidth()/4*3, 0));
        ((CanvasBox) boxs.get(0)).drawLineElevator();
        ((CanvasBox) boxs.get(0)).drawElevator(3);
        ((CanvasBox) boxs.get(2)).drawDisplay();
        ((ButtonsBox)boxs.get(3)).setFloorsButtons();
    }

    public List<Box> getBoxs() {
        return boxs;
    }
}
