package Exercice4;

public class AbsoluteValue implements Formula {

    private Formula operator;

    /**
     * Construct a Sum object with two formulas
     *
     * @param firstOperator
     */
    public AbsoluteValue(Formula firstOperator) {
        this.operator = firstOperator;
    }

    @Override
    public String asString() {
        return "|" + operator.asString() + "|";
    }

    @Override
    public double asValue() {
        return Math.abs(operator.asValue());
    }
}
