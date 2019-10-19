package Elevator.Button;

import javafx.scene.control.Button;
import java.util.List;

public abstract class Buttons {
    protected static int mode = 0; //0-Basic, 1-FIFO

    public Buttons(){

    }

    public static int getMode() {
        return mode;
    }

    public List<Button> setButtons(int floorCount){
        return null;
    }

    public List<Button> setButtons(){
        return null;
    }

}