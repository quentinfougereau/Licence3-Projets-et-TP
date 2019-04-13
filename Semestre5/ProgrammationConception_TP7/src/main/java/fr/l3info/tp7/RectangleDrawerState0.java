package fr.l3info.tp7;

import javafx.scene.canvas.GraphicsContext;

public class RectangleDrawerState0 implements DrawerState {

    public RectangleDrawerState0() {

    }

    @Override
    public void mousePressed(DrawerContext context, double x, double y) {
        context.setState(new RectangleDrawerState1(x, y));
    }

    @Override
    public void mouseReleased(DrawerContext context, double x, double y) {

    }

    @Override
    public void mouseMoved(DrawerContext context, double x, double y) {

    }

    @Override
    public void paint(GraphicsContext context) {

    }
}
