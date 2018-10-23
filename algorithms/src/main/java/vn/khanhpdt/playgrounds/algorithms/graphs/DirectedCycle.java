package vn.khanhpdt.playgrounds.algorithms.graphs;

import vn.khanhpdt.playgrounds.datastructures.graphs.Graph;
import vn.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;

/**
 * @author khanhpdt
 */
public class DirectedCycle<K, V> {

	private boolean exists;

	private DirectedCycle(Graph<K, V> graph) {
		this.exists = check(graph);
		graph.resetAfterTraverse();
	}

	private boolean check(Graph<K, V> graph) {
		for (GraphVertex<K, V> vertex : graph.getVertices()) {
			if (vertex.isNotDiscovered() && checkDirectedCycleFrom(vertex)) {
				return true;
			}
		}
		return false;
	}

	private boolean checkDirectedCycleFrom(GraphVertex<K, V> vertex) {
		// because the traverse is depth-first, a discovered vertex must be visited before the traverse can go back to
		// the vertex
		if (vertex.isDiscovered() && vertex.isNotVisited()) {
			return true;
		} else {
			vertex.markDiscovered();
			for (GraphVertex<K, V> adjacent : vertex.getAdjacents()) {
				if (checkDirectedCycleFrom(adjacent)) {
					return true;
				}
			}
			vertex.markVisited();

			return false;
		}
	}

	private boolean exists() {
		return exists;
	}

	public static <K, V> boolean checkExists(Graph<K, V> graph) {
		return new DirectedCycle<>(graph).exists();
	}
}
