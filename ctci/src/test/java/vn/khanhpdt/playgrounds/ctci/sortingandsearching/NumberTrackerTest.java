package vn.khanhpdt.playgrounds.ctci.sortingandsearching;

import org.junit.Test;
import vn.khanhpdt.playgrounds.ctci.sortingandsearching.NumberTracker;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author khanhpdt
 */
public class NumberTrackerTest {

	private NumberTracker tracker = new NumberTracker();

	@Test
	public void test_1() {
		int[] numbers = new int[]{1, 4, 3, 2, 7, 6};

		for (int n : numbers) {
			tracker.track(n);
		}

		assertRanks(Arrays.asList(1, 2, 3, 4, 6, 7), Arrays.asList(1, 2, 3, 4, 5, 6));
	}

	private void assertRanks(List<Integer> numbers, List<Integer> ranks) {
		for (int i = 0; i < numbers.size(); i++) {
			assertThat(tracker.rank(numbers.get(i)), is(ranks.get(i)));
		}
	}

	@Test
	public void test_2() {
		tracker.track(1);
		assertRanks(Collections.singletonList(1), Collections.singletonList(1));

		tracker.track(10);
		assertRanks(Arrays.asList(1, 10), Arrays.asList(1, 2));

		tracker.track(4);
		assertRanks(Arrays.asList(1, 4, 10), Arrays.asList(1, 2, 3));

		tracker.track(15);
		assertRanks(Arrays.asList(1, 4, 10, 15), Arrays.asList(1, 2, 3, 4));

		tracker.track(6);
		assertRanks(Arrays.asList(1, 4, 6, 10, 15), Arrays.asList(1, 2, 3, 4, 5));
	}

}