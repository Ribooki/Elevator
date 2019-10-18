package Elevator.Interface;

import Elevator.Button.Buttons;
import Elevator.Button.InteriorButtons;
import Elevator.MainWindow;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class InteriorInterface extends Interfacer{
    private List<Button> buttons;
    private int currentFloor = -1;

    public InteriorInterface(){
        super.Interfacer();
    }

    @Override
    public ScrollPane setInterface(){
        buttons = new ArrayList<>();
        Buttons buttonsCreator = new InteriorButtons();
        buttons.addAll(buttonsCreator.setButtons(MainWindow.maxFloor));
        GridPane boxScroll = new GridPane();
        for(int i=0; i<buttons.size(); i++){
            boxScroll.add(buttons.get(i), i%2, i);
        }
        boxScroll.setMinWidth(container.getWidth());
        boxScroll.setBorder(Border.EMPTY);
        boxScroll.alignmentProperty().set(Pos.CENTER);
        container.setContent(boxScroll);
        return container;
    }

    public void updateInterface(int floor){
        Button tempButton;
        if(floor == currentFloor) return;
        if(currentFloor == -1) currentFloor =0;
        for(int i=0; i<buttons.size(); i++){
            tempButton = buttons.get(i);
            if(Integer.parseInt(tempButton.getId()) == currentFloor){
                tempButton.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
            }
            if( Integer.parseInt(tempButton.getId()) == floor){
                tempButton.setBackground(new Background(new BackgroundFill(Color.YELLOWGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        currentFloor = floor;
    }
}
