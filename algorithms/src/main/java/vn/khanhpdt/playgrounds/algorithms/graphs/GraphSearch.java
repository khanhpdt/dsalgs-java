package vn.khanhpdt.playgrounds.algorithms.graphs;

import vn.khanhpdt.playgrounds.datastructures.graphs.Graph;
import vn.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;

import java.util.ArrayList;
import java.util.List;

/**
 * @author khanhpdt
 */
public abstract class GraphSearch<K, V> {

	protected int time = 1;

	public List<GraphVertex<K, V>> search(GraphVertex<K, V> sourceVertex) {
		return doSearch(sourceVertex);
	}

	public List<Graph<K, V>> search(Graph<K, V> graph) {
		List<Graph<K, V>> graphs = new ArrayList<>();

		graph.getVertices().stream()
				.filter(GraphVertex::isNotDiscovered)
				.forEach(v -> graphs.add(new Graph<>(this.search(v))));

		return graphs;
	}

	protected abstract List<GraphVertex<K, V>> doSearch(GraphVertex<K, V> sourceVertex);

}
