package vn.khanhpdt.playgrounds.datastructures.arrays;

/**
 * <a href="https://en.wikipedia.org/wiki/Dynamic_array">Dynamic array - Wikipedia</a>
 * <p>
 * Key features:
 * <ul>
 *     <li>Random access</li>
 *     <li>Variable-size</li>
 * </ul>
 * <p>
 * In Java, ArrayList is an implementation of dynamic array.
 */
public class DynamicArray<T> {

    private static final double GROWTH_FACTOR = 1.5;

    // use array as backend to enable random access
    private T[] elements;

    private int size = 0;

    public DynamicArray(int capacity) {
        this.elements = (T[]) new Object[capacity];
    }

    public T get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
        return elements[index];
    }

    public void add(T element) {
        // the array is full
        if (size == elements.length) {
            resize();
        }
        elements[size++] = element;
    }

    private void resize() {
        int newCapacity = (int) (elements.length * GROWTH_FACTOR);
        var newElements = (T[]) new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        elements = newElements;
    }

    int size() {
        return size;
    }

    int capacity() {
        return elements.length;
    }

}
