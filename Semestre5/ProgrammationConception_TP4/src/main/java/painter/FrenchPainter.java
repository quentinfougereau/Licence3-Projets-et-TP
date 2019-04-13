package painter;

import javafx.scene.canvas.GraphicsContext;

public class FrenchPainter implements Painter {

    GraphicsContext graphicsContext;

    public FrenchPainter(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    @Override
    public void drawRectangle(double v, double v1, double v2, double v3) {
        graphicsContext.strokeRect(v, v1, v2, v3);
    }

    @Override
    public void drawCircle(double v, double v1, double v2) {
        graphicsContext.strokeOval(v, v1, v2, v2);
    }
}
