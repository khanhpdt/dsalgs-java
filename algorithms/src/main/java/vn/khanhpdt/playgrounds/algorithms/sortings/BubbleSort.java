package vn.khanhpdt.playgrounds.algorithms.sortings;

import static vn.khanhpdt.playgrounds.datastructuresalgorithms.commons.Commons.swap;

class BubbleSort<T extends Comparable<T>> implements ComparisonSort<T> {

	@Override
	public void sort(T[] items) {
		int sorted = 0;
		// when (elements.length - 1) elements are sorted, all elements are also sorted
		while (sorted < items.length - 1) {
			// the last sorted elements are already sorted, so exclude them from the sort
			for (int i = 0; i < (items.length - 1) - sorted; i++) {
				if (items[i].compareTo(items[i+1]) > 0) {
					// swap when out of order
					swap(items, i, i + 1);
				}
			}
			sorted++;
		}
	}

}
