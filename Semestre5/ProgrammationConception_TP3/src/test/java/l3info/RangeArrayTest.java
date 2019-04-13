package l3info;


import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RangeArrayTest {



    @Test
    public void RangeArrayIteratorTest() {
        RangeArray<String> habitations = new RangeArray<>(3,5);
        habitations.set(3, "Maison");
        habitations.set(4, "Immeuble");
        habitations.set(5, "Hutte");
        String[] expected = new String[3];
        expected[0] = "Maison";
        expected[1] = "Immeuble";
        expected[2] = "Hutte";
        int index = 0;
        for (String habitation : habitations) {
            assertThat(habitation, equalTo(expected[index]));
            index++;
        }
    }

    @Test
    public void getIndexMin() {
        RangeArray<String> habitations = new RangeArray<>(3,5);
        assertThat(habitations.getIndexMin(), equalTo(3));
    }

    @Test
    public void getIndexMax() {
        RangeArray<String> habitations = new RangeArray<>(3,5);
        assertThat(habitations.getIndexMax(), equalTo(5));
    }

    @Test
    public void set() {
        RangeArray<String> habitations = new RangeArray<>(3,5);
        habitations.set(3, "Maison");
        habitations.set(4, "Immeuble");
        habitations.set(5, "Hutte");
        assertThat(habitations.get(3), equalTo("Maison"));
        assertThat(habitations.get(4), equalTo("Immeuble"));
        assertThat(habitations.get(5), equalTo("Hutte"));
    }

    @Test
    public void get() {
        RangeArray<String> habitations = new RangeArray<>(3,5);
        habitations.set(3, "Maison");
        habitations.set(4, "Immeuble");
        habitations.set(5, "Hutte");
        assertThat(habitations.get(3), equalTo("Maison"));
        assertThat(habitations.get(4), equalTo("Immeuble"));
        assertThat(habitations.get(5), equalTo("Hutte"));
    }

    @Test
    public void rangeSize() {
        RangeArray<String> habitations = new RangeArray<>(3,5);
        assertThat(habitations.rangeSize(), equalTo(3));
    }

    @Test
    public void iterator() {
    }
}
