package fr.licinfo.structure;

public class PostfixStringFilter implements StringFilter {

    private int postfixSize;

    /**
     * Construct a postfixStringFilter with a size given by postfixSize
     *
     * @param postfixSize
     */
    public PostfixStringFilter(int postfixSize) {
        this.postfixSize = postfixSize;
    }

    /**
     * Keep the n-last characters of a string
     *
     * @param string
     * @return n-last characters of a string
     */
    @Override
    public String filter(String string) {
        String res = "postfix size greater than string length";
        if (postfixSize == 0)
            res = string;
        else if (this.postfixSize <= string.length()) {
            String backwardWord = "";
            for (int i = string.length() - 1; i > postfixSize; i--) {
                backwardWord += string.charAt(i);
            }
            res = "";
            for (int i = backwardWord.length() - 1; i >= 0; i--) {
                res += backwardWord.charAt(i);
            }
        }
        return res;
    }
}
