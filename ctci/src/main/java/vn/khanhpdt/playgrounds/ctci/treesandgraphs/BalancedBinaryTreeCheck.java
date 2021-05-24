package vn.khanhpdt.playgrounds.ctci.treesandgraphs;

import vn.khanhpdt.playgrounds.datastructures.nodes.BinaryTreeNode;
import vn.khanhpdt.playgrounds.datastructures.trees.BinarySearchTree;

/**
 * Problem 4.1
 *
 */
class BalancedBinaryTreeCheck {

	private static final int UNBALANCED_CODE = -100;
	private static final int HEIGHT_NULL_NODE = -1;

	static <K, V extends Comparable<V>> boolean isBalanced(BinarySearchTree<K, V> bst) {
		if (bst == null) {
			throw new IllegalArgumentException("Null tree!");
		}
		return isBalanced(bst.getRoot());
	}

	// O(N^2)
	private static <K, V extends Comparable<V>> boolean isBalanced(BinaryTreeNode<K, V> root) {
		// empty tree is balanced
		if (root.isNull()) {
			return true;
		}

		// O(N): cost of calculating height of a root of a tree with N node
		boolean balanced = Math.abs(root.getLeft().getHeight() - root.getRight().getHeight()) <= 1;

		return balanced && isBalanced(root.getLeft()) && isBalanced(root.getRight());
	}

	static <K, V extends Comparable<V>> boolean isBalanced2(BinarySearchTree<K, V> bst) {
		if (bst == null) {
			throw new IllegalArgumentException("Null tree!");
		}
		return isBalanced2(bst.getRoot());
	}

	// O(N)
	private static <K, V extends Comparable<V>> boolean isBalanced2(BinaryTreeNode<K, V> root) {
		// empty tree is balanced
		if (root.isNull()) {
			return true;
		}
		return getHeight(root) != UNBALANCED_CODE;
	}

	private static <K, V extends Comparable<V>> int getHeight(BinaryTreeNode<K, V> node) {
		if (node.isNull()) {
			return HEIGHT_NULL_NODE;
		}

		int leftSubtreeHeight = getHeight(node.getLeft());
		if (leftSubtreeHeight == UNBALANCED_CODE) {
			return UNBALANCED_CODE;
		}

		int rightSubtreeHeight = getHeight(node.getRight());
		if (rightSubtreeHeight == UNBALANCED_CODE) {
			return UNBALANCED_CODE;
		}

		if (Math.abs(leftSubtreeHeight - rightSubtreeHeight) > 1) {
			return UNBALANCED_CODE;
		}

		return 1 + Math.max(leftSubtreeHeight, rightSubtreeHeight);
	}

}
