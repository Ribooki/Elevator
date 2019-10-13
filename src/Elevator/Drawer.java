package Elevator;

import javafx.scene.canvas.GraphicsContext;

public abstract class Drawer {
    protected GraphicsContext gc;
    protected double midX;
    protected double midY;
    protected double height;
    protected double width;
    protected double minDistance;


    public Drawer(GraphicsContext gc){
        this.gc = gc;
    }

    public void resizeDrawer(double width, double height){
        this.midX = width/2;
        this.midY = height/2;
        this.width = width;
        this.height = height;
        if(width<=height){
            minDistance = width;
        }else{
            minDistance = height;
        }
    }

    public void draw(double floor){

    }

    public void draw(int state){

    }

    public void draw(){

    }

    public void reset(){

    }

    public void clear(){

    }

}
