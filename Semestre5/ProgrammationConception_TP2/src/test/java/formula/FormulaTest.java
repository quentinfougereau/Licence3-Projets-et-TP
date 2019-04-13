package formula;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class FormulaTest {

    @Test
    public void VariableAsString() {
        Formula x = new Variable("x",444);
        assertThat(x.asString(), equalTo("x"));
    }

    @Test
    public void VariableAsValue() {
        Formula x = new Variable("x", 444);
        double expected = 444;
        assertThat(x.asValue(), equalTo(expected));
    }

    @Test
    public void SumAsStringTest() {
        Formula formula = new Sum(new Variable("x", 6), new Variable("y", 12));
        assertThat(formula.asString(), equalTo("(x+y)"));
    }

    @Test
    public void SumAsValue() {
        Formula formula = new Sum(new Variable("x", 6), new Variable("y", 12));
        double expected = 18;
        assertThat(formula.asValue(), equalTo(expected));
    }

    @Test
    public void ProductAsString() {
        Formula formula = new Product(new Variable("x", 6), new Variable("y", 6));
        assertThat(formula.asString(), equalTo("(x*y)"));
    }

    @Test
    public void ProductAsValue() {
        Formula formula = new Product(new Variable("x", 6), new Variable("y", 6));
        double expected = 36;
        assertThat(formula.asValue(), equalTo(expected));
    }

    @Test
    public void ArraySumsAsString() {
        Formula x = new Variable("x", 6);
        Formula y = new Variable("y", 10);
        Formula Firstformula = new Product(new Sum(x,y), new Sum(x,y));
        Formula Secondformula = new Sum(new Product(x,y), new Product(x,y));
        Formula Thirdformula = new Sum(new Product(x,y), new Sum(x,y));
        Formula[] formulas = new Formula[3];

        formulas[0] = Firstformula;
        formulas[1] = Secondformula;
        formulas[2] = Thirdformula;

        Formula sums = new Sum(formulas);

        String expected = "((x+y)*(x+y))+((x*y)+(x*y))+((x*y)+(x+y))";
        assertThat(sums.asString(), equalTo(expected));
    }

    @Test
    public void ArraySumsAsValue() {
        Formula x = new Variable("x", 6);
        Formula y = new Variable("y", 10);
        Formula Firstformula = new Product(new Sum(x,y), new Sum(x,y));
        Formula Secondformula = new Sum(new Product(x,y), new Product(x,y));
        Formula Thirdformula = new Sum(new Product(x,y), new Sum(x,y));
        Formula[] formulas = new Formula[3];

        formulas[0] = Firstformula;
        formulas[1] = Secondformula;
        formulas[2] = Thirdformula;

        Formula sums = new Sum(formulas);

        double expected = 452;
        assertThat(sums.asValue(), equalTo(expected));
    }

    @Test
    public void ArrayProductAsString() {
        Formula x = new Variable("x", 6);
        Formula y = new Variable("y", 10);
        Formula Firstformula = new Product(new Sum(x,y), new Sum(x,y));
        Formula Secondformula = new Sum(new Product(x,y), new Product(x,y));
        Formula Thirdformula = new Sum(new Product(x,y), new Sum(x,y));
        Formula[] formulas = new Formula[3];

        formulas[0] = Firstformula;
        formulas[1] = Secondformula;
        formulas[2] = Thirdformula;

        Formula products = new Product(formulas);

        String expected = "((x+y)*(x+y))*((x*y)+(x*y))*((x*y)+(x+y))";
        assertThat(products.asString(), equalTo(expected));
    }

    @Test
    public void ArrayProductAsValue() {
        Formula x = new Variable("x", 6);
        Formula y = new Variable("y", 10);
        Formula Firstformula = new Product(new Sum(x,y), new Sum(x,y));
        Formula Secondformula = new Sum(new Product(x,y), new Product(x,y));
        Formula Thirdformula = new Sum(new Product(x,y), new Sum(x,y));
        Formula[] formulas = new Formula[3];

        formulas[0] = Firstformula;
        formulas[1] = Secondformula;
        formulas[2] = Thirdformula;

        Formula products = new Product(formulas);

        double expected = 2334720;
        assertThat(products.asValue(), equalTo(expected));
    }



}
