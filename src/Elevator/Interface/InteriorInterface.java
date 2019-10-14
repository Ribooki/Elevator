package Elevator.Interface;

import Elevator.Button.Buttons;
import Elevator.Button.InteriorButtons;
import Elevator.MainWindow;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class InteriorInterface extends Interfacer{
    private List<Button> buttons;

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
}
