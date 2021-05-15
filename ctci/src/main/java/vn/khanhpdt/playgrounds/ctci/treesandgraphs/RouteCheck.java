package vn.khanhpdt.playgrounds.ctci.treesandgraphs;

import vn.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;
import vn.khanhpdt.playgrounds.datastructures.queues.Queue;

/**
 * Problem 4.2: Checks if there is a route from a given node to another node in a directed graph.
 *
 * @author khanhpdt
 */
class RouteCheck {

	// depth-first style
	static <K, V> boolean checkExistsDFS(GraphVertex<K, V> source, GraphVertex<K, V> dest) {
		if (source == null) {
			return false;
		}

		for (GraphVertex<K, V> adj : source.getAdjacents()) {
			if (adj.equals(dest)) {
				return true;
			}

			if (adj.isNotDiscovered()) {
				adj.markDiscovered();
				if (checkExistsDFS(adj, dest)) {
					return true;
				}
			}
		}

		return false;
	}

	// breadth-first style
	static <K, V> boolean checkExistsBFS(GraphVertex<K, V> source, GraphVertex<K, V> dest) {
		if (source == null) {
			return false;
		}

		Queue<GraphVertex<K, V>> bfsNodes = new Queue<>();
		bfsNodes.enqueueRear(source);

		while (!bfsNodes.isEmpty()) {
			GraphVertex<K, V> currentNode = bfsNodes.dequeueFront();
			for (GraphVertex<K, V> adj : currentNode.getAdjacents()) {
				if (adj.isNotDiscovered()) {
					if (adj.equals(dest)) {
						return true;
					}
					adj.markDiscovered();
					bfsNodes.enqueueRear(adj);
				}
			}
		}

		return false;
	}

}
