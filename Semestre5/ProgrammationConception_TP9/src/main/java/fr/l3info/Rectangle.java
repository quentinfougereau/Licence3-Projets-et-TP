package fr.l3info;

import javafx.scene.canvas.GraphicsContext;

public class Rectangle implements Shape {

    double x, y, width, height;

    public Rectangle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void paint(GraphicsContext graphicsContext) {
        graphicsContext.strokeRect(x, y, width, height);
    }

    @Override
    public boolean contains(double x, double y) {
        return this.x < x && x <= this.x + this.width && this.y < y && y <= this.y + this.height;
    }

    @Override
    public void translate(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    @Override
    public <R> R accept(ShapeWriterVisitor<R> visitor) {
        return visitor.visit(this);
    }

    public String toString() {
        return this.x + " " + this.y + " " + this.width + " " + this.height;
    }

}
