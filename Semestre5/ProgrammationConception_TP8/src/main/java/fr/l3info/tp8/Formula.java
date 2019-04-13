package fr.l3info.tp8;

public interface Formula {

    /**
     * @return formula as a string
     */
    String asString();

    /**
     * @return the result of the formula as a double
     */
    double asValue();

    public <R> R accept(FormulaVisitor<R> visitor);

}
