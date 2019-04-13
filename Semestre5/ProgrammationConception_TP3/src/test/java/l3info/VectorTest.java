package l3info;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.junit.Test;

public class VectorTest {

    @Test
    public void testVectorInt() {
        Vector vector = new Vector(123);
        assertThat(vector.capacity(), equalTo(123));
        assertThat(vector.size(), equalTo(0));
    }

    @Test
    public void testVector() {
        Vector vector = new Vector();
        assertThat(vector.capacity(), equalTo(10));
        assertThat(vector.size(), equalTo(0));
    }

    @Test
    public void testEnsureCapacity_CapacityDoubled() {
        Vector vector = new Vector(23);
        vector.ensureCapacity(24);
        assertThat(vector.capacity(), greaterThanOrEqualTo(23*2));
    }

    @Test
    public void testEnsureCapacity_CapacitySatified() {
        Vector vector = new Vector(23);
        vector.ensureCapacity(120);
        assertThat(vector.capacity(), equalTo(120));
    }

    @Test
    public void testEnsureCapacity_CapacityAlwaysIncreased() {
        Vector vector = new Vector(120);
        vector.ensureCapacity(10);
        assertThat(vector.capacity(), equalTo(120));
    }

    @Test
    public void testResize() {
        Vector vector = new Vector();
        vector.resize(120);
        assertThat(vector.size(), equalTo(120));
    }

    @Test
    public void testResize_Zeros() {
        Vector vector = new Vector(1);
        vector.add(2);
        vector.resize(0);
        vector.resize(1);
        assertThat(vector.get(0), Matchers.<Object>equalTo(2));
    }

    @Test
    public void testResize_CapacityIncreased() {
        Vector vector = new Vector(10);
        vector.resize(20);
        vector.resize(100);
        assertThat(vector.capacity(), greaterThanOrEqualTo(vector.size()));
    }

    @Test
    public void testResize_CapacityAlwaysIncreased() {
        Vector vector = new Vector(10);
        vector.resize(100);
        vector.resize(20);
        assertThat(vector.capacity(), greaterThanOrEqualTo(vector.size()));
    }

    @Test
    public void testIsEmpty() {
        Vector vector = new Vector();
        assertThat(vector.isEmpty(), equalTo(true));
        vector.resize(12);
        assertThat(vector.isEmpty(), equalTo(false));
    }

    @Test
    public void testAdd() {
        Vector vector = new Vector();
        vector.add(12);
        vector.add(13);
        vector.add(10);
        assertThat(vector.size(), equalTo(3));
        assertThat(vector.get(0), Matchers.<Object>equalTo(12));
        assertThat(vector.get(1), Matchers.<Object>equalTo(13));
        assertThat(vector.get(2), Matchers.<Object>equalTo(10));
        vector.resize(1);
        vector.add(2);
        assertThat(vector.get(0), Matchers.<Object>equalTo(12));
        assertThat(vector.get(1), Matchers.<Object>equalTo(2));
    }

    @Test
    public void testSet() {
        Vector vector = new Vector();
        vector.add(12);
        vector.add(13);
        vector.add(10);
        vector.set(0, 2);
        vector.set(2, 4);
        assertThat(vector.get(0), Matchers.<Object>equalTo(2));
        assertThat(vector.get(2), Matchers.<Object>equalTo(4));
    }

    @Test
    public void testGet() {
        Vector vector = new Vector();
        vector.add(12);
        vector.add(13);
        assertThat(vector.get(0), Matchers.<Object>equalTo(12));
        assertThat(vector.get(1), Matchers.<Object>equalTo(13));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSet_NegativeIndex() {
        Vector vector = new Vector();
        vector.set(-1, 3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSet_TooGreatIndex() {
         Vector vector = new Vector();
         vector.set(11, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGet_NegativeIndex() {
        Vector vector = new Vector();
        vector.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGet_TooGreatIndex() {
        Vector vector = new Vector();
        vector.get(11);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testResize_NegativeSize() {
        Vector vector = new Vector();
        vector.resize(-99);
    }

}