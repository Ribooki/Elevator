package Elevator;

import Elevator.Box.InterfaceBox;
import javafx.scene.layout.HBox;

public class TestWindow {
    private InterfaceBox interfaceBox;
    public TestWindow(HBox root){
        interfaceBox = new InterfaceBox("Test", 2);
        resizeBox(root.getWidth(), root.getHeight());
        root.widthProperty().addListener((arg0, arg1, arg2) -> {
            root.setMinWidth(arg2.doubleValue());
            root.setPrefWidth(arg2.doubleValue());
            resizeBox(arg2.doubleValue(), root.getHeight());
        });

        root.heightProperty().addListener((arg0, arg1, arg2) -> {
            root.setMinHeight(arg2.doubleValue());
            root.setPrefHeight(arg2.doubleValue());
            resizeBox(root.getWidth(), arg2.doubleValue());
        });
    }

    public void resizeBox(double width, double height){
        interfaceBox.resize(width, height);
    }

    public InterfaceBox getInterfaceBox() {
        return interfaceBox;
    }
}
