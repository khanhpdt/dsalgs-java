package vn.khanhpdt.playgrounds.ctci.sortingandsearching;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem 11.8
 *
 * @author khanhpdt
 */
class NumberTracker {

	/**
	 * <p>Count the generated numbers. This makes tracking in O(1) but ranking in O(n), where n is the number of generated
	 * numbers.</p>
	 * <p>If ranking has higher priority than tracking, this map should be used to store the rank of each number.
	 * Then tracking takes O(n), whereas ranking takes O(1).</p>
	 * <p>We can also use binary search tree to store the generated numbers, then both tracking and ranking needs O(logn).</p>
	 *
	 */
	private Map<Integer, Integer> numberCounts = new HashMap<>();

	void track(int n) {
		numberCounts.putIfAbsent(n, 0);
		numberCounts.put(n, numberCounts.get(n) + 1);
	}

	int rank(int n) {
		int r = 0;
		for (Map.Entry<Integer, Integer> entry : numberCounts.entrySet()) {
			if (entry.getKey() <= n) {
				r += entry.getValue();
			}
		}
		return r;
	}

}
