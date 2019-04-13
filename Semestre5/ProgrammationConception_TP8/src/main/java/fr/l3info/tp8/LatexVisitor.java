package fr.l3info.tp8;

public class LatexVisitor implements FormulaVisitor<String> {
    @Override
    public String visit(Sum sum) {
        String result = "$";
        int length = sum.getFormulas().length;
        for (Formula formula : sum.getFormulas()) {
            result += formula.accept(this);
            if (formula != sum.getFormulas()[length-1])
                result += " + ";
        }
        return result + "$";
    }

    @Override
    public String visit(Product product) {
        String result = "$";
        int length = product.getFormulas().length;
        for (Formula formula : product.getFormulas()) {
            result += formula.accept(this);
            if (formula != product.getFormulas()[length-1])
                result += " \\times ";
        }
        return result + "$";
    }

    @Override
    public String visit(Variable variable) {
        return variable.getName();
    }
}
