package vn.khanhpdt.playgrounds.ctci.treesandgraphs;

import vn.khanhpdt.playgrounds.datastructures.nodes.BinarySearchTreeNode;
import vn.khanhpdt.playgrounds.datastructures.nodes.BinaryTreeNode;
import vn.khanhpdt.playgrounds.datastructures.trees.BinarySearchTree;

/**
 * Problem 4.6: Finds the next node of a given node in a binary search tree. The next node is the one next to the given
 * node in the in-order traversal.
 *
 */
class NextNodeFinder {

	static <K, V extends Comparable<V>> BinaryTreeNode<K, V> find(BinarySearchTree<K, V> bst, BinarySearchTreeNode<K, V> node) {
		return bst.findSuccessorOf(node);
	}

}
