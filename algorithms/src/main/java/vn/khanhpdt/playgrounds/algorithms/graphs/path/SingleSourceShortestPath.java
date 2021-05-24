package vn.khanhpdt.playgrounds.algorithms.graphs.path;

import vn.khanhpdt.playgrounds.datastructures.graphs.Graph;
import vn.khanhpdt.playgrounds.datastructures.graphs.GraphEdge;
import vn.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class SingleSourceShortestPath<K, V> {

	protected final Graph<K, V> graph;

	protected final GraphVertex<K, V> source;

	protected final Map<GraphVertex<K, V>, Double> distances;

	protected final Map<GraphVertex<K, V>, List<GraphVertex<K, V>>> paths;

	protected SingleSourceShortestPath(Graph<K, V> graph, GraphVertex<K, V> source) {
		this.graph = graph;
		this.source = source;
		this.distances = new HashMap<>();
		this.paths = new HashMap<>();

		build();
	}

	protected abstract void build();

	public double getDistanceTo(GraphVertex<K, V> vertex) {
		return distances.get(vertex);
	}

	public List<GraphVertex<K, V>> getPathTo(GraphVertex<K, V> vertex) {
		paths.putIfAbsent(vertex, getPath(vertex));
		return paths.get(vertex);
	}

	private List<GraphVertex<K, V>> getPath(GraphVertex<K, V> vertex) {
		List<GraphVertex<K, V>> result = new ArrayList<>();

		GraphVertex<K, V> current = vertex;
		while (current != null) {
			result.add(current);
			current = current.getPredecessor();
		}

		// the given vertex is not reachable from the source, thus no path exists
		if (!result.get(result.size() - 1).equals(source)) {
			return new ArrayList<>();
		}

		Collections.reverse(result);

		return result;
	}

	protected void initialize() {
		graph.getVertices().forEach(v -> {
			distances.put(v, Double.POSITIVE_INFINITY);
			v.setPredecessor(null);
		});

		distances.put(source, 0d);
	}

	protected void relax(GraphEdge<K, V> edge) {
		GraphVertex<K, V> vertexTo = edge.getToVertex();
		GraphVertex<K, V> vertexFrom = edge.getFromVertex();

		Double distanceVertexTo = distances.get(vertexTo);
		Double distanceVertexFrom = distances.get(vertexFrom);

		// This is the key to the algorithms to find single-source shortest paths. This updates the shortest path found
		// so far if the newly found path is shorter.
		if (distanceVertexTo > distanceVertexFrom + edge.getWeight()) {
			distances.put(vertexTo, distanceVertexFrom + edge.getWeight());
			vertexTo.setPredecessor(vertexFrom);
		}
	}
}
