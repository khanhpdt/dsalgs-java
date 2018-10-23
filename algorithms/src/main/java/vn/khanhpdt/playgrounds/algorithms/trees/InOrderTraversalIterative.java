package vn.khanhpdt.playgrounds.algorithms.trees;

import vn.khanhpdt.playgrounds.datastructures.nodes.BinaryTreeNode;
import vn.khanhpdt.playgrounds.datastructures.stacks.Stack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author khanhpdt
 */
public class InOrderTraversalIterative {

	public static <K, V extends Comparable<V>> List<BinaryTreeNode<K, V>> traverse(BinaryTreeNode<K, V> sourceNode) {
		List<BinaryTreeNode<K, V>> result = new ArrayList<>();

		// for temporarily holding nodes during traverse
		Stack<BinaryTreeNode<K, V>> stack = new Stack<>();

		/*
		Main idea: Keep traversing accordingly to the in-order order. When reaching the sentinel node, goes back to the
		most recently visited node which is at the top of the stack.
		*/

		BinaryTreeNode<K, V> currentNode = sourceNode;
		// there is no node left to traverse when currentNode is null and no node is in the stack
		while (currentNode.isNotNull() || !stack.isEmpty()) {
			if (currentNode.isNotNull()) {
				// save node to move to it later
				stack.push(currentNode);
				// traverse the left subtree of the node before traversing the node
				currentNode = currentNode.getLeft();
			} else {
				// visit node
				BinaryTreeNode<K, V> visitedNode = stack.pop();
				result.add(visitedNode);

				// traverse the right subtree of the node after traversing the node
				currentNode = visitedNode.getRight();
			}
		}

		return result;
	}

}
