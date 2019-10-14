package Elevator.Box;

import Elevator.Elevator;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;


public abstract class AbstractBox {
    protected VBox vBox;
    protected double width;
    protected double height;
    protected Label title;

    public AbstractBox(String title){
        vBox = new VBox();
        vBox.setMaxSize(width, height);
        vBox.setMinSize(0, 0);
        vBox.setSpacing(0);
        vBox.setPadding(new Insets(0,0, 0,0));
        vBox.setStyle(
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-color: black;");
        this.title = new Label(title);
        this.title.setTextAlignment(TextAlignment.CENTER);
        vBox.getChildren().add(this.title);
    }

    public void resize(double width, double height){
        vBox.setMinSize(width, height);
    }

    protected void setWidth(double newWidth){
        width = newWidth;
    }

    protected void setHeight(double newHeight){
        height = newHeight;
    }

    public void update(){}

    public VBox getvBox() {
        return vBox;
    }
}
