package fr.l3info;

import javafx.scene.canvas.GraphicsContext;

public class RectangleDrawerState1 implements DrawerState {

    public double initialX;
    public double initialY;
    public double finalX;
    public double finalY;

    public RectangleDrawerState1(double x, double y) {
        this.initialX = x;
        this.initialY = y;
    }

    @Override
    public void mousePressed(DrawerContext context, double x, double y) {

    }

    @Override
    public void mouseReleased(DrawerContext context, double x, double y) {
        finalX = x;
        finalY = y;
        Shape rectangle = new Rectangle(initialX, initialY, -initialX + finalX, -initialY + finalY);
        context.drawer().shapes.add(rectangle);
        context.setState(new RectangleDrawerState0());
    }

    @Override
    public void mouseMoved(DrawerContext context, double x, double y) {
        context.drawer().repaint();
        finalX = x;
        finalY = y;
        paint(context.drawer().getGraphicsContext2D());
    }

    @Override
    public void paint(GraphicsContext context) {
        context.strokeRect(initialX, initialY,-initialX + finalX,-initialY + finalY);
    }
}
