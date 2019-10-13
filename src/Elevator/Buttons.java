package Elevator;

import javafx.event.ActionEvent;

import javafx.scene.control.Button;

import javafx.scene.text.Text;

import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Buttons {
    private int mode = 0; //0-Basic, 1-FIFO

    //si change en main informer le chieur
    public Buttons(){

    }

    public List<Button> setFloorButtons(int floorsCount){
        List<Button> floorsButtons = new ArrayList<Button>();
        for(double i = floorsCount-1; i>=0; i--){
            Button tempButton = new Button("Up");
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

            tempButton = new Button("Down");
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

    public List<Button> setTestButtons(){
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

    public List<Button> setFloorInteriorChoice(int floorsCount){
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