package Elevator;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class ExteriorButtons extends Buttons{

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
