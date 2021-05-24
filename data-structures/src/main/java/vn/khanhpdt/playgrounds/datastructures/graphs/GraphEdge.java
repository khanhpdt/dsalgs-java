package vn.khanhpdt.playgrounds.datastructures.graphs;

import vn.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;

public class GraphEdge<K, V> {

	private final GraphVertex<K, V> fromVertex;

	private final GraphVertex<K, V> toVertex;

	private final double weight;

	public GraphEdge(GraphVertex<K, V> fromVertex, GraphVertex<K, V> toVertex, double weight) {
		this.fromVertex = fromVertex;
		this.toVertex = toVertex;
		this.weight = weight;
	}

	public GraphVertex<K, V> getFromVertex() {
		return fromVertex;
	}

	public GraphVertex<K, V> getToVertex() {
		return toVertex;
	}

	public double getWeight() {
		return weight;
	}

}
