package fr.l3info;

public class ShapeWriterVisitorImpl<R> implements ShapeWriterVisitor<R> {

    @Override
    public R visit(Rectangle rectangle) {
        return (R) ("Rectangle " + rectangle.toString());
    }

    @Override
    public R visit(Circle circle) {
        return (R) ("Circle " + circle.toString());
    }
}
