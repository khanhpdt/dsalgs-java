package vn.khanhpdt.playgrounds.datastructures.queues;

import vn.khanhpdt.playgrounds.datastructures.trees.BinaryHeap;
import vn.khanhpdt.playgrounds.datastructures.trees.BinaryMinHeap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @param <T> node type
 */
public class MinPriorityQueue<T> extends PriorityQueue<T> {

	private final BinaryMinHeap<T> minHeap;

	public MinPriorityQueue(List<T> nodes, Comparator<T> nodeComparator) {
		minHeap = new BinaryMinHeap<>(new ArrayList<>(nodes), nodeComparator);

		minHeap.setIndices(
				IntStream.range(0, getNodes().size())
						.boxed()
						.collect(Collectors.toMap(this::getNode, Function.identity())));
	}

	@Override
	protected BinaryHeap<T> getHeap() {
		return minHeap;
	}

	public <K> void decreaseKey(T node, K key, BiConsumer<T, K> keySetter) {
		keySetter.accept(node, key);

		// re-order because decreasing key can cause the current node and its parent out of order
		int nodeIndex = getIndexOf(node);
		int parentIndex = getParentIndexOf(nodeIndex);
		while (parentIndex >= 0 && getNodeComparator().compare(getNode(nodeIndex), getNode(parentIndex)) < 0) {
			// re-order
			swapNodes(nodeIndex, parentIndex);

			// keep going up to check for the heap property
			nodeIndex = parentIndex;
			parentIndex = getParentIndexOf(nodeIndex);
		}
	}

	public T extractMin() {
		return extractRoot();
	}
}
