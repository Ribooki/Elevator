package Elevator;

import javafx.scene.layout.HBox;

public class TestInterface {
    private ButtonsBox buttonsBox;
    public TestInterface(HBox root){
        buttonsBox = new ButtonsBox("Test");
        buttonsBox.setTestButtons();
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
        buttonsBox.resize(width, height);
    }

    public ButtonsBox getButtonsBox() {
        return buttonsBox;
    }
}
