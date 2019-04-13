package fr.l3info;

public interface ShapeWriterVisitor<R> {
    R visit(Rectangle rectangle);
    R visit(Circle circle);
}
