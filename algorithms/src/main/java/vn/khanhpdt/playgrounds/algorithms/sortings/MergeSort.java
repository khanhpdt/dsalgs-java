package vn.khanhpdt.playgrounds.algorithms.sortings;

class MergeSort<T extends Comparable<T>> implements ComparisonSort<T> {

	@Override
	public void sort(T[] items) {
		T[] auxiliary = items.clone();
		doSort(items, auxiliary, 0, items.length - 1);
	}

	private void doSort(T[] elements, T[] auxiliary, int start, int end) {
		if (start == end) {
			return;
		}

		int mid = start + (end - start) / 2;
		doSort(elements, auxiliary, start, mid);
		doSort(elements, auxiliary, mid + 1, end);
		merge(elements, auxiliary, start, mid, end);
	}

	private void merge(T[] elements, T[] auxiliary, int start, int mid, int end) {
		System.arraycopy(elements, start, auxiliary, start, end + 1 - start);

		int indexFirst = start;
		int indexSecond = mid + 1;

		int indexResult = start;
		while (indexResult < end) {
			if (auxiliary[indexFirst].compareTo(auxiliary[indexSecond]) <= 0) {
				elements[indexResult] = auxiliary[indexFirst];
				indexFirst++;
			} else {
				elements[indexResult] = auxiliary[indexSecond];
				indexSecond++;
			}

			indexResult++;

			// all elements in the first subarray are merged
			if (indexFirst >= mid + 1) {
				// copy all the remaining elements in the second subarray
				System.arraycopy(auxiliary, indexSecond, elements, indexResult, end - indexSecond + 1);
				indexResult = end + 1;
			}

			// all elements in the second subarray are merged
			if (indexSecond >= end + 1) {
				// copy all the remaining elements in the first subarray
				System.arraycopy(auxiliary, indexFirst, elements, indexResult, mid - indexFirst + 1);
				indexResult = end + 1;
			}
		}
	}

}
