package vn.khanhpdt.playgrounds.datastructures.nodes;

import java.util.function.Function;
import java.util.function.Predicate;

public class BinarySearchTreeNode<K, V extends Comparable<V>> extends BinaryTreeNode<K, V> {

	BinarySearchTreeNode(Node<K, V> nodeContent) {
		super(nodeContent);
	}

	@Override
	BinarySearchTreeNode<K, V> getNullNode() {
		return BinarySearchTreeNullNode.getInstance();
	}

	@Override
	public BinarySearchTreeNode<K, V> getLeft() {
		return (BinarySearchTreeNode<K, V>) super.getLeft();
	}

	@Override
	public BinarySearchTreeNode<K, V> getRight() {
		return (BinarySearchTreeNode<K, V>) super.getRight();
	}

	@Override
	public BinarySearchTreeNode<K, V> getParent() {
		return (BinarySearchTreeNode<K, V>) super.getParent();
	}

	@Override
	public BinarySearchTreeNode<K, V> getNext() {
		return (BinarySearchTreeNode<K, V>) super.getNext();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o == null || this.getClass() != o.getClass()) {
			return false;
		}

		@SuppressWarnings("unchecked")
		BinarySearchTreeNode<K, V> that = (BinarySearchTreeNode<K, V>) o;
		return getKey().equals(that.getKey());
	}

	@Override
	public int hashCode() {
		int result = 17;
		int c = getKey().hashCode();
		return 31 + result * c;
	}

	public static <K, V extends Comparable<V>> BinarySearchTreeNode<K, V> from(K key, V value) {
		return new BinarySearchTreeNode<>(new Node<>(key, value));
	}

	public BinarySearchTreeNode<K, V> getSuccessor() {
		if (getRight().isNotNull()) {
			return getDownwardSuccessor();
		}
		return findUpwardSuccessor();
	}

	public BinarySearchTreeNode<K, V> getPredecessor() {
		if (getLeft().isNotNull()) {
			return findDownwardPredecessor();
		}
		return findUpwardPredecessor();
	}

	public BinarySearchTreeNode<K, V> getDownwardSuccessor() {
		return findMinimumNode(getRight());
	}

	private BinarySearchTreeNode<K, V> findDownwardPredecessor() {
		return findMaximumNode(getLeft());
	}

	private BinarySearchTreeNode<K, V> findMinimumNode(BinarySearchTreeNode<K, V> subtreeRoot) {
		return findOutermostNode(subtreeRoot, BinarySearchTreeNode::getLeft);
	}

	private BinarySearchTreeNode<K, V> findMaximumNode(BinarySearchTreeNode<K, V> subtreeRoot) {
		return findOutermostNode(subtreeRoot, BinarySearchTreeNode::getRight);
	}

	private BinarySearchTreeNode<K, V> findOutermostNode(BinarySearchTreeNode<K, V> from,
	                                                     Function<BinarySearchTreeNode<K, V>, BinarySearchTreeNode<K, V>> nextMove) {
		if (from.isNull()) {
			return getNullNode();
		}

		BinarySearchTreeNode<K, V> current = from;
		BinarySearchTreeNode<K, V> next = nextMove.apply(current);
		while (next.isNotNull()) {
			current = next;
			next = nextMove.apply(current);
		}
		return current;
	}

	private BinarySearchTreeNode<K, V> findUpwardSuccessor() {
		return goUpUntil(upNode -> upNode.getValue().compareTo(getValue()) >= 0);
	}

	private BinarySearchTreeNode<K, V> findUpwardPredecessor() {
		return goUpUntil(upNode -> upNode.getValue().compareTo(getValue()) <= 0);
	}

	private BinarySearchTreeNode<K, V> goUpUntil(Predicate<BinarySearchTreeNode<K, V>> until) {
		BinarySearchTreeNode<K, V> ancestor = getParent();
		while (ancestor.isNotNull()) {
			if (until.test(ancestor)) {
				return ancestor;
			}
			ancestor = ancestor.getParent();
		}
		// none found
		return getNullNode();
	}

}
