package Elevator;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawElevator extends Drawer{
    double floor;

    public DrawElevator(GraphicsContext gc) {
        super(gc);
        floor = 0.0;
    }

    private void drawLineElevator(){
        gc.setFill(Color.BLACK);
        gc.fillRect(midX-1,0, 2, height);
    }

    private void drawElevator(double floor){
        gc.setFill(Color.RED);
        double side = minDistance/10;
        System.out.println(side);
        gc.fillRect(midX-side,(height/MainInterface.maxFloor *(MainInterface.maxFloor - floor))-side, side*2, side*2);
        gc.setStroke(Color.BLACK);
        gc.strokeText(String.valueOf(floor), midX-side, (height/MainInterface.maxFloor *(MainInterface.maxFloor - floor)));
        gc.setFill(Color.BLACK);
    }

    private void resetElevator(){
        double side = minDistance/10;
        gc.setFill(Color.WHITE);
        gc.fillRect(midX-side-1,(height/MainInterface.maxFloor *(MainInterface.maxFloor - this.floor))-side-1, side*2+2, side*2+2);
    }

    @Override
    public void draw(double floor){
        resetElevator();
        this.floor = floor;
        drawLineElevator();
        drawElevator(floor);
    }

}
