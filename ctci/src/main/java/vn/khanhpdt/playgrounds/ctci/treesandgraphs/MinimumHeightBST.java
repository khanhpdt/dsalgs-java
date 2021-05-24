package vn.khanhpdt.playgrounds.ctci.treesandgraphs;

import vn.khanhpdt.playgrounds.datastructures.nodes.BinarySearchTreeNode;
import vn.khanhpdt.playgrounds.datastructures.nodes.BinarySearchTreeNullNode;
import vn.khanhpdt.playgrounds.datastructures.trees.BinarySearchTree;

import java.util.UUID;

/**
 * Problem 4.3: Creates a BST with minimum height from increasingly sorted array.
 *
 */
class MinimumHeightBST {

	static BinarySearchTree<UUID, Integer> create(int[] sortedIntegers) {
		BinarySearchTree<UUID, Integer> bst = new BinarySearchTree<>();
		create(sortedIntegers, 0, sortedIntegers.length - 1, bst);
		return bst;
	}

	// O(NlgN)
	private static void create(int[] sortedIntegers, int from, int to, BinarySearchTree<UUID, Integer> bst) {
		if (from > to) {
			return;
		}

		int middle = from + (int) Math.ceil((to - from) / 2);

		// O(lgN): cost of inserting one node
		bst.insert(BinarySearchTreeNode.from(UUID.randomUUID(), sortedIntegers[middle]));

		create(sortedIntegers, from, middle - 1, bst);
		create(sortedIntegers, middle + 1, to, bst);
	}

	// O(N)
	static BinarySearchTree<UUID, Integer> create2(int[] sortedIntegers) {
		BinarySearchTreeNode<UUID, Integer> root = create2(sortedIntegers, 0, sortedIntegers.length - 1);
		// O(1) to create the tree from a given root
		return new BinarySearchTree<>(root);
	}

	private static BinarySearchTreeNode<UUID, Integer> create2(int[] sortedIntegers, int from, int to) {
		if (from > to) {
			return BinarySearchTreeNullNode.getInstance();
		}

		int middle = from + (int) Math.ceil((to - from) / 2);

		// O(1) to create a node
		BinarySearchTreeNode<UUID, Integer> node = BinarySearchTreeNode.from(UUID.randomUUID(), sortedIntegers[middle]);

		// O(1) to links the nodes
		node.setLeft(create2(sortedIntegers, from, middle - 1));
		node.setRight(create2(sortedIntegers, middle + 1, to));

		return node;
	}

}
