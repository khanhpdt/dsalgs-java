package vn.khanhpdt.playgrounds.algorithms.sortings;

class MergeSort<T extends Comparable<T>> implements ComparisonSort<T> {

	@Override
	public void sort(T[] items) {
		// allocate an auxiliary array once here to avoid allocating multiple arrays during the merge operation
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

		int firstSubArrayIdx = start;
		int secondSubArrayIdx = mid + 1;

		int resultIdx = start;
		while (resultIdx < end) {
			if (auxiliary[firstSubArrayIdx].compareTo(auxiliary[secondSubArrayIdx]) <= 0) {
				elements[resultIdx] = auxiliary[firstSubArrayIdx];
				firstSubArrayIdx++;
			} else {
				elements[resultIdx] = auxiliary[secondSubArrayIdx];
				secondSubArrayIdx++;
			}

			resultIdx++;

			// all elements in the first subarray are merged
			if (firstSubArrayIdx >= mid + 1) {
				// copy all the remaining elements in the second subarray
				System.arraycopy(auxiliary, secondSubArrayIdx, elements, resultIdx, end - secondSubArrayIdx + 1);
				resultIdx = end + 1;
			}

			// all elements in the second subarray are merged
			if (secondSubArrayIdx >= end + 1) {
				// copy all the remaining elements in the first subarray
				System.arraycopy(auxiliary, firstSubArrayIdx, elements, resultIdx, mid - firstSubArrayIdx + 1);
				resultIdx = end + 1;
			}
		}
	}

}
