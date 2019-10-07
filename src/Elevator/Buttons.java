package Elevator;

import javafx.event.ActionEvent;

import javafx.scene.control.Button;

import javafx.scene.text.Text;

import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Buttons {
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
                //TODO callFrom(i, 1);
                System.out.println("monter: "+ finalI);
            });
            floorsButtons.add(tempButton);

            tempButton = new Button("Down");
            tempButton.setId(String.valueOf(i));
            tempButton.setWrapText(true);
            double finalI1 = i;
            tempButton.setOnAction((ActionEvent event) -> {
                //TODO callFrom(i, -1);
                System.out.println("descendre: "+ finalI1);
            });
            floorsButtons.add(tempButton);
        }
        return floorsButtons;
    }

    public List<Button> setTestButtons(){
        List<Button> testButtons = new ArrayList<Button>();

        Button tempButton = new Button("Up");
        tempButton.setOnAction((ActionEvent event) -> {
            System.out.println("L'ascenseur monte");
        });
        testButtons.add(tempButton);

        tempButton = new Button("Decendre");
        tempButton.setOnAction((ActionEvent event) -> {
            System.out.println("L'ascenseur descend");

        });
        testButtons.add(tempButton);

        tempButton = new Button("Arreter au prochain");
        tempButton.setOnAction((ActionEvent event) -> {
            System.out.println("L'ascenseur s'arrete au prochain étage");
        });
        testButtons.add(tempButton);

        tempButton = new Button("Arreter d'urgence");
        tempButton.setOnAction((ActionEvent event) -> {
            System.out.println("L'ascenseur s'arrete en urgence");
        });
        testButtons.add(tempButton);

        tempButton = new Button("Switch mode");
        tempButton.setOnAction((ActionEvent event) -> {
            System.out.println("L'ascenseur switch de mode entre FIFO, Round robin...");
        });
        testButtons.add(tempButton);
        return testButtons;
    }

}