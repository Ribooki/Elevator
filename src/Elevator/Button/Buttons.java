package Elevator.Button;


import Elevator.Elevator;
import javafx.scene.control.Button;
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