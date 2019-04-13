package formula;

public class Sum implements Formula {

    private Formula firstOperator, secondOperator;

    private Formula[] formulas;

    /**
     * Construct a Sum object with two formulas
     *
     * @param firstOperator
     * @param secondOperator
     */
    public Sum(Formula firstOperator, Formula secondOperator) {
        this.firstOperator = firstOperator;
        this.secondOperator = secondOperator;
    }

    /**
     * Construct a Sum object with formulas array
     *
     * @param formulas
     */
    public Sum(Formula[] formulas) {
        this.formulas = formulas;
    }

    /**
     * @return formula as a String
     */
    public String asString() {
        String result = "";
        if (formulas == null) {
            return "(" + firstOperator.asString() + "+" + secondOperator.asString() + ")";
        }
        for (Formula formula : formulas) {
            result += formula.asString();
            if (formula != formulas[formulas.length-1]) {
                result += "+";

            }
        }
        return result;
    }

    /**
     *
     * @return formula result
     */
    public double asValue() {
        double result = 0;
        if (formulas == null)
            return firstOperator.asValue() + secondOperator.asValue();
        for (Formula formula : formulas) {
            result += formula.asValue();
        }
        return result;
    }
}
