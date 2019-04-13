package formula;

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

    public String asString() {
        return operator.asString() + "²";
    }

    public double asValue() {
        return operator.asValue() * operator.asValue();
    }

}
