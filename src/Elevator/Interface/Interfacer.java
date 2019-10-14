package Elevator.Interface;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;

public abstract class Interfacer {
    protected ScrollPane container;

    public void Interfacer(){
        container = new ScrollPane();
        container.setFitToWidth(true);
        container.setPannable(true);
        container.setBorder(Border.EMPTY);
        container.setBackground(Background.EMPTY);
    }

    public ScrollPane setInterface(){
        return container;
    }
}
