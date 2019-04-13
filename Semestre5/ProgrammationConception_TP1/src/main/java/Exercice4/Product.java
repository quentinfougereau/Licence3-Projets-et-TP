package Exercice4;

public class Product implements Formula {

    private Formula firstOperator, secondOperator;

    private Formula[] formulas;

    /**
     * Construct a Product object with two formulas
     *
     * @param firstOperator
     * @param secondOperator
     */
    public Product(Formula firstOperator, Formula secondOperator) {
        this.firstOperator = firstOperator;
        this.secondOperator = secondOperator;
    }

    public Product(Formula[] formulas) {
        this.formulas = formulas;
    }

    @Override
    public String asString() {
        String result = "";
        if (formulas == null) {
            return "(" + firstOperator.asString() + "*" + secondOperator.asString() + ")";
        }
        for (Formula formula : formulas) {
            result += formula.asString();
            if (formula != formulas[formulas.length-1]) {
                result += "*";

            }
        }
        return result;
    }

    @Override
    public double asValue() {
        double result = 1;
        if (formulas == null)
            return firstOperator.asValue() * secondOperator.asValue();
        for (Formula formula : formulas) {
            result *= formula.asValue();
        }
        return result;
    }
}
