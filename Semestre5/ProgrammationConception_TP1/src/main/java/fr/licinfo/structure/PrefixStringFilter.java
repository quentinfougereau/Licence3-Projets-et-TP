package fr.licinfo.structure;

public class PrefixStringFilter implements StringFilter {

    private int prefixSize;

    /**
     * Construct a prefixStringFilter with a size given by prefixSize
     *
     * @param prefixSize
     */
    public PrefixStringFilter(int prefixSize) {
        this.prefixSize = prefixSize;
    }

    /**
     * Keep the n-first characters of a string
     *
     * @param string
     * @return n-first characters of a string
     */
    @Override
    public String filter(String string) {
        String res = "prefix longer than string length";
        if (this.prefixSize <= string.length())
            res = string.substring(0, this.prefixSize);
        return res;
    }

}
