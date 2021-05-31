package vn.khanhpdt.playgrounds.datastructures.arrays;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DynamicArrayTest {

    @Test
    public void testWithinCapacity() {
        var arr = new DynamicArray<Integer>(10);
        assertThat(arr.capacity(), is(10));

        for (int i = 0; i < arr.capacity(); i++) {
            arr.add(i);
        }

        assertThat(arr.size(), is(10));
    }

    @Test
    public void testResizeArray() {
        var arr = new DynamicArray<Integer>(10);
        assertThat(arr.capacity(), is(10));

        for (int i = 0; i < 11; i++) {
            arr.add(i);
        }
        assertThat(arr.capacity(), is(15));
        assertThat(arr.size(), is(11));

        for (int i = 0; i < 5; i++) {
            arr.add(i);
        }
        assertThat(arr.capacity(), is(22));
        assertThat(arr.size(), is(16));
    }

}
