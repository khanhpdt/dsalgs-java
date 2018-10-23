package vn.khanhpdt.playgrounds.algorithms.trees;

import vn.khanhpdt.playgrounds.datastructures.nodes.BinaryTreeNode;
import vn.khanhpdt.playgrounds.datastructures.stacks.Stack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author khanhpdt
 */
public class PostOrderTraversalIterative {

	public static <K, V extends Comparable<V>> List<BinaryTreeNode<K, V>> traverse(BinaryTreeNode<K, V> sourceNode) {
		List<BinaryTreeNode<K, V>> result = new ArrayList<>();

		Stack<BinaryTreeNode<K, V>> stack = new Stack<>();

		// to avoid revisit a child of the current node, which will lead to endless loop
		BinaryTreeNode<K, V> lastVisited = null;

		BinaryTreeNode<K, V> currentNode = sourceNode;
		while (currentNode.isNotNull() || !stack.isEmpty()) {
			if (currentNode.isNotNull()) {
				// save node to traverse to it later
				stack.push(currentNode);

				// traverse left
				currentNode = currentNode.getLeft();
			} else {
				// one of the child of this node is the most recently visited node
				BinaryTreeNode<K, V> parentOfLastVisited = stack.peek();

				if (parentOfLastVisited.getRight().isNotNull() && !parentOfLastVisited.getRight().equals(lastVisited)) {
					// traverse right
					currentNode = parentOfLastVisited.getRight();
				} else {
					// visit node
					BinaryTreeNode<K, V> node = stack.pop();
					result.add(node);
					lastVisited = node;

					// don't update the currentNode (== null) here because we want to go back to the just-visited node's
					// parent which is currently at the top of the stack. we can do this because the traversal is post-order,
					// meaning that all nodes under the just-visited node have already been visited.
				}
			}
		}

		return result;
	}

}
