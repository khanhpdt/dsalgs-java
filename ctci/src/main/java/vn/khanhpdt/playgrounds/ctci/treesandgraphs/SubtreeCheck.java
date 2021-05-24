package vn.khanhpdt.playgrounds.ctci.treesandgraphs;

import vn.khanhpdt.playgrounds.datastructures.nodes.BinaryTreeNode;
import vn.khanhpdt.playgrounds.datastructures.trees.BinarySearchTree;

/**
 * Problem 4.8: Checks if a tree is a subtree of another tree.
 *
 */
class SubtreeCheck {

	static <K, V extends Comparable<V>> boolean isSubtree(BinarySearchTree<K, V> binaryTree,
	                                                      BinarySearchTree<K, V> binarySubTree) {
		return isSubtree(binaryTree.getRoot(), binarySubTree.getRoot());
	}

	private static <K, V extends Comparable<V>> boolean isSubtree(BinaryTreeNode<K, V> treeRoot,
	                                                              BinaryTreeNode<K, V> subtreeRoot) {
		if (treeRoot.isNull() && subtreeRoot.isNull()) {
			return true;
		} else if (treeRoot.isNull()) {
			return false;
		}
		return isEquals(treeRoot, subtreeRoot)
				|| isSubtree(treeRoot.getLeft(), subtreeRoot)
				|| isSubtree(treeRoot.getRight(), subtreeRoot);
	}

	private static <K, V extends Comparable<V>> boolean isEquals(BinaryTreeNode<K, V> firstTreeRoot,
	                                                             BinaryTreeNode<K, V> secondTreeRoot) {
		if (firstTreeRoot.isNull() && secondTreeRoot.isNull()) {
			return true;
		} else if (firstTreeRoot.isNull() || secondTreeRoot.isNull()) {
			return false;
		}
		return firstTreeRoot.getValue().equals(secondTreeRoot.getValue())
				&& isEquals(firstTreeRoot.getLeft(), secondTreeRoot.getLeft())
				&& isEquals(firstTreeRoot.getRight(), secondTreeRoot.getRight());

	}

}
