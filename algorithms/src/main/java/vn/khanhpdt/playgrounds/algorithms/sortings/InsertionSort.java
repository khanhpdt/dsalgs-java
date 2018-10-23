package vn.khanhpdt.playgrounds.algorithms.sortings;

/**
 * @author khanhpdt
 */
class InsertionSort<T extends Comparable<T>> implements ComparisonSort<T> {

	@Override
	public void sort(T[] items) {
		for (int i = 0; i < items.length; i++) {
			T element = items[i];

			int j = i - 1;

			// shift the elements on the left of i one position to their right, until the right place to insert
			// the element is found
			while (j >= 0 && element.compareTo(items[j]) < 0) {
				items[j + 1] = items[j];
				j--;
			}

			// insert
			items[j + 1] = element;
		}
	}

}
