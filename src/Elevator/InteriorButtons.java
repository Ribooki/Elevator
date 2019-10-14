package Elevator;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class InteriorButtons extends Buttons {

    @Override
    public List<Button> setButtons(int floorsCount){
        List<Button> floorsButtons = new ArrayList<Button>();
        Button tempButton;
        for(double i = floorsCount-1; i>=0; i--){
            tempButton = new Button(String.valueOf(Math.round(i)));
            tempButton.setShape(new Circle(10));
            tempButton.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10.0), new BorderWidths(1.0))));
            tempButton.setId(String.valueOf(i));
            tempButton.setWrapText(true);
            double finalI = i;
            tempButton.setOnAction((ActionEvent event) -> {
                if(mode==0)
                    BasicProtocol.callFromInside(finalI);
                //else
                //FIFOprotocol.callFromInside(finalI, 1);
            });
            floorsButtons.add(tempButton);
        }
        tempButton = new Button("E");
        tempButton.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        floorsButtons.add(tempButton);
        tempButton.setOnAction((ActionEvent event) -> {
            if(mode==0)
                BasicProtocol.emergencyStop();
            else
                FIFOprotocol.emergencyStop();
        });
        return floorsButtons;
    }
}
