package vn.khanhpdt.playgrounds.datastructures.arrays;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CircularBufferTest {

    @Test
    public void testBuffer() {
        var b = new CircularBuffer<Integer>(5);
        assertBuffer(b, -1, -1, 0);

        b.add(1);
        assertBuffer(b, 0, 0, 1);

        b.add(2);
        assertBuffer(b, 1, 0, 2);

        b.add(3);
        assertBuffer(b, 2, 0, 3);

        assertThat(b.remove(), is(1));
        assertBuffer(b, 2, 1, 2);

        assertThat(b.remove(), is(2));
        assertBuffer(b, 2, 2, 1);

        assertThat(b.remove(), is(3));
        assertBuffer(b, -1, -1, 0);

        // 1 (T), 2, 3, 4, 5 (H) (T: tail or oldest, H: head or newest)
        List.of(1, 2, 3, 4, 5).forEach(b::add);
        assertBuffer(b, 4, 0, 5);

        // -, 2 (T), 3, 4, 5 (H)
        assertThat(b.remove(), is(1));
        assertBuffer(b, 4, 1, 4);

        // 6 (H), 2 (T), 3, 4, 5
        b.add(6);
        assertBuffer(b, 0, 1, 5);

        // 6, 7 (H), 3 (T), 4, 5
        b.add(7);
        assertBuffer(b, 1, 2, 5);

        // 6, 7, 8 (H), 4 (T), 5
        b.add(8);
        assertBuffer(b, 2, 3, 5);

        // 6, 7, 8, 9 (H), 5 (T)
        b.add(9);
        assertBuffer(b, 3, 4, 5);

        // 6 (T), 7, 8, 9, 10 (H)
        b.add(10);
        assertBuffer(b, 4, 0, 5);

        // 11 (H), 7 (T), 8, 9, 10
        b.add(11);
        assertBuffer(b, 0, 1, 5);

        // 11 (H), -, 8 (T), 9, 10
        assertThat(b.remove(), is(7));
        assertBuffer(b, 0, 2, 4);

        // 11 (H), -, -, 9 (T), 10
        assertThat(b.remove(), is(8));
        assertBuffer(b, 0, 3, 3);

        // 11 (H), -, -, -, 10 (T)
        assertThat(b.remove(), is(9));
        assertBuffer(b, 0, 4, 2);

        // 11 (H) (T), -, -, -, -
        assertThat(b.remove(), is(10));
        assertBuffer(b, 0, 0, 1);

        // -, -, -, -, -
        assertThat(b.remove(), is(11));
        assertBuffer(b, -1, -1, 0);
    }

    private void assertBuffer(CircularBuffer<Integer> b, int head, int tail, int size) {
        assertThat(b.getHead(), is(head));
        assertThat(b.getTail(), is(tail));
        assertThat(b.getSize(), is(size));
    }
}
