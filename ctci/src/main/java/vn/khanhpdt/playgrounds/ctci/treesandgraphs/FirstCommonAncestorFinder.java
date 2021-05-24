package vn.khanhpdt.playgrounds.ctci.treesandgraphs;

import vn.khanhpdt.playgrounds.datastructures.nodes.BinaryTreeNode;

class FirstCommonAncestorFinder {

	static <K, V extends Comparable<V>> BinaryTreeNode<K, V> find(BinaryTreeNode<K, V> firstNode,
	                                                              BinaryTreeNode<K, V> secondNode) {
		// make two nodes at the same level
		if (firstNode.getDepth() > secondNode.getDepth()) {
			firstNode = moveUp(firstNode, firstNode.getDepth() - secondNode.getDepth());
		} else {
			secondNode = moveUp(secondNode, secondNode.getDepth() - firstNode.getDepth());
		}

		if (firstNode.equals(secondNode)) {
			return firstNode.getParent();
		}

		while (firstNode.isNotNull() && !firstNode.equals(secondNode)) {
			firstNode = firstNode.getParent();
			secondNode = secondNode.getParent();
		}

		// either firstNode or secondNode can be returned. at this time, either they are both null nodes or they
		// reach their first common ancestor.
		return firstNode;
	}

	private static <K, V extends Comparable<V>> BinaryTreeNode<K, V> moveUp(BinaryTreeNode<K, V> node, int nLevels) {
		BinaryTreeNode<K, V> result = node;
		int up = 0;
		while (up < nLevels) {
			result = result.getParent();
			up++;
		}
		return result;
	}
}
