package vn.khanhpdt.playgrounds.datastructures.nodes;

/**
 * @param <K>  type of node key
 * @param <V>  type of node value
 *
 * @author khanhpdt
 */
public class BinaryTreeNode<K, V extends Comparable<V>>
		implements Comparable<BinaryTreeNode<K, V>>, ForwardLinked<BinaryTreeNode<K, V>> {

	private Node<K, V> content;

	private BinaryTreeNode<K, V> left;

	private BinaryTreeNode<K, V> right;

	private BinaryTreeNode<K, V> parent;

	// for traversing
	private BinaryTreeNode<K, V> next;

	BinaryTreeNode(Node<K, V> nodeContent) {
		this.content = nodeContent;

		initNullNeighbors();
	}

	private void initNullNeighbors() {
		this.left = getNullNode();
		this.right = getNullNode();
		this.parent = getNullNode();
	}

	public V getValue() {
		return content.getValue();
	}

	public BinaryTreeNode<K, V> getLeft() {
		return left;
	}

	public void setLeft(BinaryTreeNode<K, V> left) {
		this.left = left;
	}

	public BinaryTreeNode<K, V> getRight() {
		return right;
	}

	public void setRight(BinaryTreeNode<K, V> right) {
		this.right = right;
	}

	public BinaryTreeNode<K, V> getParent() {
		return parent;
	}

	public void setParent(BinaryTreeNode<K, V> parent) {
		this.parent = parent;
	}

	@Override
	public int compareTo(BinaryTreeNode<K, V> o) {
		return getValue().compareTo(o.getValue());
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
		BinaryTreeNode<K, V> that = (BinaryTreeNode<K, V>) o;
		return getKey().equals(that.getKey());

	}

	@Override
	public int hashCode() {
		int result = 17;
		int c = getKey().hashCode();
		return 31 + result * c;
	}

	public K getKey() {
		return content.getKey();
	}

	@Override
	public BinaryTreeNode<K, V> getNext() {
		return this.next;
	}

	@Override
	public void setNext(BinaryTreeNode<K, V> next) {
		this.next = next;
	}

	public static <K, V extends Comparable<V>> BinaryTreeNode<K, V> from(K key, V value) {
		return new BinaryTreeNode<>(new Node<>(key, value));
	}

	BinaryTreeNode<K, V> getNullNode() {
		throw new UnsupportedOperationException("Not supported yet!");
	}

	public boolean isNull() {
		return this == getNullNode();
	}

	public boolean isNotNull() {
		return !isNull();
	}

	/**
	 * The height of a node is the number of the edges on the longest path from the node to a leaf in the tree.
	 *
	 */
	public int getHeight() {
		// let height of null node be -1 by convention
		if (isNull()) {
			return -1;
		}
		return 1 + Math.max(getLeft().getHeight(), getRight().getHeight());
	}

	/**
	 * The depth of a node is the number of edges on the path from the node to the root of the tree.
	 *
	 */
	public int getDepth() {
		// let depth of null node be -1 by convention
		if (isNull()) {
			return -1;
		}
		return 1 + getParent().getDepth();
	}

}
