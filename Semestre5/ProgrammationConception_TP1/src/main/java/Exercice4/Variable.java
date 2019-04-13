package Exercice4;

public class Variable implements Formula {

    private String name;
    private double value;

    /**
     * Construct a Variable object with name and value
     *
     * @param name
     * @param value
     */
    public Variable(String name, double value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String asString() {
        return name;
    }

    @Override
    public double asValue() {
        return value;
    }
}
