package fr.licinfo.structure;

public class AsciiStringFilter implements StringFilter {

    /**
     * Replace all the characters with an ascii code greater than 128 in the string given in argument and return it
     *
     * @param string the string that will be filtered
     * @return a string without the characters with an ascii code greater than 128
     */
    @Override
    public String filter(String string) {

        int ascii_code;
        char special_character;
        for (int i = 0; i < string.length(); i++) {
            ascii_code = string.charAt(i);
            if (ascii_code >= 128) {
                special_character = (char) ascii_code;
                string = string.replace(String.valueOf(special_character), "");
            }
        }
        return string;
    }
}
