package Elevator;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawState extends Drawer {
    private int lastState;
    public DrawState(GraphicsContext gc) {
        super(gc);
    }

    @Override
    public void draw(int state){
        lastState = state;
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

        //TODO bysare
        //Show state of elevator: 1 is in moving, 3 is breaking and 0 and 2 are for stop and urgency stop
        if(state == 1){
            gc.setFill(Color.GREEN);
            gc.fillRect(midX-dispWidth/2, midY-dispHeight/2, dispWidth, dispHeight);
        } else  if(state == 3){
            gc.setFill(Color.ORANGE);
            gc.fillRect(midX-dispWidth/2, midY-dispHeight/2, dispWidth, dispHeight);
        }else{
            gc.setFill(Color.RED);
            gc.fillRect(midX-dispWidth/2, midY-dispHeight/2, dispWidth, dispHeight);
        }
        gc.setFill(Color.BLACK);

        gc.strokeRect(midX-dispWidth/2, midY-dispHeight/2, dispWidth, dispHeight);

    }

    @Override
    public void resizeDrawer(double width, double height){
        super.resizeDrawer(width, height);
        gc.setFill(Color.WHITE);
        gc.fillRect(0,0,width,height);
        this.draw(lastState);

    }

}
