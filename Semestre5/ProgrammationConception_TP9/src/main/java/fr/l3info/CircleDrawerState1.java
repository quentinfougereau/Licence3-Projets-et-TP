package fr.l3info;

import javafx.scene.canvas.GraphicsContext;

public class CircleDrawerState1 implements DrawerState {

    public double initialX;
    public double initialY;
    public double radius;

    public CircleDrawerState1(double x, double y) {
        this.initialX = x;
        this.initialY = y;
    }

    @Override
    public void mousePressed(DrawerContext context, double x, double y) {

    }

    @Override
    public void mouseReleased(DrawerContext context, double x, double y) {
        radius = x;
        Shape circle = new Circle(initialX, initialY, -initialX + radius);
        context.drawer().shapes.add(circle);
        context.setState(new CircleDrawerState0());
    }

    @Override
    public void mouseMoved(DrawerContext context, double x, double y) {
        context.drawer().repaint();
        radius = x;
        paint(context.drawer().getGraphicsContext2D());
    }

    @Override
    public void paint(GraphicsContext context) {
        context.strokeOval(initialX, initialY,-initialX + radius,-initialX + radius);
    }
}
