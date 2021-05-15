package vn.khanhpdt.playgrounds.ctci.treesandgraphs;

import vn.khanhpdt.playgrounds.datastructures.nodes.BinaryTreeNode;

import java.util.UUID;

/**
 * Problem 4.5: Checks if a binary tree is a binary search tree.
 *
 * @author khanhpdt
 */
class BSTCheck {

	// O(N^2)
	static boolean isBST(BinaryTreeNode<UUID, Integer> root) {
		if (root.isNull()) {
			return true;
		}

		// O(N)
		boolean rootSafe = isLargerThanOrEqualTo(root, root.getLeft()) && isSmallerThanOrEqualTo(root, root.getRight());

		return rootSafe && isBST(root.getLeft()) && isBST(root.getRight());
	}

	private static boolean isLargerThanOrEqualTo(BinaryTreeNode<UUID, Integer> root,
	                                             BinaryTreeNode<UUID, Integer> subtreeRoot) {
		if (subtreeRoot.isNull()) {
			return true;
		}
		return root.compareTo(subtreeRoot) >= 0
				&& isLargerThanOrEqualTo(root, subtreeRoot.getLeft())
				&& isLargerThanOrEqualTo(root, subtreeRoot.getRight());
	}

	private static boolean isSmallerThanOrEqualTo(BinaryTreeNode<UUID, Integer> root,
	                                              BinaryTreeNode<UUID, Integer> subtreeRoot) {
		if (subtreeRoot.isNull()) {
			return true;
		}
		return root.compareTo(subtreeRoot) <= 0
				&& isSmallerThanOrEqualTo(root, subtreeRoot.getLeft())
				&& isSmallerThanOrEqualTo(root, subtreeRoot.getRight());
	}

	// O(N)
	static boolean isBST2(BinaryTreeNode<UUID, Integer> root) {
		return isBST2(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private static boolean isBST2(BinaryTreeNode<UUID, Integer> node, int minValue, int maxValue) {
		if (node.isNull()) {
			return true;
		}
		if (node.getValue() < minValue || node.getValue() > maxValue) {
			return false;
		}
		return isBST2(node.getLeft(), minValue, node.getValue())
				&& isBST2(node.getRight(), node.getValue(), maxValue);
	}

}
