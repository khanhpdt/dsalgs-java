package vn.khanhpdt.playgrounds.datastructures.arrays;

/**
 * <a href="https://en.wikipedia.org/wiki/Circular_buffer">Circular buffer - Wikipedia</a>
 * <p>
 * Features in this implementation:
 * <ul>
 *     <li>Fixed capacity</li>
 *     <li>The data is retrieved in FIFO order.</li>
 *     <li>New items always get added. If buffer is full, old items are overwritten.</li>
 * </ul>
 * </p>
 */
public class CircularBuffer<T> {

    private T[] elements;
    private int head = -1; // points to the newest item
    private int tail = -1; // points to the oldest item

    private int size; // the number of items in the buffer

    public CircularBuffer(int capacity) {
        // fixed capacity
        this.elements = (T[]) new Object[capacity];
    }

    private int capacity() {
        return elements.length;
    }

    public void add(T item) {
        // head moves circularly
        head = increase(head);

        // this will overwrite the oldest element when head == tail
        // alternatively, we can disallow adding new element when the buffer is full.
        elements[head] = item;

        // let tail point to the first item
        if (tail == -1) {
            tail = 0;
        }
        // tail was overwritten
        // in this case, we need to move tail forward to point to the new oldest item.
        else if (head == tail) {
            tail = increase(tail);
        }

        // if size == capacity, the buffer is full and the size is unchanged because the new item is overwriting
        // some existing item
        if (size < capacity()) {
            size++;
        }
    }

    private int increase(int pointer) {
        return (pointer + 1) % elements.length;
    }

    public T remove() {
        if (size == 0) {
            throw new UnsupportedOperationException("Buffer is empty");
        }

        var item = elements[tail];

        if (size > 1) {
            // tail also moves circularly
            tail = increase(tail);
        } else {
            // empty
            tail = -1;
            head = -1;
        }

        size--;

        return item;
    }

    int getHead() {
        return head;
    }

    int getTail() {
        return tail;
    }

    int getSize() {
        return size;
    }
}
