package vn.khanhpdt.playgrounds.algorithms.graphs;

import vn.khanhpdt.playgrounds.datastructures.graphs.Graph;
import vn.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StronglyConnectedComponents {

	public static <K, V> List<Graph<K, V>> of(Graph<K, V> graph) {
		DepthFirstSearchRecursive<K, V> dfs = new DepthFirstSearchRecursive<>();

		// DFS the original graph to compute the finished time of its vertices
		List<Graph<K, V>> dfsGraphs = dfs.search(graph);

		Graph<K, V> transposeGraph = TransposeGraph.of(graph);

		Map<K, GraphVertex<K, V>> transposeGraphVertexByKey = transposeGraph.getVertices().stream()
				.collect(Collectors.toMap(GraphVertex::getKey, Function.identity()));

		List<Graph<K, V>> stronglyConnectedComponents = new ArrayList<>();

		// traverse the vertices of the transpose graph in the decreasing order of their finished times.
		// note that the vertices in dfsGraphs are sorted in increasing order of their finished times
		for (int i = dfsGraphs.size() - 1; i >= 0; i--) {
			Graph<K, V> dfsGraph = dfsGraphs.get(i);
			for (int j = dfsGraph.getVertices().size() - 1; j >= 0; j--) {
				GraphVertex<K, V> transposeGraphVertex = transposeGraphVertexByKey.get(dfsGraph.getVertex(j).getKey());
				if (transposeGraphVertex.isNotDiscovered()) {
					List<GraphVertex<K, V>> stronglyConnectedVertices = dfs.search(transposeGraphVertex);
					stronglyConnectedComponents.add(new Graph<>(stronglyConnectedVertices));
				}
			}
		}

		return stronglyConnectedComponents;
	}

}
