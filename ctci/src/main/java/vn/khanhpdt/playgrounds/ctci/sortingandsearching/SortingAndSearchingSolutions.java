package vn.khanhpdt.playgrounds.ctci.sortingandsearching;

import java.util.*;

class SortingAndSearchingSolutions {

    /**
     * Problem 11.1: You are given two sorted arrays, A and B, where A has a large enough buffer at the
     * end to hold B. Write a method to merge B into A in sorted order.
     *
     * @param a sorted array, has enough empty spaces at the end to store b
     * @param b sorted array
     */
    static void mergeSortedArrays(int[] a, int[] b) {
        // shift all elements in a to the right
        int nElementsA = a.length - b.length;
        System.arraycopy(a, 0, a, b.length, nElementsA);

        // merge in-place.
        int i = b.length; // since a's elements were shifted to the right.
        int j = 0;
        for (int k = 0; k < a.length; k++) {
            // out of a's items
            if (i == a.length) {
                a[k] = b[j];
                j++;
            }
            // out of b's items
            else if (j == b.length) {
                a[k] = a[i];
                i++;
            } else {
                // smaller first
                if (a[i] < b[j]) {
                    a[k] = a[i];
                    i++;
                } else {
                    a[k] = b[j];
                    j++;
                }
            }
        }
    }

    /**
     * Problem 11.1
     * <p>Solution 2: Merge two arrays from their backs, so that we don't need to shift the elements in a.</p>
     */
    static void mergeSortedArrays_2(int[] a, int[] b) {
        final int nElementsA = a.length - b.length;
        final int nElementsB = b.length;

        int i = nElementsA - 1, j = nElementsB - 1, k = a.length - 1;
        while (i >= 0 && j >= 0) {
            // put larger numbers at the end of the array
            if (a[i] > b[j]) {
                a[k] = a[i];
                i--;
            } else {
                a[k] = b[j];
                j--;
            }

            k--;
        }

        // in case all elements in a are merged but some of b are not
        while (j >= 0) {
            a[k] = b[j];
            j--;
        }
    }

    /**
     * Problem 11.2: Write a method to sort an array ot strings so that all the anagrams are next to each other.
     * Two strings are anagrams if they contain the same characters and the number of those characters.
     *
     * <p>Solution 2: Use sorted anagram as a key. This actually uses the idea from bucket sort.</p>
     */
    static List<List<String>> clusterAnagrams_2(List<String> strings) {
        Map<String, List<String>> sortedStrings = new HashMap<>();
        strings.forEach(s -> {
            char[] sortedChars = s.toCharArray();
            Arrays.sort(sortedChars);
            String sortedString = String.valueOf(sortedChars);

            sortedStrings.putIfAbsent(sortedString, new ArrayList<>());
            sortedStrings.get(sortedString).add(s);
        });

        return new ArrayList<>(sortedStrings.values());
    }

    /**
     * Problem 11.3: Given a sorted array of n integers that has been rotated an unknown
     * number of times, write code to find an element in the array. You may assume that the array was
     * originally sorted in increasing order.
     *
     * @param numbers a rotated of a sorted array
     */
    static int findInRotatedArray(int[] numbers, int n) {
        int indexOfSmallestItem = -1;
        int i = 0;

        // O(N), where N is the length of numbers
        while (indexOfSmallestItem == -1 && i < numbers.length - 1) {
            // micro optimization: exploit the search for the smallest number to find the given number
            if (numbers[i] == n) {
                return i;
            }

            if (numbers[i] > numbers[i + 1]) {
                indexOfSmallestItem = i + 1;
            }

            i++;
        }

        // because indexSmallest == -1 here means no rotation, meaning that the given number must have been found
        // in the loop if it is in the array.
        if (indexOfSmallestItem == -1) {
            throw new IllegalArgumentException(n + " not found.");
        }

        // the array is structured like this:
        // [the rotated part (all items in this part >= the items in other parts), the smallest item, the part not rotated]

        if (n >= numbers[0]) {
            // n must be in the rotated part
            return binarySearch(n, numbers, 0, indexOfSmallestItem - 1);
        } else {
            // n must in the not rotated part
            return binarySearch(n, numbers, indexOfSmallestItem, numbers.length - 1);
        }
    }

    private static int binarySearch(int n, int[] numbers, int from, int to) {
        while (from <= to) {
            int middle = (from + to) / 2;
            if (n == numbers[middle]) {
                return middle;
            }
            if (n > numbers[middle]) {
                from = middle + 1;
            } else {
                to = middle - 1;
            }
        }

        throw new IllegalArgumentException(n + " not found.");
    }

    /**
     * Problem 11.3.
     * <p>
     * O(log(N)) solution.
     */
    static int findInRotatedArray_2(int[] numbers, int n) {
        return findInRotatedArray_2(numbers, n, 0, numbers.length - 1);
    }

