package vn.khanhpdt.playgrounds.datastructures.trees;

import vn.khanhpdt.playgrounds.datastructures.Commons;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public abstract class BinaryHeap<T> {

	private List<T> nodes;

	private Comparator<T> nodeComparator;

	private int heapSize;

	// to get index for a given node in constant time
	private Map<T, Integer> indices;

	BinaryHeap(List<T> nodes, Comparator<T> nodeComparator) {
		this.nodes = nodes;
		this.nodeComparator = nodeComparator;
		this.heapSize = nodes.size();

		build();
	}

	private void build() {
		// heap leaves are from (floor(nodes.length/2)) to nodes.length
		int leafStartingIndex = (int) Math.floor(heapSize / 2) + 1;

		// bottom-up heapify: starting from the bottom and from the first node which is not a leaf, makes sure that the
		// children of each node satisfy the heap property, i.e., if the heap is a max-heap, the children can only be
		// smaller than or equal to the node; otherwise, if the heap is a min-heap, the children must be larger than
		// or equal to the node.
		for (int i = leafStartingIndex - 1; i >= 0; i--) {
			heapify(i);
		}
	}

	private void heapify(int indexCurrent) {
		int indexLeftChild = getLeftChildIndexOf(indexCurrent);
		int indexRightChild = getRightChildIndexOf(indexCurrent);

		int heapifiedNodeIndex = getIndexGivenHeapProperty(indexCurrent, indexLeftChild, indexRightChild);

		if (heapifiedNodeIndex == indexCurrent) {
			// no need to heapify the current node
			return;
		}

		// swap the node and its heapified child. so the node floats down.
		swapNodes(indexCurrent, heapifiedNodeIndex);

		// because the node floats down, it might break the heap property in its subtree. so we must continue to
		// heapify the subtree.
		heapify(heapifiedNodeIndex);
	}

	public void swapNodes(int i1, int i2) {
		// keep track of the indices
		if (indices != null) {
			indices.put(getNode(i1), i2);
			indices.put(getNode(i2), i1);
		}
		Commons.swap(nodes, i1, i2);
	}

	private int getRightChildIndexOf(int indexCurrent) {
		return 2 * indexCurrent + 2;
	}

	private int getLeftChildIndexOf(int indexCurrent) {
		return 2 * indexCurrent + 1;
	}

	/**
	 * @return the index of the minimum node if the heap is a min-heap; otherwise, if the node is a max-heap,
	 * return the index of the maximum node.
	 */
	protected abstract int getIndexGivenHeapProperty(int... nodeIndices);

	public List<T> getNodes() {
		return nodes;
	}

	T getNode(int index) {
		return nodes.get(index);
	}

	public int getHeapSize() {
		return heapSize;
	}

	public Comparator<T> getNodeComparator() {
		return nodeComparator;
	}

	public void reduceHeapSizeBy(int reductionSize) {
		heapSize -= reductionSize;
	}

	public void heapifyRoot() {
		heapify(0);
	}

	public Map<T, Integer> getIndices() {
		return indices;
	}

	public void setIndices(Map<T, Integer> indices) {
		this.indices = indices;
	}

	public boolean contains(T node) {
		if (indices != null) {
			Integer nodeIndex = indices.get(node);
			return nodeIndex != null && nodeIndex >= 0 && nodeIndex < heapSize;
		}
		return nodes.stream().anyMatch(n -> n.equals(node));
	}

	public T extractRoot() {
		T root = getNodes().get(0);

		// move root out of the heap, and re-heapify the heap
		swapNodes(0, getHeapSize() - 1);
		reduceHeapSizeBy(1);
		heapify(0);

		return root;
	}


}
