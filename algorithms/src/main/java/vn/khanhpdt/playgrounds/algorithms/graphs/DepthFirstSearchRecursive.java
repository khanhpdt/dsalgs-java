package vn.khanhpdt.playgrounds.algorithms.graphs;

import vn.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;

import java.util.ArrayList;
import java.util.List;

/**
 * @author khanhpdt
 */
public class DepthFirstSearchRecursive<K, V> extends GraphSearch<K, V> {

	@Override
	protected List<GraphVertex<K, V>> doSearch(GraphVertex<K, V> sourceVertex) {
		List<GraphVertex<K, V>> reachableVertices = new ArrayList<>();
		doSearch(sourceVertex, null, reachableVertices);
		return reachableVertices;
	}

	private void doSearch(GraphVertex<K, V> vertex, GraphVertex<K, V> predecessor, List<GraphVertex<K, V>> reachableVertices) {
		if (vertex.isNotDiscovered()) {
			vertex.markDiscovered(predecessor, time++);

			vertex.getAdjacents().stream()
					.filter(GraphVertex::isNotDiscovered)
					.forEach(adj -> doSearch(adj, vertex, reachableVertices));

			vertex.markVisited(time++);
			reachableVertices.add(vertex);
		}
	}

}
