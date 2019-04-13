package fr.l3info.tp8;

public class Variable implements Formula {

    private String name;
    private double value;

    /**
     * Construct a Variable object containing name and value
     *
     * @param name
     * @param value
     */
    public Variable(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String asString() {
        return name;
    }

    public double asValue() {
        return value;
    }

    public <R> R accept(FormulaVisitor<R> visitor) {
        return visitor.visit(this);
    }

}
