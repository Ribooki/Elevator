package Elevator.Interface;

import Elevator.Button.Buttons;
import Elevator.Button.TestButtons;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class TestInterface extends Interfacer{
    List<Button> buttons;

    public TestInterface(){
        super.Interfacer();
    }

    @Override
    public ScrollPane setInterface(){
        buttons = new ArrayList<>();
        Buttons buttonsCreator = new TestButtons();
        buttons.addAll(buttonsCreator.setButtons());
        VBox subContainer = new VBox();
        subContainer.getChildren().addAll(buttons);
        container.setContent(subContainer);
        return container;
    }
}
