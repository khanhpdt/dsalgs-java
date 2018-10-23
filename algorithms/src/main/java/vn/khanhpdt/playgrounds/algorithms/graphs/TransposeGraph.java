package vn.khanhpdt.playgrounds.algorithms.graphs;

import vn.khanhpdt.playgrounds.datastructures.graphs.Graph;
import vn.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author khanhpdt
 */
public class TransposeGraph {

	public static <K, V> Graph<K, V> of(Graph<K, V> graph) {
		List<GraphVertex<K, V>> graphVertices = graph.getVertices();

		// clone vertices
		List<GraphVertex<K, V>> transposedGraphVertices = graphVertices.stream()
				.map(GraphVertex::new)
				.collect(Collectors.toList());

		// map from vertex's key to the vertex in the transposed graph
		Map<K, GraphVertex<K, V>> transposedGraphVertexByKey = transposedGraphVertices.stream()
				.collect(Collectors.toMap(GraphVertex::getKey, Function.identity()));

		// reverse the original edges
		graphVertices.forEach(vertex ->
				vertex.getAdjacents().forEach(adj ->
						transposedGraphVertexByKey.get(adj.getKey()).addEdge(transposedGraphVertexByKey.get(vertex.getKey()))));

		return new Graph<>(transposedGraphVertices);
	}

}
