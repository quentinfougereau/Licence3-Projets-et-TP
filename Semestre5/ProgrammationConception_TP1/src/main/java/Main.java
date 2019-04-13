import Exercice4.*;

public class Main {
    public static  void main(String[] args) {

        Variable x = new Variable("x", 2.5);
        Variable y = new Variable("y", 4);
        Formula formula1 = new Product(new Sum(x,y), new Sum(x,y));
        Formula formula2 = new Sum(new Sum(x,y), new Sum(x,y));
        Formula formula3 = new Product(x, new Product(y, new Product(x,y)));
        Formula formula4 = new Sum(x, new Sum(y, new Sum(x,y)));

        Formula[] formulas = new Formula[4];
        formulas[0] = formula1;
        formulas[1] = formula2;
        formulas[2] = formula3;
        formulas[3] = formula4;

        Formula sums = new Sum(formulas);
        System.out.println(sums.asString());
        System.out.println(sums.asValue());

        Formula products = new Product(formulas);
        System.out.println(products.asString());
        System.out.println(products.asValue());

        Formula absValue = new AbsoluteValue(formula1);
        System.out.println(absValue.asString());
        System.out.println(absValue.asValue());

        Formula square = new Square(formula1);
        System.out.println(square.asString());
        System.out.println(square.asValue());

    }
}
