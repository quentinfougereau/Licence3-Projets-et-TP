package formula;

public interface Formula {

    /**
     * @return formula as a string
     */
    String asString();

    /**
     * @return the result of the formula as a double
     */
    double asValue();

}
