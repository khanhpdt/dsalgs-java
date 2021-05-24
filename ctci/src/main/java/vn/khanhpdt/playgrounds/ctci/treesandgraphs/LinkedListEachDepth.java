package vn.khanhpdt.playgrounds.ctci.treesandgraphs;

import vn.khanhpdt.playgrounds.datastructures.linkedlists.SinglyLinkedList;
import vn.khanhpdt.playgrounds.datastructures.nodes.BinaryTreeNode;
import vn.khanhpdt.playgrounds.datastructures.trees.BinarySearchTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem 4.4: Create a linked list for each depth of a given binary tree
 *
 */
class LinkedListEachDepth {

	static <K, V extends Comparable<V>> List<SinglyLinkedList<BinaryTreeNode<K, V>>> build(
			BinarySearchTree<K, V> binaryTree) {

		List<SinglyLinkedList<BinaryTreeNode<K, V>>> result = new ArrayList<>();

		collect(binaryTree.getRoot(), 1, result);

		return result;
	}

	private static <K, V extends Comparable<V>> void collect(BinaryTreeNode<K, V> node, int level,
	                                                         List<SinglyLinkedList<BinaryTreeNode<K, V>>> result) {

		if (node.isNull()) {
			return;
		}

		// reach new level
		if (result.size() < level) {
			result.add(new SinglyLinkedList<>());
		}

		// should use insertFirst instead of insertLast to improve the running time
		result.get(level - 1).insertLast(node);
		collect(node.getLeft(), level + 1, result);
		collect(node.getRight(), level + 1, result);
	}
}
