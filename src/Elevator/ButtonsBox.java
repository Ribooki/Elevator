package Elevator;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class ButtonsBox extends Box{
    List<Button> buttons;
    List<Label> floorNumbers;
    ScrollPane container;
    public ButtonsBox(String title) {
        super(title);
        buttons = new ArrayList<>();
        container = new ScrollPane();
        container.setFitToWidth(true);
        container.setPannable(true);
    }

    public void setFloorButtons(){
        floorNumbers = new ArrayList<>();
        Buttons buttonsCreator = new ExteriorButtons();
        buttons.addAll(buttonsCreator.setButtons(MainInterface.maxFloor));
        VBox boxScroll = new VBox();
        for(int i = 0; i<MainInterface.maxFloor; i++){
            String title = "Floor n°"+String.valueOf(MainInterface.maxFloor-(i+1));
            Label tempLabel = new Label(title);
            tempLabel.setWrapText(true);
            HBox box = new HBox();
            tempLabel.setLabelFor(box);
            box.getChildren().addAll(buttons.get(i*2), buttons.get(i*2+1));
            tempLabel.setLabelFor(box);
            floorNumbers.add(tempLabel);
            boxScroll.getChildren().addAll(tempLabel, box);
        }
        container = new ScrollPane();
        container.setPannable(true);
        container.setContent(boxScroll);
        super.vBox.getChildren().add(container);
    }

    public void setTestButtons(){
        Buttons buttonsCreator = new TestButtons();
        buttons.addAll(buttonsCreator.setButtons());
        vBox.getChildren().addAll(buttons);
    }

    public void setFloorInteriorChoice(){
        Buttons buttonsCreator = new InteriorButtons();
        buttons.addAll(buttonsCreator.setButtons(MainInterface.maxFloor));
        //VBox boxScroll = new VBox();
        GridPane boxScroll = new GridPane();
        for(int i=0; i<buttons.size(); i++){
            boxScroll.add(buttons.get(i), i%2, i);
        }
        //boxScroll.getChildren().addAll(buttons);
        boxScroll.setMinWidth(container.getWidth());
        //boxScroll.setFillWidth(true);
        //boxScroll.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        boxScroll.alignmentProperty().set(Pos.CENTER);
        container.setContent(boxScroll);
        super.vBox.getChildren().add(container);
    }


}
