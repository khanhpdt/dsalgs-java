package vn.khanhpdt.playgrounds.algorithms.trees;

import vn.khanhpdt.playgrounds.datastructures.nodes.BinaryTreeNode;
import vn.khanhpdt.playgrounds.datastructures.stacks.Stack;

import java.util.ArrayList;
import java.util.List;

public class PreOrderTraversalIterative {

	public static <K, V extends Comparable<V>> List<BinaryTreeNode<K, V>> traverse(BinaryTreeNode<K, V> sourceNode) {
		List<BinaryTreeNode<K, V>> result = new ArrayList<>();
		Stack<BinaryTreeNode<K, V>> stack = new Stack<>();

		BinaryTreeNode<K, V> currentNode = sourceNode;
		while (currentNode.isNotNull() || !stack.isEmpty()) {
			if (currentNode.isNotNull()) {
				// visit node
				result.add(currentNode);

				// save right to move to it later
				if (currentNode.getRight().isNotNull()) {
					stack.push(currentNode.getRight());
				}

				// traverse left
				currentNode = currentNode.getLeft();
			} else {
				// traverse right
				currentNode = stack.pop();
			}
		}

		return result;
	}

}
