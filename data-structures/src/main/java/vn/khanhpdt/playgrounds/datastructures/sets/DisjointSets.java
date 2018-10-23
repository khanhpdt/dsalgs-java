package vn.khanhpdt.playgrounds.datastructures.sets;

import vn.khanhpdt.playgrounds.datastructures.nodes.DisjointSetNode;

import java.util.List;

/**
 * @author khanhpdt
 */
public class DisjointSets {

	private DisjointSets() {
		// no instance
	}

	public static <K, V> DisjointSet<K, V> makeSet(List<DisjointSetNode<K, V>> nodes) {
		DisjointSet<K, V> disjointSet = new DisjointSet<>();
		nodes.forEach(disjointSet::insert);
		return disjointSet;
	}

	public static <K, V> DisjointSet<K, V> findSet(DisjointSetNode<K, V> node) {
		return node.getSet();
	}

	public static <K, V> DisjointSet<K, V> union(DisjointSetNode<K, V> node1, DisjointSetNode<K, V> node2) {
		return union(node1.getSet(), node2.getSet());
	}

	private static <K, V> DisjointSet<K, V> union(DisjointSet<K, V> set1, DisjointSet<K, V> set2) {
		// use weighted-union heuristic: append the elements in the shorter set to the other set. This reduces the time
		// to update the set of the appended nodes.
		DisjointSet<K, V> unionSet;
		DisjointSet<K, V> destroyedSet;
		if (set1.size() >= set2.size()) {
			unionSet = set1;
			destroyedSet = set2;
		} else {
			unionSet = set2;
			destroyedSet = set1;
		}

		// add nodes from destroyed set to the union set
		DisjointSetNode<K, V> node = destroyedSet.getHead();
		while (node != null) {
			unionSet.insert(node);
			node.setSet(unionSet);

			node = node.getNext();
		}

		// don't need this set anymore
		destroyedSet.clear();

		return unionSet;
	}
}
