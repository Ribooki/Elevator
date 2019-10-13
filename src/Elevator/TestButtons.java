package Elevator;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class TestButtons extends Buttons{

    @Override
    public List<Button> setButtons(){
        List<Button> testButtons = new ArrayList<Button>();

        Button tempButton = new Button("Up");
        tempButton.setOnAction((ActionEvent event) -> {
            if(mode==0)
                BasicProtocol.testAscend();
            else
                FIFOprotocol.testAscend();
        });
        testButtons.add(tempButton);

        tempButton = new Button("Decendre");
        tempButton.setOnAction((ActionEvent event) -> {
            if(mode==0)
                BasicProtocol.testGoDown();
            else
                FIFOprotocol.testGoDown();

        });
        testButtons.add(tempButton);

        tempButton = new Button("Arreter au prochain");
        tempButton.setOnAction((ActionEvent event) -> {
            if(mode==0)
                BasicProtocol.stopNextFloor();
            else
                FIFOprotocol.stopNextFloor();
            System.out.println("L'ascenseur s'arretera au prochain étage");
        });
        testButtons.add(tempButton);

        tempButton = new Button("Arreter d'urgence");
        tempButton.setOnAction((ActionEvent event) -> {
            if(mode==0)
                BasicProtocol.emergencyStop();
            else
                FIFOprotocol.emergencyStop();
        });
        testButtons.add(tempButton);

        tempButton = new Button("Switch mode");
        tempButton.setOnAction((ActionEvent event) -> {
//            if(mode==0)TODO
//                mode=1;
//            else
//                mode=0;
            System.out.println("L'ascenseur switch de mode entre FIFO, Round robin...");
        });
        testButtons.add(tempButton);
        return testButtons;
    }
}
