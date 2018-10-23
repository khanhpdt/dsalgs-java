package vn.khanhpdt.playgrounds.datastructures.trees;

import vn.khanhpdt.playgrounds.datastructures.nodes.BinarySearchTreeNode;
import vn.khanhpdt.playgrounds.datastructures.nodes.RedBlackTreeNode;
import vn.khanhpdt.playgrounds.datastructures.nodes.RedBlackTreeNullNode;

import static java.awt.Color.BLACK;
import static java.awt.Color.RED;

/**
 * @author khanhpdt
 */
class RedBlackTree<K, V extends Comparable<V>> extends BinarySearchTree<K, V> {

	private RedBlackTreeNode<K, V> root = getNullNode();

	@Override
	RedBlackTreeNode<K, V> getNullNode() {
		return RedBlackTreeNullNode.getInstance();
	}

	// see [1, subsection 13.3] for details
	public RedBlackTreeNode<K, V> insert(RedBlackTreeNode<K, V> node) {
		// do normal insert as in binary search tree
		RedBlackTreeNode<K, V> newNode = (RedBlackTreeNode<K, V>) super.insert(node);

		newNode.setColor(RED);

		// to preserve the red-black properties
		fixUpAfterInsert(newNode);

		return newNode;
	}

	private void fixUpAfterInsert(RedBlackTreeNode<K, V> node) {
		// first node
		if (getRoot().equals(node)) {
			// the root of a red-black tree must be black
			getRoot().setColor(BLACK);
			return;
		}

		// no need to re-arrange the node if its parent is black.
		while (node.getParent().getColor() == RED) {
			if (node.getParent().equals(node.getGrandParent().getLeft())) {
				RedBlackTreeNode<K, V> uncle = node.getGrandParent().getRight();

				// case 1
				if (uncle.getColor() == RED) {
					node.getParent().setColor(BLACK);
					uncle.setColor(BLACK);
					node.getGrandParent().setColor(RED);

					node = node.getGrandParent();
				}
				// node's uncle is black.
				// note that since node's parent is red, its grandparent must be black due to property 4.
				else {
					// case 2
					if (node.getParent().getRight().equals(node)) {
						rotateLeft(node.getParent(), node);
						// transform to case 3
						node = node.getLeft();
					}

					// case 3
					node.getParent().setColor(BLACK);
					rotateRight(node.getGrandParent(), node.getParent());
					node.getParent().getRight().setColor(RED);
				}
			}
			// node's parent is the right child
			else {
				RedBlackTreeNode<K, V> uncle = node.getGrandParent().getLeft();

				// case 1
				if (uncle.getColor() == RED) {
					node.getParent().setColor(BLACK);
					uncle.setColor(BLACK);
					node.getGrandParent().setColor(RED);

					node = node.getGrandParent();
				}
				// node's uncle is black.
				// note that since node's parent is red, its grandparent must be black due to property 4.
				else {
					// case 2
					if (node.getParent().getLeft().equals(node)) {
						rotateRight(node.getParent(), node);
						// transform to case 3
						node = node.getRight();
					}

					// case 3
					node.getParent().setColor(BLACK);
					rotateLeft(node.getGrandParent(), node.getParent());
					node.getParent().getLeft().setColor(RED);
				}
			}
		}

		// always keep the root black
		getRoot().setColor(BLACK);
	}

	private void rotateLeft(RedBlackTreeNode<K, V> parent, RedBlackTreeNode<K, V> node) {
		transplantParent(parent, node);

		RedBlackTreeNode<K, V> nodeLeftChild = node.getLeft();
		node.setLeft(parent);
		parent.setParent(node);

		parent.setRight(nodeLeftChild);
		nodeLeftChild.setParent(parent);
	}

	private void rotateRight(RedBlackTreeNode<K, V> parent, RedBlackTreeNode<K, V> node) {
		transplantParent(parent, node);

		RedBlackTreeNode<K, V> nodeRightChild = node.getRight();
		node.setRight(parent);
		parent.setParent(node);

		parent.setLeft(nodeRightChild);
		nodeRightChild.setParent(parent);
	}

	@Override
	protected void setRoot(BinarySearchTreeNode<K, V> root) {
		this.root = (RedBlackTreeNode<K, V>) root;
	}

	@Override
	public RedBlackTreeNode<K, V> getRoot() {
		return this.root;
	}

	@Override
	public RedBlackTreeNode<K, V> findNodeByValue(V value) {
		return (RedBlackTreeNode<K, V>) super.findNodeByValue(value);
	}

	@Override
	protected RedBlackTreeNode<K, V> findNodeByKey(K nodeKey) {
		return (RedBlackTreeNode<K, V>) super.findNodeByKey(nodeKey);
	}

