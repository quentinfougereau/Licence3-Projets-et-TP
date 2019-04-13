package fr.licinfo.structure;

public class LowerCaseStringFilter implements StringFilter {

    /**
     * Convert characters of a string to lowercase
     *
     * @param string
     * @return string in lower case
     */
    @Override
    public String filter(String string) {
        return string.toLowerCase();
    }

}
