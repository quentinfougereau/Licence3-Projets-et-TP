package fr.l3info.tp8;

public interface FormulaVisitor<T> {
    T visit(Sum sum);
    T visit(Product product);
    T visit(Variable variable);
}