	// we need to rewrite the super method here because there are some states we need to keep track of during the removal
	@Override
	public void remove(K nodeKey) {
		remove(findNodeByKey(nodeKey));
	}

	public void remove(V nodeValue) {
		remove(findNodeByValue(nodeValue));
	}

	// see [1, subsection 13.4] for details
	private void remove(RedBlackTreeNode<K, V> removedNode) {
		// no node found with the given key
		if (removedNode.isNull()) {
			return;
		}

		// this node will replace the removed node
		RedBlackTreeNode<K, V> replacingNode;

		// to decide whether we should fix the red-black properties after the removal
		boolean fixUp;
		// the node where we start to the fix. this is the node that is moved to new position without changing its color.
		RedBlackTreeNode<K, V> fixUpStartingNode;
		RedBlackTreeNode<K, V> fixUpStartingNodeParent;

		// the removed node has two children
		if (removedNode.getLeft().isNotNull() && removedNode.getRight().isNotNull()) {
			replacingNode = (RedBlackTreeNode<K, V>) findDownwardSuccessorOf(removedNode);

			// moving a black node might break the red-black properties
			fixUp = replacingNode.getColor() == BLACK;
			// save the node here because the children of the replacing node might be changed
			fixUpStartingNode = replacingNode.getRight();
			// just in case fixUpStartingNode is a null node
			fixUpStartingNodeParent = replacingNode;

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

			// to make sure that the replacing node will not break any red-black properties
			replacingNode.setColor(removedNode.getColor());
		}
		// the removed node has only one child or none at all
		else {
			replacingNode = removedNode.getLeft().isNotNull() ? removedNode.getLeft() : removedNode.getRight();

			fixUp = replacingNode.getColor() == BLACK;
			fixUpStartingNode = replacingNode;
			fixUpStartingNodeParent = removedNode.getParent();

			transplantParent(removedNode, replacingNode);
		}

		if (fixUp) {
			fixUpAfterRemove(fixUpStartingNode, fixUpStartingNodeParent);
		}
	}

	private void fixUpAfterRemove(RedBlackTreeNode<K, V> node, RedBlackTreeNode<K, V> firstParent) {
		/* The idea is to keep moving the node with the extra black up until the red-black properties hold */
		while (!node.equals(getRoot()) && node.getColor() == BLACK) {
			// since we are moving up, node can be null only one time at the beginning
			RedBlackTreeNode<K, V> parent = node.isNull() ? firstParent : node.getParent();

			if (node.equals(parent.getLeft())) {
				RedBlackTreeNode<K, V> sibling = parent.getRight();
				// case 1
				if (sibling.getColor() == RED) {
					sibling.setColor(BLACK);
					parent.setColor(RED);
					rotateLeft(parent, sibling);
					sibling = parent.getRight();
				}
				// case 2
				if (sibling.getLeft().getColor() == BLACK && sibling.getRight().getColor() == BLACK) {
					sibling.setColor(RED);
					node = parent;
				}
				else {
					// case 3
					if (sibling.getRight().getColor() == BLACK) {
						sibling.getLeft().setColor(BLACK);
						sibling.setColor(RED);
						rotateRight(sibling, sibling.getLeft());
						sibling = parent.getRight();
					}

					// case 4
					sibling.setColor(parent.getColor());
					parent.setColor(BLACK);
					sibling.getRight().setColor(BLACK);
					rotateLeft(parent, parent.getRight());
					node = getRoot();
				}
			}
			// node is the right child
			else {
				RedBlackTreeNode<K, V> sibling = parent.getLeft();
				// case 1
				if (sibling.getColor() == RED) {
					sibling.setColor(BLACK);
					parent.setColor(RED);
					rotateRight(parent, sibling);
					sibling = parent.getLeft();
				}
				// case 2
				if (sibling.getLeft().getColor() == BLACK && sibling.getRight().getColor() == BLACK) {
					sibling.setColor(RED);
					node = parent;
				}
				else {
					// case 3
					if (sibling.getLeft().getColor() == BLACK) {
						sibling.getRight().setColor(BLACK);
						sibling.setColor(RED);
						rotateLeft(sibling, sibling.getRight());
						sibling = parent.getLeft();
					}

					// case 4
					sibling.setColor(parent.getColor());
					parent.setColor(BLACK);
					sibling.getLeft().setColor(BLACK);
					rotateRight(parent, parent.getLeft());
					node = getRoot();
				}
			}
		}

		node.setColor(BLACK);
	}

	private void transplantParent(RedBlackTreeNode<K, V> fromNode, RedBlackTreeNode<K, V> toNode) {
		RedBlackTreeNode<K, V> parent = fromNode.getParent();
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

		// the difference with the super method
		toNode.setParent(parent);
	}

}
