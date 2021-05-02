package vn.khanhpdt.playgrounds.datastructures.trees;

import vn.khanhpdt.playgrounds.datastructures.nodes.BinarySearchTreeNode;
import vn.khanhpdt.playgrounds.datastructures.nodes.BinarySearchTreeNullNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinarySearchTree<K, V extends Comparable<V>> {

	private BinarySearchTreeNode<K, V> root;

	public BinarySearchTree() {
		this.root = getNullNode();
	}

	public BinarySearchTree(BinarySearchTreeNode<K, V> root) {
		this.root = root;
	}

	void setRoot(BinarySearchTreeNode<K, V> root) {
		this.root = root;
	}

	public BinarySearchTreeNode<K, V> getRoot() {
		return root;
	}

	/**
	 * Inserts the new node as a leaf of this tree and still preserves the BST property.
	 *
	 */
	public BinarySearchTreeNode<K, V> insert(BinarySearchTreeNode<K, V> node) {
		// first node
		if (getRoot().isNull()) {
			setRoot(node);
			getRoot().setLeft(getNullNode());
			getRoot().setRight(getNullNode());
			getRoot().setParent(getNullNode());
			return getRoot();
		}

		// add new node to appropriate place to preserve the BST property
		BinarySearchTreeNode<K, V> parent = findParentOfNewNode(node);
		if (parent.compareTo(node) >= 0) {
			parent.setLeft(node);
		} else {
			parent.setRight(node);
		}
		node.setParent(parent);

		return node;
	}

	BinarySearchTreeNode<K, V> getNullNode() {
		return BinarySearchTreeNullNode.getInstance();
	}

	private BinarySearchTreeNode<K, V> findParentOfNewNode(BinarySearchTreeNode<K, V> newNode) {
		BinarySearchTreeNode<K, V> parent = getRoot();
		BinarySearchTreeNode<K, V> current = getRoot();
		while (current.isNotNull()) {
			parent = current;
			if (newNode.compareTo(current) > 0) {
				current = current.getRight();
			} else {
				current = current.getLeft();
			}
		}
		return parent;
	}

	public void remove(K nodeKey) {
		remove(findNodeByKey(nodeKey));
	}

	public void remove(V nodeValue) {
		remove(findNodeByValue(nodeValue));
	}

	private void remove(BinarySearchTreeNode<K, V> removedNode) {
		// no node found with the given key
		if (removedNode.isNull()) {
			return;
		}

		// this node will replace the removed node
		BinarySearchTreeNode<K, V> replacingNode;

		// the removed node has two children
		if (removedNode.getLeft().isNotNull() && removedNode.getRight().isNotNull()) {
			replacingNode = findDownwardSuccessorOf(removedNode);

			// because the replacing node is the successor of the removed node, it is either the right child of the
			// removed node or the left-most node of the right subtree of the removed node.
			replacingNode.setLeft(removedNode.getLeft());
			removedNode.getLeft().setParent(replacingNode);
			if (!replacingNode.equals(removedNode.getRight())) {
				transplantParent(replacingNode, replacingNode.getRight());
				replacingNode.setRight(removedNode.getRight());
				removedNode.getRight().setParent(replacingNode);
			}
			transplantParent(removedNode, replacingNode);
		}
		// the removed node has only one child or none at all
		else {
			replacingNode = removedNode.getLeft().isNotNull() ? removedNode.getLeft() : removedNode.getRight();
			transplantParent(removedNode, replacingNode);
		}
	}

	/**
	 * Makes the parent of fromNode become the parent of toNode and removes the link to fromNode from its parent.
	 *
	 */
	private void transplantParent(BinarySearchTreeNode<K, V> fromNode, BinarySearchTreeNode<K, V> toNode) {
		BinarySearchTreeNode<K, V> parent = fromNode.getParent();
		// fromNode is the root
		if (parent.isNull()) {
			setRoot(toNode);
		}
		// fromNode is the left child
		else if (fromNode.equals(parent.getLeft())) {
			parent.setLeft(toNode);
		}
		// fromNode is the right child
		else {
			parent.setRight(toNode);
		}

		if (toNode.isNotNull()) {
			toNode.setParent(parent);
		}
	}

	protected BinarySearchTreeNode<K, V> findNodeByKey(K nodeKey) {
		List<BinarySearchTreeNode<K, V>> nodes = traverseInOrder();
		return nodes.stream().filter(n -> n.getKey().equals(nodeKey)).findFirst().orElse(getNullNode());
	}

	List<BinarySearchTreeNode<K, V>> traverseInOrder() {
		return traverseInOrder(getRoot());
	}

	private List<BinarySearchTreeNode<K, V>> traverseInOrder(BinarySearchTreeNode<K, V> sourceNode) {
		if (sourceNode.isNull()) {
			return Collections.emptyList();
		}

		// in-order traversal
		List<BinarySearchTreeNode<K, V>> result = new ArrayList<>();
		result.addAll(traverseInOrder(sourceNode.getLeft()));
		result.add(sourceNode);
		result.addAll(traverseInOrder(sourceNode.getRight()));

		return result;
	}

	public BinarySearchTreeNode<K, V> findNodeByValue(V value) {
		BinarySearchTreeNode<K, V> current = getRoot();
		while (current.isNotNull()) {
			if (current.getValue().equals(value)) {
				return current;
			}
			if (current.getValue().compareTo(value) < 0) {
				current = current.getRight();
			} else {
				current = current.getLeft();
			}
		}
		// none found
		return getNullNode();
	}

	public BinarySearchTreeNode<K, V> findSuccessorOf(BinarySearchTreeNode<K, V> node) {
		return node.getSuccessor();
	}

	public BinarySearchTreeNode<K, V> findPredecessorOf(BinarySearchTreeNode<K, V> node) {
		return node.getPredecessor();
	}

	BinarySearchTreeNode<K, V> findDownwardSuccessorOf(BinarySearchTreeNode<K, V> node) {
		return node.getDownwardSuccessor();
	}

	public int getHeight() {
		return getRoot().getHeight();
	}

	public int size() {
		return traverseInOrder().size();
	}

}
