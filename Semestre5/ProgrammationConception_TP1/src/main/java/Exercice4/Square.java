package Exercice4;

public class Square implements Formula {

    private Formula operator;

    /**
     * Construct a Sum object with two formulas
     *
     * @param firstOperator
     */
    public Square(Formula firstOperator) {
        this.operator = firstOperator;
    }

    @Override
    public String asString() {
        return operator.asString() + "²";
    }

    @Override
    public double asValue() {
        return operator.asValue() * operator.asValue();
    }
}
