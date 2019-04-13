package fr.l3info.tp7;

import javafx.scene.canvas.GraphicsContext;

public class Circle implements Shape {

    double x, y, radius;

    public Circle(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void paint(GraphicsContext graphicsContext) {
        graphicsContext.strokeOval(x, y, radius, radius);
    }

    @Override
    public boolean contains(double x, double y) {
        return ((this.x - x) * (this.x - x) + (this.y - y) * (this.y - y)) <= radius * radius;
    }

    @Override
    public void translate(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }
}
