package Elevator;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawDefault extends Drawer {
    public DrawDefault(GraphicsContext gc) {
        super(gc);
    }

    @Override
    public void draw() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,1000,1000);
    }
}
