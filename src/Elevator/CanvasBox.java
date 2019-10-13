package Elevator;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CanvasBox extends Box {
    private Canvas canvas;
    private double canvasHeight;
    private double canvasWidth;
    private double midX;
    private double midY;
    private double minDistance;
    private double floor = 0;

    public CanvasBox(String title, double width, double height, double x, double y) {
        super(title, width, height, x, y);
        resized();
        canvas = new Canvas(canvasWidth, canvasHeight);
        canvas.getGraphicsContext2D().setFill(Color.WHITE);
        canvas.getGraphicsContext2D().fillRect(2,2,canvasWidth, canvasHeight);
        vBox.getChildren().add(canvas);
    }

    public void resized(){
        canvasHeight = height-title.getGraphicTextGap()*title.getGraphicTextGap()-4;
        canvasWidth = width-4;
        midX = canvasWidth/2;
        midY = canvasHeight/2;
        if(canvasWidth<=canvasHeight){
            minDistance = canvasWidth;
        }else{
            minDistance = canvasHeight;
        }
    }


    private void drawLineElevator(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(midX-1,0, 2, canvasHeight);
    }

    private void drawElevator(double floor){
        this.floor = floor;
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.RED);
        double side = minDistance/10;
        gc.fillRect(midX-side,(canvasHeight/MainInterface.maxFloor *(MainInterface.maxFloor - floor))-side, side*2, side*2);
        gc.setStroke(Color.BLACK);
        gc.strokeText(String.valueOf(floor), midX-side, (canvasHeight/MainInterface.maxFloor *(MainInterface.maxFloor - floor)));
        gc.setFill(Color.BLACK);
    }

    private void resetElevator(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double side = minDistance/10;
        gc.setFill(Color.WHITE);
        gc.fillRect(midX-side-1,(canvasHeight/MainInterface.maxFloor *(MainInterface.maxFloor - this.floor))-side-1, side*2+2, side*2+2);
    }

    public void drawElevatorInterface(double floor){
        resetElevator();
        drawLineElevator();
        drawElevator(floor);
    }

    public void drawStateDisplay(int state){
        drawDisplay(state);
    }

    private void drawDisplay(int state){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        double dispHeight = canvasHeight/5;
        double dispWidth = canvasWidth/6;
        gc.fillRect(midX-dispWidth,midY-dispHeight, dispWidth*2, dispHeight*2);
        gc.setStroke(Color.WHITE);
        if(state == 1){
            gc.setFill(Color.RED);
            gc.fillPolygon(new double[]{midX, midX-dispWidth+2, midX+dispWidth-2}, new double[]{midY-dispHeight+2, midY-dispHeight+20, midY-dispHeight+20},3);
        }
        gc.strokePolygon(new double[]{midX, midX-dispWidth+2, midX+dispWidth-2}, new double[]{midY-dispHeight+2, midY-dispHeight+20, midY-dispHeight+20},3);
        gc.setFill(Color.BLACK);

        if(state == -1){
            gc.setFill(Color.RED);
            gc.fillPolygon(new double[]{midX, midX-dispWidth+2, midX+dispWidth-2}, new double[]{midY+dispHeight-2, midY+dispHeight-20, midY+dispHeight-20},3);
        }
        gc.strokePolygon(new double[]{midX, midX-dispWidth+2, midX+dispWidth-2}, new double[]{midY+dispHeight-2, midY+dispHeight-20, midY+dispHeight-20},3);
        gc.setFill(Color.BLACK);


        if(state == 2 || state == 0){
            gc.setFill(Color.RED);
            gc.fillRect(midX-dispWidth/2, midY-dispHeight/2, dispWidth, dispHeight);
            gc.setFill(Color.BLACK);
        }

        gc.strokeRect(midX-dispWidth/2, midY-dispHeight/2, dispWidth, dispHeight);

    }
}
