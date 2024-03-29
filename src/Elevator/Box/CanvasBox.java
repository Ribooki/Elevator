package Elevator.Box;

import Elevator.Drawer.DrawDefault;
import Elevator.Drawer.DrawElevator;
import Elevator.Drawer.DrawState;
import Elevator.Drawer.Drawer;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class CanvasBox extends AbstractBox {
    private Canvas canvas;
    private Drawer drawer;
    private double canvasHeight;
    private double canvasWidth;

    public CanvasBox(String title, int thingToDraw) {
        super(title);
        canvas = new Canvas(canvasWidth, canvasHeight);
        canvas.getGraphicsContext2D().setFill(Color.WHITE);
        canvas.getGraphicsContext2D().fillRect(2,2,canvasWidth, canvasHeight);
        vBox.getChildren().add(canvas);
        switch (thingToDraw){
            case 0:
                this.drawer = new DrawElevator(canvas.getGraphicsContext2D());
                break;
            case 1:
                this.drawer = new DrawState(canvas.getGraphicsContext2D());
                break;
            default:
                this.drawer = new DrawDefault(canvas.getGraphicsContext2D());
                break;
        }
        resized();
        vBox.widthProperty().addListener((arg0, arg1, arg2) -> {
            setWidth(arg2.doubleValue());
            resized();
        });

        vBox.heightProperty().addListener((arg0, arg1, arg2) -> {
            setHeight(arg2.doubleValue());
            resized();

        });
    }

    public void resized(){
        canvasHeight = height-title.getGraphicTextGap()*title.getGraphicTextGap()-4;
        canvasWidth = width-4;
        canvas.setHeight(canvasHeight);
        canvas.setWidth(canvasWidth);
        drawer.resizeDrawer(canvasWidth, canvasHeight);
    }

    public Drawer getDrawer() {
        return drawer;
    }



}
