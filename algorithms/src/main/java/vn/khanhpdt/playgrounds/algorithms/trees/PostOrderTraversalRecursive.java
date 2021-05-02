package vn.khanhpdt.playgrounds.algorithms.trees;

import vn.khanhpdt.playgrounds.datastructures.nodes.BinaryTreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class PostOrderTraversalRecursive {

	static <K, V extends Comparable<V>> List<BinaryTreeNode<K, V>> traverse(BinaryTreeNode<K, V> sourceNode) {
		return doTraverse(sourceNode);
	}

	private static <K, V extends Comparable<V>> List<BinaryTreeNode<K, V>> doTraverse(BinaryTreeNode<K, V> sourceNode) {
		if (sourceNode.isNull()) {
			return Collections.emptyList();
		}

		List<BinaryTreeNode<K, V>> result = new ArrayList<>();
		result.addAll(doTraverse(sourceNode.getLeft()));
		result.addAll(doTraverse(sourceNode.getRight()));
		result.add(sourceNode);
		return result;
	}

}
