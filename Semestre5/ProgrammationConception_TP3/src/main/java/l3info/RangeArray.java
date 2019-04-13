package l3info;

import java.util.Iterator;

public class RangeArray<T> implements Iterable<T> {

    private int indexMin;
    private int indexMax;
    private T[] array;

    /**
     * Construct RangeArray object containing indexMin (int), indexMax (int), array (int[])
     *
     * @param indexMin
     * @param indexMax
     */
    public RangeArray(int indexMin, int indexMax) {
        this.indexMin = indexMin;
        this.indexMax = indexMax;
        this.array = (T[]) new Object[indexMax-indexMin+1];
    }

    /**
     * @return indexMin
     */
    public int getIndexMin() {
        return indexMin;
    }

    /**
     * @return indexMax
     */
    public int getIndexMax() {
        return indexMax;
    }

    /**
     * Set value at index [index-this.indexMin] in array.
     * Prevent negative index. Ex : if indexMin = -2 and index = -1 then index-this.indexMin = -1-(-2) = 1
     *
     * @param index
     * @param value
     */
    public void set(int index, T value) {
        this.array[index-this.indexMin] = value;
    }

    /**
     * @param index
     * @return value at index [index-this.indexMin] in array.
     */
    public T get(int index) {
        return this.array[index-this.indexMin];
    }

    /**
     * @return array size
     */
    public int rangeSize() {
        return this.array.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new RangeArrayIterator();
    }

    public class RangeArrayIterator implements Iterator<T> {

        int position = indexMin - 1;

        @Override
        public boolean hasNext() {
            return position < indexMax;
        }

        @Override
        public T next() {
            position++;
            return get(position);
        }

        @Override
        public void remove() {

        }
    }


}
