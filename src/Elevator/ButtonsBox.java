package Elevator;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class ButtonsBox extends Box{
    List<Button> buttons;
    public ButtonsBox(String title, double width, double height, double x, double y) {
        super(title, width, height, x, y);
        buttons = new ArrayList<Button>();
    }

    public void setFloorsButtons(){
        Buttons buttonsCreator = new Buttons();
        buttons.addAll(buttonsCreator.setFloorsButtons(MainInterface.maxFloor));
        vBox.getChildren().addAll(buttons);
    }

    public void setTestButtons(){
        Buttons buttonsCreator = new Buttons();
        buttons.addAll(buttonsCreator.setTestButtons());
        vBox.getChildren().addAll(buttons);
    }
}
