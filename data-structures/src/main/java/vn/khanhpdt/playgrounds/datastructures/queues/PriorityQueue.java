package vn.khanhpdt.playgrounds.datastructures.queues;

import vn.khanhpdt.playgrounds.datastructures.trees.BinaryHeap;

import java.util.Comparator;
import java.util.List;

/**
 * @author khanhpdt
 */
public abstract class PriorityQueue<T> {

	void swapNodes(int i1, int i2) {
		getHeap().swapNodes(i1, i2);
	}

	int getParentIndexOf(int indexCurrent) {
		return (int) Math.ceil((double) indexCurrent/2) - 1;
	}

	int getIndexOf(T node) {
		return getHeap().getIndices().get(node);
	}

	public List<T> getNodes() {
		return getHeap().getNodes();
	}

	T getNode(int index) {
		return getNodes().get(index);
	}

	Comparator<T> getNodeComparator() {
		return getHeap().getNodeComparator();
	}

	public boolean isNotEmpty() {
		return getHeap().getHeapSize() > 0;
	}

	T extractRoot() {
		return getHeap().extractRoot();
	}

	public boolean contains(T node) {
		return getHeap().contains(node);
	}

	protected abstract BinaryHeap<T> getHeap();
}
