package Elevator;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class InteriorButtons extends Buttons {

    @Override
    public List<Button> setButtons(int floorsCount){
        List<Button> floorsButtons = new ArrayList<Button>();
        for(double i = floorsCount-1; i>=0; i--){
            Button tempButton = new Button(String.valueOf(Math.round(i)));
            tempButton.setId(String.valueOf(i));
            tempButton.setWrapText(true);
            double finalI = i;
            tempButton.setOnAction((ActionEvent event) -> {
                System.out.println("monter: "+ finalI);
                if(mode==0)
                    BasicProtocol.callFromInside(finalI);
                //else
                //FIFOprotocol.callFromInside(finalI, 1);
            });
            floorsButtons.add(tempButton);

        }
        return floorsButtons;
    }
}
