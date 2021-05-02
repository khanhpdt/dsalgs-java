package vn.khanhpdt.playgrounds.algorithms.sortings;

import java.util.Arrays;
import java.util.function.Function;

class RadixSort implements IntegerSort {

	/**
	 * Worst-case complexity: O(d(n + b)), where d is the number of digits in each integer and b is the base of the
	 * integer number system.
	 */
	@Override
	public int[] sort(int[] numbers) {
		int max = Arrays.stream(numbers).max().getAsInt();

		int nDigits = countDigits(max);

		int[] sorted = numbers;
		for (int i = 1; i <= nDigits; i++) {
			int finalI = i;
			// sort accordingly to the least significant digit first
			Function<Integer, Integer> getDigit = x -> (x % (int) Math.pow(10, finalI)) / (int) Math.pow(10, finalI - 1);
			sorted = CountingSort.sort(sorted, getDigit);
		}

		return sorted;
	}

	private static int countDigits(int n) {
		int result = 0;
		while (n > 0) {
			result++;
			n /= 10;
		}
		return result;
	}

}
