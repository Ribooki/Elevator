package Elevator.Button;

import Elevator.BasicProtocol;
import Elevator.FIFOprotocol;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class ExteriorButtons extends Buttons {

    @Override
    public List<Button> setButtons(int floorsCount){
        List<Button> floorsButtons = new ArrayList<Button>();
        Button tempButton;
        for(double i = floorsCount-1; i>=0; i--){
            tempButton = new Button("↑");
            tempButton.setId(String.valueOf(i));
            tempButton.setWrapText(true);
            double finalI = i;
            tempButton.setOnAction((ActionEvent event) -> {
                System.out.println("monter: "+ finalI);
                if(mode==0)
                    BasicProtocol.callFrom(finalI, 1);
                else
                    FIFOprotocol.callFrom(finalI, 1);
            });
            floorsButtons.add(tempButton);

            tempButton = new Button("↓");
            tempButton.setId(String.valueOf(i));
            tempButton.setWrapText(true);
            double finalI1 = i;
            tempButton.setOnAction((ActionEvent event) -> {
                System.out.println("descendre: "+ finalI1);
                if(mode==0)
                    BasicProtocol.callFrom(finalI, -1);
                else
                    FIFOprotocol.callFrom(finalI, -1);
            });
            floorsButtons.add(tempButton);
        }
        return floorsButtons;
    }
}
