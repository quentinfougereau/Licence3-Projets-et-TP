package fr.l3info;

import javafx.scene.canvas.GraphicsContext;

public interface Shape {

    void paint(GraphicsContext graphicsContext);
    boolean contains(double x, double y);
    void translate(double dx, double dy);
    public <R> R accept(ShapeWriterVisitor<R> visitor);

}
