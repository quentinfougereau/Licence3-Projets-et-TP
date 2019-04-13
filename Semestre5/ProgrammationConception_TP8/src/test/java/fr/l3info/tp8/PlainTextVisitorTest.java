package fr.l3info.tp8;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlainTextVisitorTest {

    private FormulaVisitor<String> visitor = new PlainTextVisitor();

    @Test
    public void testVariable() {
        Formula formula = new Variable("x", 12);
        String result = formula.accept(visitor);
        assertThat(result, equalTo("x"));
    }

    @Test
    public void testProduct() {
        Formula x = new Variable("x", 12);
        Formula y = new Variable("y", 12);
        Formula[] formulas = new Formula[2];
        formulas[0] = x;
        formulas[1] = y;
        //Formula product = new Product(x,y); //Ancien constructeur
        /* Nouveau constructeur */
        Formula product = new Product(formulas);
        String result = product.accept(visitor);
        assertThat(result, equalTo("(x*y)"));
    }

    @Test
    public void testSum() {
        Formula x = new Variable("x", 12);
        Formula y = new Variable("y", 12);
        Formula[] formulas = new Formula[2];
        formulas[0] = x;
        formulas[1] = y;
        //Formula product = new Product(x,y); //Ancien constructeur
        /* Nouveau constructeur */
        Formula sum = new Sum(formulas);
        String result = sum.accept(visitor);
        assertThat(result, equalTo("(x+y)"));
    }

}
