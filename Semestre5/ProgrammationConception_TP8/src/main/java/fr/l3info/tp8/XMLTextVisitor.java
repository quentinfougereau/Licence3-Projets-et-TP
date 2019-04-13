package fr.l3info.tp8;

public class XMLTextVisitor implements FormulaVisitor<String>{
    @Override
    public String visit(Sum sum) {
        String result = "";
        for (Formula formula : sum.getFormulas()) {
            result += formula.accept(this);
        }
        return printXML(result, "sum");
    }

    @Override
    public String visit(Product product) {
        String result = "";
        for (Formula formula : product.getFormulas()) {
            result += formula.accept(this);
        }
        return printXML(result, "product");
    }

    @Override
    public String visit(Variable variable) {
        return printXML(variable.getName(), "var");
    }

    public String printXML(String content, String tag) {
        return  "<" + tag + ">" + content + "</" + tag + ">";
    }

}
