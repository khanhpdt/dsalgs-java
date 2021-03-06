package vn.khanhpdt.playgrounds.algorithms.sortings;

class ShellSort<T extends Comparable<T>> implements ComparisonSort<T> {

	private int gapSeed;

	@Override
	public void sort(T[] items) {
		generateGapSeed(items.length);

		int gap;
		do {
			// split the array into multiple subarrays
			// gap is also the number of subarrays
			gap = generateGap();

			// each iteration in this for-loop sorts one subarray
			for (int i = 0; i < gap; i++) {
				// insertion-sort each subarray containing elements separated by gap-position
				for (int j = i + gap; j < items.length; j = j + gap) {
					T element = items[j];

					int k = j - gap;
					while (k >= 0 && element.compareTo(items[k]) < 0) {
						items[k + gap] = items[k];
						k = k - gap;
					}
					items[k + gap] = element;
				}
			}
		}
		// when gap = 1, the whole array will be sorted
		while (gap >= 1);
	}

	/**
	 * @see <a href="https://en.wikipedia.org/wiki/Shellsort#Gap_sequences"></a>
	 */
	private int generateGap() {
		int gap = (int) ((Math.pow(3, gapSeed) - 1) / 2);
		gapSeed--;
		return gap;
	}

	/**
	 * @see <a href="https://en.wikipedia.org/wiki/Shellsort#Gap_sequences"></a>
	 */
	private void generateGapSeed(int nElements) {
		gapSeed = (int) Math.floor(Math.log(2 * Math.ceil(((double) nElements) / 3) + 1) / Math.log(3));
	}

}
