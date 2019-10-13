package Elevator;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawState extends Drawer {

    public DrawState(GraphicsContext gc) {
        super(gc);
    }

    @Override
    public void draw(int state){
        gc.setFill(Color.BLACK);
        double dispHeight = height/5;
        double dispWidth = width/6;
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
