package l3info;

import java.util.Iterator;

public class Grid<T> implements Iterable<T> {

    private T[][] elements;

    /**
     * Construct Grid object containing 2D array
     *
     * @param elements Generic 2D array
     */
    public Grid(T[][] elements) {
        this.elements = elements;
    }

    /**
     * Return number of lines in elements
     *
     * @return elements length
     */
    public int nbLines() {
        return this.elements.length;
    }

    /**
     * Return number of columns in elements
     *
     * @return first element length in elements array
     */
    public int nbColumns() {
        return this.elements[0].length;
    }

    /**
     * @param line
     * @param column
     * @return element at given line and column
     */
    public T get(int line, int column) {
        return elements[line][column];
    }

    @Override
    public Iterator<T> iterator() {
        return new GridIterator();
    }

    public class GridIterator implements Iterator<T> {

        int positionLine = 0;
        int positionColumn = -1;

        @Override
        public boolean hasNext() {
            return positionLine < nbLines()-1;
        }

        @Override
        public T next() {
            positionColumn++;
            if (positionColumn >= nbColumns()) {
                positionLine++;
                positionColumn = 0;
            }
            return get(positionLine, positionColumn);
        }

        @Override
        public void remove() {

        }
    }
}
