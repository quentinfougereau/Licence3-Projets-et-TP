package fr.l3info.tp8;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class EvaluatorVisitorTest {

    private FormulaVisitor<Double> visitor = new EvaluatorVisitor();

    @Test
    public void testVariable() {
        Formula formula = new Variable("x", 12);
        double result = formula.accept(visitor);
        assertThat(result, equalTo(12.0));
    }

    @Test
    public void testProduct() {
        Formula x = new Variable("x", 2);
        Formula y = new Variable("y", 3);
        Formula[] formulas = new Formula[2];
        formulas[0] = x;
        formulas[1] = y;
        //Formula product = new Product(x,y); //Ancien constructeur
        /* Nouveau constructeur */
        Formula product = new Product(formulas);
        double result = product.accept(visitor);
        assertThat(result, equalTo(6.0));
    }

    @Test
    public void testSum() {
        Formula x = new Variable("x", 2);
        Formula y = new Variable("y", 3);
        Formula[] formulas = new Formula[2];
        formulas[0] = x;
        formulas[1] = y;
        //Formula product = new Product(x,y); //Ancien constructeur
        /* Nouveau constructeur */
        Formula sum = new Sum(formulas);
        double result = sum.accept(visitor);
        assertThat(result, equalTo(5.0));
    }

}