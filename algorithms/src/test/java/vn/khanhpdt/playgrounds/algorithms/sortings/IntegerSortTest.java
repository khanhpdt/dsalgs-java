package vn.khanhpdt.playgrounds.algorithms.sortings;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author khanhpdt
 */
public class IntegerSortTest {

	private IntegerSort countingSort = new CountingSort();
	private IntegerSort radixSort = new RadixSort();

	@Test
	public void testCountingSort_1() {
		int[] numbers = {2, 5, 3, 0, 2, 3, 0, 3};

		int[] sorted = countingSort.sort(numbers);

		assertThat(Arrays.equals(sorted, new int[]{0, 0, 2, 2, 3, 3, 3, 5}), is(true));
	}

	@Test
	public void testCountingSort_2() {
		int[] numbers = {4, 2, 5, 7, 3, 0, 9, 12, 7, 2, 3, 0, 3};

		int[] sorted = countingSort.sort(numbers);

		assertThat(Arrays.equals(sorted, new int[]{0, 0, 2, 2, 3, 3, 3, 4, 5, 7, 7, 9, 12}), is(true));
	}

	@Test
	public void testRadixSort_1() {
		int[] numbers = {2, 5, 3, 0, 2, 3, 0, 3};

		int[] sorted = radixSort.sort(numbers);

		assertThat(Arrays.equals(sorted, new int[]{0, 0, 2, 2, 3, 3, 3, 5}), is(true));
	}

	@Test
	public void testRadixSort_2() {
		int[] numbers = {4, 2, 5, 7, 3, 0, 9, 12, 7, 2, 3, 0, 3};

		int[] sorted = radixSort.sort(numbers);

		assertThat(Arrays.equals(sorted, new int[]{0, 0, 2, 2, 3, 3, 3, 4, 5, 7, 7, 9, 12}), is(true));
	}

	@Test
	public void testRadixSort_3() {
		int[] numbers = IntStream.range(0, 101).toArray();
		Collections.shuffle(Arrays.asList(numbers));

		int[] sorted = radixSort.sort(numbers);

		assertThat(Arrays.equals(sorted, IntStream.range(0, 101).toArray()), is(true));
	}

}
