package fr.licinfo.structure;

public interface StringFilter {

    String filter(String string);

    /**
     * Apply filter on all strings in array "strings" given in argument
     *
     * @param strings
     * @param filter
     * @return an array of filtered strings
     */
    static String[] filter(String[] strings, StringFilter filter) {
        int index = 0;
        for (String string : strings) {
            strings[index] = filter.filter(string);
            index++;
        }
        return strings;
    }

}
