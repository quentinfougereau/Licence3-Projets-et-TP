package filter;

import java.util.ArrayList;
import java.util.List;

class BadFilter {
    public enum Type { ODD, LEQ }
    private Type type;
    private int value;
    public BadFilter(Type type) { this.type = type; }
    public BadFilter(Type type, int value) {
        this.type = type;
        this.value = value;
    }
    public List<Integer> apply(List<Integer> list) {
        List<Integer> result = new ArrayList<Integer>();
        switch (type) {
            case ODD: for (int i : list) if (i%2==1) result.add(i);
                break;
            case LEQ: for (int i : list) if (i<=value) result.add(i);
                break;
        }
        return result;
    }
}