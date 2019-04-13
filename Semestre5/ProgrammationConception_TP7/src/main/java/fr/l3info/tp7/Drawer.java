package fr.l3info.tp7;

import javafx.event.Event;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;


public class Drawer extends Canvas {

    DrawerContext context = new DrawerContext(this);
    List<Shape> shapes = new ArrayList<>();

    public Drawer(int width, int height) {
        super(width,height);
        setFocusTraversable(true);
        setOnMousePressed(event->context.mousePressed(event));
        setOnMouseReleased(event->context.mouseReleased(event));
        //setOnMouseMoved(event->context.mouseMoved(event));
        setOnMouseDragged(event->context.mouseMoved(event));
        //setOnMouseDragged(event->context.mouseDragged(event));
        setOnKeyPressed(event -> context.keyPressed(event));
    }

    public void repaint() {
        this.getGraphicsContext2D().clearRect(0,0,this.getWidth(),this.getHeight());
        for (Shape shape : shapes) {
            shape.paint(this.getGraphicsContext2D());
        }
    }

    public void add(Shape shape) {
        shapes.add(shape);
    }

    public Shape shapeContaining(double x, double y) {
        Shape container = null;
        for (Shape shape : shapes) {
            if (shape.contains(x, y)) {
                container = shape;
                break;
            }
        }
        return container;
    }


}
