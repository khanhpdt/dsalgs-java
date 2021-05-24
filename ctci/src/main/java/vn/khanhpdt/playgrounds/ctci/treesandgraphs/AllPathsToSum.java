package vn.khanhpdt.playgrounds.ctci.treesandgraphs;

import vn.khanhpdt.playgrounds.datastructures.nodes.BinarySearchTreeNode;
import vn.khanhpdt.playgrounds.datastructures.nodes.BinaryTreeNode;
import vn.khanhpdt.playgrounds.datastructures.trees.BinarySearchTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Problem 4.9: Finds all paths whose sum is equal to the given sum.
 *
 */
class AllPathsToSum {

	static List<List<BinaryTreeNode<UUID, Integer>>> find(BinarySearchTree<UUID, Integer> bst, int sum) {
		return findPaths(bst.getRoot(), sum);
	}

	// O(NlgN)
	private static List<List<BinaryTreeNode<UUID, Integer>>> findPaths(BinarySearchTreeNode<UUID, Integer> source, int sum) {
		if (source.isNull()) {
			return Collections.emptyList();
		}

		List<List<BinaryTreeNode<UUID, Integer>>> result = new ArrayList<>();

		// O(lgN)
		appendPathsSingleSource(source, sum, new ArrayList<>(), result);

		result.addAll(findPaths(source.getLeft(), sum));
		result.addAll(findPaths(source.getRight(), sum));

		return result;
	}

	private static void appendPathsSingleSource(BinarySearchTreeNode<UUID, Integer> source,
	                                            int sum,
	                                            List<BinaryTreeNode<UUID, Integer>> currentPath,
	                                            List<List<BinaryTreeNode<UUID, Integer>>> result) {

		if (source.isNull()) {
			return;
		}

		// save the current path to a local variable for backtracking
		List<BinaryTreeNode<UUID, Integer>> currentPathLocal = new ArrayList<>(currentPath);
		currentPathLocal.add(source);

		boolean sumPathSatisfied = source.getValue() == sum;
		if (sumPathSatisfied) {
			result.add(currentPathLocal);
		}

		appendPathsSingleSource(source.getLeft(), sum - source.getValue(), currentPathLocal, result);
		appendPathsSingleSource(source.getRight(), sum - source.getValue(), currentPathLocal, result);
	}

}
