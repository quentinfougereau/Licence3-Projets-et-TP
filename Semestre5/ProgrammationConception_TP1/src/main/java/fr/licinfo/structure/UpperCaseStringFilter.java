package fr.licinfo.structure;

public class UpperCaseStringFilter implements StringFilter {

    /**
     * Convert characters of a string in uppercase
     *
     * @param string
     * @return string in upper case
     */
    @Override
    public String filter(String string) {
        return string.toUpperCase();
    }

}
