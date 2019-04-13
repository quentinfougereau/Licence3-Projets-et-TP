package filter;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) list.add(i);
        BadFilter filterOdd = new BadFilter(BadFilter.Type.ODD);
        BadFilter filterLeq = new BadFilter(BadFilter.Type.LEQ, 6);
        List<Integer> result = filterLeq.apply(filterOdd.apply(list));
        for (int i : result) System.out.println(i);

        // Les trois blocs suivants sont les versions qui doivent s'éxécuter
        // normalement au fur et à mesure que vous modifiez votre code.
        // Il suffit
        /*
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) list.add(i);
        Filter filterOdd = new Filter(new Odd());
        Filter filterLeq = new Filter(new Leq(6));
        List<Integer> result = filterLeq.apply(filterOdd.apply(list));
        for (int i : result) System.out.println(i);
         */

        /*
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) list.add(i);
        Filter filter = new Filter(new And(new Odd(), new Leq(6)));
        List<Integer> result = filter.apply(list);
        for (int i : result) System.out.println(i);
        */

        /*
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) list.add(i);
        Predicate predicate = new And(new Odd(), new Leq(6));
        List<Integer> result = Filter.filter(predicate, list);
        for (int i : result) System.out.println(i);
        */


    }
}
