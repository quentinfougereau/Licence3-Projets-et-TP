package l3info;


import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GridTest {

    @Test
    public void GridIteratorTest() {

        String[][] elements = { { "A", "C" },
                                { "B", "D" } };
        Grid<String> grid = new Grid<String>(elements);
        String[] expected = {"A", "C", "B", "D" };
        int index = 0;
        for (String letter : grid) {
            assertThat(letter, equalTo(expected[index]));
            index++;
        }

    }

    @Test
    public void nbLinesTest() {
        String[][] elements = { { "A", "C", "E" },
                                { "B", "D", "F" },
                                { "K", "L", "M"} };
        Grid<String> grid = new Grid<>(elements);
        assertThat(grid.nbLines(), equalTo(3));
    }

    @Test
    public void nbColumnsTest() {
        String[][] elements = { { "A", "C", "E" },
                                { "B", "D", "F" },
                                { "K", "L", "M"} };
        Grid<String> grid = new Grid<>(elements);
        assertThat(grid.nbColumns(), equalTo(3));
    }

}