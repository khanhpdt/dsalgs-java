package vn.khanhpdt.playgrounds.datastructures.graphs;

import vn.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;

public class GraphEdge<K, V> {

	private GraphVertex<K, V> fromVertex;

	private GraphVertex<K, V> toVertex;

	private double weight;

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
