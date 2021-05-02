package vn.khanhpdt.playgrounds.algorithms.sortings;

/**
 * @author khanhpdt
 */
class InsertionSort<T extends Comparable<T>> implements ComparisonSort<T> {

	@Override
	public void sort(T[] items) {
		// start from the 1st item b/c the subarray containing the first item is already sorted
		for (int i = 1; i < items.length; i++) {
			T element = items[i];

			// find the right position in sorted part [0, i-1] to insert the current ith element
			int j = i - 1;
			while (j >= 0 && element.compareTo(items[j]) < 0) {
				j--;
			}

			// the current element is already at the right position
			if (j == i - 1) {
				continue;
			}

			// when j >= 0, the current element >= items[j] and should be placed at j + 1.
			// when j == -1, the current element < items[0] and should be placed at 0.
			// in either case, the element should be placed at j + 1.

			// in-place placing the current element at position (j + 1)
			// first, shift items [j + 1, i - 1] to override [j + 2, i]
			// (alternative: System.arraycopy(items, j + 1, items, j + 2, (i - (j + 1)));)
			for (int k = i; k > j + 1; k--) {
				items[k] = items[k - 1];
			}
			// then, place the element at (j + 1) position
			items[j + 1] = element;
		}
	}

}
