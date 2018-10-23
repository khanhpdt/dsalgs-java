package vn.khanhpdt.playgrounds.datastructures.nodes;

import java.awt.Color;
import java.util.UUID;

/**
 * @author khanhpdt
 */
public class RedBlackTreeNullNode extends RedBlackTreeNode {

	private static final RedBlackTreeNullNode INSTANCE = new RedBlackTreeNullNode(new Node<>(UUID.randomUUID()));

	private RedBlackTreeNullNode(Node nodeContent) {
		super(nodeContent, Color.BLACK);
	}

	@Override
	public RedBlackTreeNode getLeft() {
		return INSTANCE;
	}

	@Override
	public RedBlackTreeNode getRight() {
		return INSTANCE;
	}

	@Override
	public RedBlackTreeNode getParent() {
		return INSTANCE;
	}

	@Override
	public RedBlackTreeNode getGrandParent() {
		return INSTANCE;
	}

	@Override
	public void setColor(Color color) {
		// nope
	}

	@Override
	public void setLeft(BinaryTreeNode left) {
		// nope
	}

	@Override
	public void setRight(BinaryTreeNode right) {
		// nope
	}

	@Override
	public void setParent(BinaryTreeNode parent) {
		// nope
	}

	@Override
	public boolean equals(Object o) {
		return o == INSTANCE;
	}

	public static <K, V extends Comparable<V>> RedBlackTreeNode<K, V> getInstance() {
		return INSTANCE;
	}
}
