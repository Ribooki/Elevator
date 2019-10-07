package Elevator;

import javafx.stage.Stage;

public class TestInterface {
    private ButtonsBox buttonsBox;
    public TestInterface(Stage stage){
        buttonsBox = new ButtonsBox("Test",stage.getWidth(),stage.getHeight(), 0, 0);

        buttonsBox.setTestButtons();
    }

    public ButtonsBox getButtonsBox() {
        return buttonsBox;
    }
}
