package Elevator.Drawer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class DrawState extends Drawer {
    private int lastState;
    public DrawState(GraphicsContext gc) {
        super(gc);
    }

    @Override
    //Show state of elevator: -1 is going down, 0 is stop, is in moving up, 3 (up) / 4 (down) is breaking and 0 and 2 urgency stop
    public void draw(int state){
        lastState = state;
        gc.setFill(Color.BLACK);
        double dispHeight = height/5;
        double dispWidth = width/6;
        gc.fillRect(midX-dispWidth,midY-dispHeight, dispWidth*2, dispHeight*2);
        gc.setStroke(Color.WHITE);
        if(state == 1 || state == 3){
            gc.setFill(Color.RED);
            gc.fillPolygon(new double[]{midX, midX-dispWidth+2, midX+dispWidth-2}, new double[]{midY-dispHeight+2, midY-dispHeight+20, midY-dispHeight+20},3);
        }
        gc.strokePolygon(new double[]{midX, midX-dispWidth+2, midX+dispWidth-2}, new double[]{midY-dispHeight+2, midY-dispHeight+20, midY-dispHeight+20},3);
        gc.setFill(Color.BLACK);

        if(state == -1 || state == 4){
            gc.setFill(Color.RED);
            gc.fillPolygon(new double[]{midX, midX-dispWidth+2, midX+dispWidth-2}, new double[]{midY+dispHeight-2, midY+dispHeight-20, midY+dispHeight-20},3);
        }
        gc.strokePolygon(new double[]{midX, midX-dispWidth+2, midX+dispWidth-2}, new double[]{midY+dispHeight-2, midY+dispHeight-20, midY+dispHeight-20},3);
        gc.setFill(Color.BLACK);

        if(state == 1 || state == -1){
            gc.setFill(Color.GREEN);
            gc.fillRect(midX-dispWidth/2, midY-dispHeight/2, dispWidth, dispHeight);
        } else  if(state == 3 || state == 4){
            gc.setFill(Color.ORANGE);
            gc.fillRect(midX-dispWidth/2, midY-dispHeight/2, dispWidth, dispHeight);
        }else{
            gc.setFill(Color.RED);
            gc.fillRect(midX-dispWidth/2, midY-dispHeight/2, dispWidth, dispHeight);
            if(state == 2){
                gc.setStroke(Color.BLACK);
                gc.setFont(new Font(10));
                gc.setTextAlign(TextAlignment.CENTER);
                gc.strokeText("U", midX, midY);
            }
        }
        gc.setFill(Color.BLACK);

        gc.setStroke(Color.WHITE);
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
