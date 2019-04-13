package fr.l3info.tp8;

public class EvaluatorVisitor implements FormulaVisitor<Double>{
    @Override
    public Double visit(Sum sum) {
        Double result = 0.0;
        for (Formula formula : sum.getFormulas()) {
            result += formula.accept(this);
        }
        return result;
    }

    @Override
    public Double visit(Product product) {
        Double result = 1.0;
        for (Formula formula : product.getFormulas()) {
            result *= formula.accept(this);
        }
        return result;
    }

    @Override
    public Double visit(Variable variable) {
        return variable.asValue();
    }
}
