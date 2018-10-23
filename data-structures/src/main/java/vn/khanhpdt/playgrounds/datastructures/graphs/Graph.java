package vn.khanhpdt.playgrounds.datastructures.graphs;

import vn.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @param <K>  type of vertex key
 * @param <V>  type of vertex value
 * @author khanhpdt
 */
public class Graph<K, V> {

	private List<GraphVertex<K, V>> vertices;

	public Graph() {
		this.vertices = new ArrayList<>();
	}

	public Graph(List<GraphVertex<K, V>> vertices) {
		this.vertices = vertices;
	}

	public GraphVertex<K, V> addVertex(K vertexKey) {
		GraphVertex<K, V> vertex = new GraphVertex<>(vertexKey);
		vertices.add(vertex);
		return vertex;
	}

	public GraphVertex<K, V> addVertex(K key, V value) {
		GraphVertex<K, V> vertex = new GraphVertex<>(key, value);
		vertices.add(vertex);
		return vertex;
	}

	public GraphVertex<K, V> getVertex(K vertexKey) {
		return vertices.stream().filter(v -> v.getKey().equals(vertexKey)).findFirst().orElseGet(null);
	}

	public GraphVertex<K, V> getVertex(int vertexIndex) {
		return vertices.get(vertexIndex);
	}

	public List<GraphVertex<K, V>> getVertices(int[] vertexIndices) {
		List<GraphVertex<K, V>> result = new ArrayList<>();
		Arrays.stream(vertexIndices).forEach(i -> result.add(vertices.get(i)));
		return result;
	}

	public void addEdge(GraphVertex<K, V> vertex1, GraphVertex<K, V> vertex2) {
		addEdge(vertex1, vertex2, 0);
	}

	private void addEdge(GraphVertex<K, V> vertex1, GraphVertex<K, V> vertex2, int weight) {
		vertex1.addEdge(vertex2, weight);
		vertex2.addEdge(vertex1, weight);
	}

	public void addEdge(int vertex1Index, int vertex2Index) {
		addEdge(vertices.get(vertex1Index), vertices.get(vertex2Index), 0);
	}

	private void addEdge(int vertex1Index, int vertex2Index, int weight) {
		addEdge(vertices.get(vertex1Index), vertices.get(vertex2Index), weight);
	}

	public void addEdges(int[][] pairIndexes) {
		for (int[] pairIndex : pairIndexes) {
			if (pairIndex.length == 2) {
				addEdge(pairIndex[0], pairIndex[1]);
			} else if (pairIndex.length == 3) {
				addEdge(pairIndex[0], pairIndex[1], pairIndex[2]);
			}
		}
	}

	public int numberOfVertices() {
		return vertices.size();
	}

	public void addDirectedEdges(int[][] indexPairs) {
		for (int[] indexPair : indexPairs) {
			if (indexPair.length == 2) {
				addDirectedEdge(indexPair[0], indexPair[1]);
			} else if (indexPair.length == 3) {
				int edgeWeight = indexPair[2];
				addDirectedEdge(indexPair[0], indexPair[1], edgeWeight);
			}
		}
	}

	private void addDirectedEdge(int vertexIndex, int adjacentVertexIndex) {
		addDirectedEdge(getVertex(vertexIndex), getVertex(adjacentVertexIndex));
	}

	private void addDirectedEdge(GraphVertex<K, V> from, GraphVertex<K, V> to) {
		from.addEdge(to);
	}

	private void addDirectedEdge(int vertexIndex, int adjacentVertexIndex, int weight) {
		addDirectedEdge(getVertex(vertexIndex), getVertex(adjacentVertexIndex), weight);
	}

	private void addDirectedEdge(GraphVertex<K, V> from, GraphVertex<K, V> to, int weight) {
		from.addEdge(to, weight);
	}

	public List<GraphVertex<K, V>> getVertices() {
		return vertices;
	}

	public boolean hasVertex(GraphVertex<K, V> vertex) {
		return vertices.stream().anyMatch(v -> v.equals(vertex));
	}

	public List<GraphEdge<K, V>> getEdges() {
		List<GraphEdge<K, V>> edges = new ArrayList<>();
		vertices.forEach(v -> edges.addAll(v.getOutgoingEdges()));
		return edges;
	}

	public void resetAfterTraverse() {
		getVertices().forEach(GraphVertex::resetAfterTraverse);
	}
}
