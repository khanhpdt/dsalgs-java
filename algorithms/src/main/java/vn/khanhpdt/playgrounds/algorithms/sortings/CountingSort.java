package vn.khanhpdt.playgrounds.algorithms.sortings;

import java.util.Arrays;
import java.util.function.Function;

class CountingSort implements IntegerSort {

	/**
	 * Worst-case complexity: O(n + k)
	 */
	@Override
	public int[] sort(int[] integers) {
		return sort(integers, Function.identity());
	}

	private static void validate(int[] integers) {
		for (int element : integers) {
			if (element < 0) {
				throw new IllegalArgumentException("Only supports non-negative numbers at the moment");
			}
		}
	}

	public static int[] sort(int[] items, Function<Integer, Integer> keyGetter) {
		int[] keys = Arrays.stream(items).map(keyGetter::apply).toArray();

		validate(keys);

		int max = Arrays.stream(keys).max().getAsInt();
		int min = Arrays.stream(keys).min().getAsInt();

		// count the number of the keys smaller than or equal to a given key
		int[] lessThanOrEqualCounts = new int[max - min + 1];
		for (int key : keys) {
			// first, count equal
			lessThanOrEqualCounts[key - min]++;
		}
		for (int i = 1; i < lessThanOrEqualCounts.length; i++) {
			// then, plus less than keys
			lessThanOrEqualCounts[i] += lessThanOrEqualCounts[i - 1];
		}

		int[] result = new int[items.length];

		// go backward to achieve stability
		for (int i = items.length - 1; i >= 0; i--) {
			int element = items[i];
			int key = keyGetter.apply(element);

			// because of the counting, we're sure that the keys less than or equal to this key
			// will be placed before this position.
			int position = lessThanOrEqualCounts[key - min] - 1;
			result[position] = element;

			// place the other keys equal to this element before this element to preserve stability
			lessThanOrEqualCounts[key - min]--;
		}

		return result;
	}

}