package painter;

import javafx.scene.canvas.GraphicsContext;
import org.info.painter.EnglishPainter;

import javafx.geometry.Point2D;

public class EnglishPainterAdapter implements Painter {
    EnglishPainter englishPainter;

    public EnglishPainterAdapter(GraphicsContext graphicsContext) {
        englishPainter = new EnglishPainter(graphicsContext);
    }

    @Override
    public void drawRectangle(double v, double v1, double v2, double v3) {
        Point2D x = new Point2D(v, v1);
        Point2D y = new Point2D(v2, v3);
        englishPainter.paintRectangle(x, y);
    }

    @Override
    public void drawCircle(double v, double v1, double v2) {
        Point2D center = new Point2D(v, v1);
        Point2D width = new Point2D(v2, v2);
        englishPainter.paintCircle(center, width);
    }
}