    static int findInRotatedArray_2(int[] numbers, int n, int from, int to) {
        if (from > to) return -1;

        if (from == to) {
            if (n != numbers[from]) return -1;
            return from;
        }

        var mid = (from + to) / 2;

        if (numbers[mid] == n) return mid;

        // the left half must be in increasing order
        // e.g., [4, 5, 6, 7, 8 (mid), 9, 1, 2, 3]
        if (numbers[mid] > numbers[from]) {
            if (n >= numbers[from] && n < numbers[mid]) return findInRotatedArray_2(numbers, n, from, mid - 1);
            return findInRotatedArray_2(numbers, n, mid + 1, to);
        }

        // the right half must be in increasing order (it's impossible otherwise)
        // e.g., [7, 8, 9, 1, 2 (mid), 3, 4, 5, 6]
        if (numbers[mid] < numbers[from]) {
            if (n > numbers[mid] && n <= numbers[to]) return findInRotatedArray_2(numbers, n, mid + 1, to);
            return findInRotatedArray_2(numbers, n, from, mid - 1);
        }

        // numbers[mid] == numbers[from]

        // special case. e.g., [3, 3, 3, 4, 3], [3, 2, 3, 3, 3], [3, 3, 3, 2, 3]
        // we must search both size, b/c we cannot know which half n can be (see the examples above)
        if (numbers[mid] == numbers[to]) {
            var fromLeft = findInRotatedArray_2(numbers, n, from + 1, mid - 1);
            if (fromLeft == -1) {
                return findInRotatedArray_2(numbers, n, mid + 1, to - 1);
            }
            return fromLeft;
        }

        // e.g., [3, 3, 3, 4, 5], [3, 3, 3, 1, 2]
        return findInRotatedArray_2(numbers, n, mid + 1, to);
    }

    /**
     * Problem 11.5: Given a sorted array of strings that is interspersed with empty strings, write a
     * method to find the location of a given string.
     *
     * @param strings a sorted string array interspersed with empty strings
     */
    static int findInInterspersedArray(String[] strings, String s) {
        int from = 0;
        int to = strings.length - 1;
        while (from <= to) {
            final int middle = (from + to) / 2;

            // move up until a non-empty string found
            int nonEmptyStringMiddle = middle;
            while ("".equals(strings[nonEmptyStringMiddle]) && nonEmptyStringMiddle <= to) {
                nonEmptyStringMiddle++;
            }
            // no non-empty string found upward
            if (nonEmptyStringMiddle > to) {
                // move down until a non-empty string found
                nonEmptyStringMiddle = middle - 1;
                while ("".equals(strings[nonEmptyStringMiddle]) && nonEmptyStringMiddle >= from) {
                    nonEmptyStringMiddle--;
                }
            }
            // NOTE: we can also move into the two directions at the same time to avoid the bad case when the non-empty
            // string is not in the upward but in the downward direction

            // no non-empty string found downward
            if (nonEmptyStringMiddle < from) {
                throw new IllegalArgumentException("All strings empty.");
            }

            // binary search
            if (s.equals(strings[nonEmptyStringMiddle])) {
                return nonEmptyStringMiddle;
            }
            if (s.compareTo(strings[nonEmptyStringMiddle]) > 0) {
                from = nonEmptyStringMiddle + 1;
            } else {
                to = nonEmptyStringMiddle - 1;
            }
        }

        throw new IllegalArgumentException(s + " not found.");
    }

    /**
     * Problem 11.6: Given an M x N matrix in which each row and each column is sorted in
     * ascending order, write a method to find an element.
     *
     * @param matrix a matrix whose columns and rows are sorted in ascending order
     */
    static int[] findInSortedMatrix(int[][] matrix, int n) {
        return findInSortedMatrix(n, matrix, 0, matrix.length - 1, 0, matrix[0].length - 1);
    }

    private static int[] findInSortedMatrix(int n, int[][] matrix, int rowFrom, int rowTo, int colFrom, int colTo) {
        if (rowFrom > rowTo || colFrom > colTo) {
            // not found
            return null;
        }

        int middleRow = (rowFrom + rowTo) / 2;
        int middleCol = (colFrom + colTo) / 2;

        if (n == matrix[middleRow][middleCol]) {
            return new int[]{middleRow, middleCol};
        }

        int[] indexes;
        if (n > matrix[middleRow][middleCol]) {
            // right
            indexes = findInSortedMatrix(n, matrix, middleRow, middleRow, middleCol + 1, colTo);
            if (indexes == null) {
                // lower right
                indexes = findInSortedMatrix(n, matrix, middleRow + 1, rowTo, middleCol, colTo);
            }
        } else {
            // left
            indexes = findInSortedMatrix(n, matrix, middleRow, middleRow, colFrom, middleCol - 1);
            if (indexes == null) {
                // upper left
                indexes = findInSortedMatrix(n, matrix, rowFrom, middleRow - 1, colFrom, middleCol);
            }
        }
        // search in these areas in both cases (less and greater than), because we cannot know their relationships
        // with matrix[middleRow][middleCol]
        if (indexes == null) {
            // upper right
            indexes = findInSortedMatrix(n, matrix, rowFrom, middleRow - 1, middleCol + 1, colTo);
            if (indexes == null) {
                // lower left
                indexes = findInSortedMatrix(n, matrix, middleRow + 1, rowTo, colFrom, middleCol - 1);
            }
        }

        return indexes;
    }

    /**
     * Problem 11.7
     */
    static int circusTowerMaxSize(int[][] people) {
        sortByHeight(people);
        return lengthLongestWeightSequenceInOrder(people);
    }

    private static int lengthLongestWeightSequenceInOrder(int[][] people) {
        int longest = 0;
        for (int i = 0; i < people.length - 1; i++) {
            int thisWeight = people[i][1];
            int length = 1;
            for (int j = i + 1; j < people.length; j++) {
                int otherWeight = people[j][1];
                if (thisWeight < otherWeight) length++;
            }
            if (length > longest) longest = length;
        }
        return longest;
    }

    private static void sortByHeight(int[][] people) {
        List<int[]> peopleList = Arrays.asList(people);
        Collections.sort(peopleList, (p1, p2) -> p1[0] - p2[0]);
    }
}
