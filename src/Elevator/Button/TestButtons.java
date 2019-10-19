package Elevator.Button;

import Elevator.Controller.BasicProtocol;
import Elevator.Controller.FIFOprotocol;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class TestButtons extends Buttons {

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

        tempButton = new Button("Down");
        tempButton.setOnAction((ActionEvent event) -> {
            if(mode==0)
                BasicProtocol.testGoDown();
            else
                FIFOprotocol.testGoDown();

        });
        testButtons.add(tempButton);

        tempButton = new Button("Stop to next floor");
        tempButton.setOnAction((ActionEvent event) -> {
            if(mode==0)
                BasicProtocol.stopNextFloor();
            else
                FIFOprotocol.stopNextFloor();
            System.out.println("Elevator will stop at the next floor");
        });
        testButtons.add(tempButton);

        tempButton = new Button("Emergency stop");
        tempButton.setOnAction((ActionEvent event) -> {
            if(mode==0)
                BasicProtocol.emergencyStop();
            else
                FIFOprotocol.emergencyStop();
        });
        testButtons.add(tempButton);

        tempButton = new Button("Switch mode");
        tempButton.setOnAction((ActionEvent event) -> {
            if(mode==0) {
                if (!BasicProtocol.isOnFloor()){
                    System.out.println("The elevator have to be stopped to switch mode.");
                }
                else {
                    mode = 1;
                    BasicProtocol.setActive(false);
                    FIFOprotocol.setActive(true);
                    System.out.println("Switch FIFO mode");
                }
            }
            else {
                if(!FIFOprotocol.isOnFloor()){
                    System.out.println("The elevator have to be stopped to switch mode.");
                }
                else{
                    mode = 0;
                    BasicProtocol.setActive(true);
                    FIFOprotocol.setActive(false);
                    System.out.println("Switch Basic mode");
                }
            }
        });
        testButtons.add(tempButton);
        return testButtons;
    }
}
