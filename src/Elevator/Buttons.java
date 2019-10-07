package Elevator;

import javafx.event.ActionEvent;

import javafx.scene.control.Button;

import javafx.scene.text.Text;

import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Buttons {
    public Buttons(){

    }

    public List<Button> setFloorsButtons(int floorsCount){
        List<Button> floorsButtons = new ArrayList<Button>();
        for(int i = 0; i<floorsCount; i++){
            String title = "floor n°"+String.valueOf(i);
            Button tempButton = new Button(title);
            tempButton.setWrapText(true);
            floorsButtons.add(tempButton);
        }
        return floorsButtons;
    }

    public List<Button> setTestButtons(){
        List<Button> testButtons = new ArrayList<Button>();

        Button tempButton = new Button("Monter");
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