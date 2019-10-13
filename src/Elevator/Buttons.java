package Elevator;

import javafx.event.ActionEvent;

import javafx.scene.control.Button;

import javafx.scene.text.Text;

import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public abstract class Buttons {
    protected int mode = 0; //0-Basic, 1-FIFO

    //si change en main informer le chieur
    public Buttons(){

    }

    public List<Button> setButtons(int floorCount){
        return null;
    }

    public List<Button> setButtons(){
        return null;
    }








}