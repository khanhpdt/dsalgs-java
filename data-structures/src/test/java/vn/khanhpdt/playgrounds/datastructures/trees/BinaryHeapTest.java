package vn.khanhpdt.playgrounds.datastructures.trees;

import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BinaryHeapTest {

	@Test
	public void testBuildMaxHeap() {
		List<Integer> nodes = IntStream.range(1, 20).boxed().collect(Collectors.toList());
		Collections.shuffle(nodes);

		BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>(nodes, Integer::compareTo);

		assertThat("max heap property satisfied", checkMaxHeapProperty(heap), is(true));
	}

	private boolean checkMaxHeapProperty(BinaryMaxHeap<Integer> binaryMaxHeap) {
		List<Integer> nodes = binaryMaxHeap.getNodes();
		for (int i = 0; i < nodes.size(); i++) {
			int leftChildIndex = 2 * i + 1;
			if (leftChildIndex < nodes.size() && nodes.get(leftChildIndex).compareTo(nodes.get(i)) > 0) {
				return false;
			}

			int rightChildIndex = 2 * i + 2;
			if (rightChildIndex < nodes.size() && nodes.get(rightChildIndex).compareTo(nodes.get(i)) > 0) {
				return false;
			}
		}
		return true;
	}

	@Test
	public void testBuildMinHeap() {
		List<Integer> nodes = IntStream.range(1, 20).boxed().collect(Collectors.toList());
		Collections.shuffle(nodes);

		BinaryMinHeap<Integer> heap = new BinaryMinHeap<>(nodes, Integer::compare);

		assertThat("min heap property satisfied", checkMinHeapProperty(heap), is(true));
	}

	private boolean checkMinHeapProperty(BinaryMinHeap<Integer> binaryMinHeap) {
		List<Integer> nodes = binaryMinHeap.getNodes();
		for (int i = 0; i < nodes.size(); i++) {
			int leftChildIndex = 2 * i + 1;
			if (leftChildIndex < nodes.size() && nodes.get(leftChildIndex).compareTo(nodes.get(i)) < 0) {
				return false;
			}

			int rightChildIndex = 2 * i + 2;
			if (rightChildIndex < nodes.size() && nodes.get(rightChildIndex).compareTo(nodes.get(i)) < 0) {
				return false;
			}
		}
		return true;
	}

}
