package Elevator;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class ButtonsBox extends Box{
    List<Button> buttons;
    List<Label> floorNumbers;
    public ButtonsBox(String title, double width, double height, double x, double y) {
        super(title, width, height, x, y);
        buttons = new ArrayList<Button>();
    }

    public void setFloorButtons(){
        floorNumbers = new ArrayList<Label>();
        Buttons buttonsCreator = new Buttons();
        buttons.addAll(buttonsCreator.setFloorButtons(MainInterface.maxFloor));
        for(int i = 0; i<MainInterface.maxFloor; i++){
            String title = "Floor n°"+String.valueOf(MainInterface.maxFloor-(i+1));
            Label tempLabel = new Label(title);
            tempLabel.setWrapText(true);
            HBox box = new HBox();
            tempLabel.setLabelFor(box);
            box.getChildren().addAll(buttons.get(i*2), buttons.get(i*2+1));
            tempLabel.setLabelFor(box);
            floorNumbers.add(tempLabel);
            vBox.getChildren().addAll(tempLabel, box);
        }
    }

    public void setTestButtons(){
        Buttons buttonsCreator = new Buttons();
        buttons.addAll(buttonsCreator.setTestButtons());
        vBox.getChildren().addAll(buttons);
    }
}
