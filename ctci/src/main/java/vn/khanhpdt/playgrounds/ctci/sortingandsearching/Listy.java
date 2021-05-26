package vn.khanhpdt.playgrounds.ctci.sortingandsearching;

import java.util.Random;

/**
 * Problem 10.4 (3rd edition): You are given an array-like data structure Listy which lacks a size
 * method. It does, however, have an elementAt(i) method that returns the element at index i in
 * O(1) time. If i is beyond the bounds of the data structure, it returns -1. (For this reason, the data
 * structure only supports positive integers.) Given a Listy which contains sorted, positive integers,
 * find the index at which an element x occurs. If x occurs multiple times, you may return any index.
 */
public class Listy {

    private int[] elements;

    public int elementAt(int i) {
        return i >= elements.length ? -1 : elements[i];
    }

    public int indexOf(int element) {
        if (elementAt(0) == element) return 0;

        var i = 1;
        while (true) {
            var currentElement = elementAt(i);

            // out of bound
            if (currentElement == -1) {
                break;
            }

            // found
            if (element == currentElement) {
                return i;
            }

            // upper bound found
            if (element < currentElement) {
                break;
            }

            i *= 2;
        }

        return binarySearch(element, Math.floorDiv(i, 2), i);
    }

    private int binarySearch(int element, int from, int to) {
        if (from > to) return -1;

        var mid = from + Math.floorDiv(to - from, 2);

        // mid already out of bound
        if (elementAt(mid) == -1) return binarySearch(element, from, mid - 1);

        if (element == elementAt(mid)) return mid;

        if (element > elementAt(mid)) return binarySearch(element, mid + 1, to);

        return binarySearch(element, from, mid - 1);
    }

    public static void main(String[] args) {
        var listy = new Listy();
        listy.elements = new Random().ints(100, 1, 10000).sorted().toArray();

        if (listy.indexOf(listy.elements[77]) == 77) {
            System.out.println("Passed");
        } else {
            System.out.println("Failed");
        }
    }

}
