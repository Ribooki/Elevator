package Elevator.Drawer;

import Elevator.MainWindow;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

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
        gc.fillRect(midX-side,(height/ MainWindow.maxFloor *(MainWindow.maxFloor - floor))-side, side*2, side*2);
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.BLACK);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(Font.font(side));
        gc.fillText(String.valueOf(floor), midX, (height/ MainWindow.maxFloor *(MainWindow.maxFloor - floor)));
        gc.setFill(Color.BLACK);
    }

    private void resetElevator(){
        double side = minDistance/10;
        gc.setFill(Color.WHITE);
        gc.fillRect(midX-side-1,(height/ MainWindow.maxFloor *(MainWindow.maxFloor - this.floor))-side-1, side*2+2, side*2+2);
    }

    @Override
    public void draw(double floor){
        resetElevator();
        this.floor = floor;
        drawLineElevator();
        drawElevator(floor);
    }

    @Override
    public void resizeDrawer(double width, double height){
        super.resizeDrawer(width, height);
        gc.setFill(Color.WHITE);
        gc.fillRect(0,0,width,height);
        this.draw(floor);

    }

}
