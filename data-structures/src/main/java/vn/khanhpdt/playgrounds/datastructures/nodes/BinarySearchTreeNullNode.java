package vn.khanhpdt.playgrounds.datastructures.nodes;

import java.util.UUID;

/**
 * @author khanhpdt
 */
public class BinarySearchTreeNullNode extends BinarySearchTreeNode {

	private static final BinarySearchTreeNullNode INSTANCE = new BinarySearchTreeNullNode(new Node<>(UUID.randomUUID()));

	private BinarySearchTreeNullNode(Node nodeContent) {
		super(nodeContent);
	}

	public static <K, V extends Comparable<V>> BinarySearchTreeNode<K, V> getInstance() {
		return INSTANCE;
	}

	@Override
	public BinarySearchTreeNode getLeft() {
		return getInstance();
	}

	@Override
	public BinarySearchTreeNode getRight() {
		return getInstance();
	}

	@Override
	public BinarySearchTreeNode getParent() {
		return getInstance();
	}
}
