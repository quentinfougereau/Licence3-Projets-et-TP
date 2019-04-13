package fr.l3info.tp7;


import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.FillRule;

public class DrawerContext {

    private DrawerState currentState;
    private Drawer drawer;

    public DrawerContext(Drawer drawer) {
        this.drawer = drawer;
    }

    public void mousePressed(MouseEvent event) {
        this.currentState.mousePressed(this, event.getX(), event.getY());
    }

    public void mouseReleased(MouseEvent event) {
        this.currentState.mouseReleased(this, event.getX(), event.getY());

    }

    public void mouseMoved(MouseEvent event) {
        this.currentState.mouseMoved(this, event.getX(), event.getY());
    }


    public void keyPressed(KeyEvent event) {
        switch (event.getText()) {
            case "c":
                this.setState(new CircleDrawerState0());
                break;
            case "r":
                this.setState(new RectangleDrawerState0());
                break;
            default:
                break;
        }
    }

    public Drawer drawer() {
        return this.drawer;
    }

    public void setState(DrawerState state) {
        this.currentState = state;
    }

}
