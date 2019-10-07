package Elevator;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Box {
    protected VBox vBox;
    protected double width;
    protected double height;
    protected Label title;

    public Box(String title, double width, double height, double x, double y){
        vBox = new VBox();
        vBox.setMaxSize(width, height);
        vBox.setMinSize(width, height);
        vBox.setLayoutX(x);
        vBox.setLayoutY(y);
        vBox.setStyle(
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: black;");
        this.width = width;
        this.height = height;
        this.title = new Label(title);
        vBox.getChildren().add(this.title);
    }

    public VBox getvBox() {
        return vBox;
    }
}
