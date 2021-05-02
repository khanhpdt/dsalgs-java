package vn.khanhpdt.playgrounds.datastructures.nodes;

import java.awt.Color;

public class RedBlackTreeNode<K, V extends Comparable<V>> extends BinarySearchTreeNode<K, V> {

	private Color color;

	public RedBlackTreeNode(Node<K, V> nodeContent) {
		super(nodeContent);
	}

	RedBlackTreeNode(Node<K, V> nodeContent, Color color) {
		this(nodeContent);

		this.color = color;
	}

	@Override
	protected RedBlackTreeNode<K, V> getNullNode() {
		return RedBlackTreeNullNode.getInstance();
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	@Override
	public RedBlackTreeNode<K, V> getLeft() {
		return (RedBlackTreeNode<K, V>) super.getLeft();
	}

	@Override
	public RedBlackTreeNode<K, V> getRight() {
		return (RedBlackTreeNode<K, V>) super.getRight();
	}

	@Override
	public RedBlackTreeNode<K, V> getParent() {
		return (RedBlackTreeNode<K, V>) super.getParent();
	}

	public RedBlackTreeNode<K, V> getGrandParent() {
		return getParent().getParent();
	}

}
