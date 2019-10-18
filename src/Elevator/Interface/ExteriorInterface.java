package Elevator.Interface;

import Elevator.Button.Buttons;
import Elevator.Button.ExteriorButtons;
import Elevator.MainWindow;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class ExteriorInterface extends Interfacer{
    private List<Button> buttons;
    private List<Label> floorNumbers;
    private int currentFloor = -1;

    public ExteriorInterface(){
        super.Interfacer();
    }

    @Override
    public ScrollPane setInterface(){
        buttons = new ArrayList<>();
        floorNumbers = new ArrayList<>();
        Buttons buttonsCreator = new ExteriorButtons();
        buttons.addAll(buttonsCreator.setButtons(MainWindow.maxFloor));
        VBox boxScroll = new VBox();
        for(int i = 0; i< MainWindow.maxFloor; i++){
            String title = "Floor n°"+String.valueOf(MainWindow.maxFloor-(i+1));
            Label tempLabel = new Label(title);
            tempLabel.setId(String.valueOf(MainWindow.maxFloor-Math.round(i+1)));
            tempLabel.setWrapText(true);
            HBox box = new HBox();
            tempLabel.setLabelFor(box);
            box.getChildren().addAll(buttons.get(i*2), buttons.get(i*2+1));
            tempLabel.setLabelFor(box);
            floorNumbers.add(tempLabel);
            boxScroll.getChildren().addAll(tempLabel, box);
        }
        container.setContent(boxScroll);
        return container;
    }

    @Override
    public void updateInterface(int floor){
        Label tempLabel;
        if(floor == currentFloor) return;
        if(currentFloor == -1) currentFloor =0;
        for(int i=0; i<floorNumbers.size(); i++){
            tempLabel = floorNumbers.get(i);
            if(Integer.parseInt(tempLabel.getId()) == currentFloor){
                tempLabel.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            }
            if( Integer.parseInt(tempLabel.getId()) == floor){
                tempLabel.setBackground(new Background(new BackgroundFill(Color.YELLOWGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        currentFloor = floor;
    }
}
