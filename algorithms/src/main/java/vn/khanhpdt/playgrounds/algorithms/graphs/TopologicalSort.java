package vn.khanhpdt.playgrounds.algorithms.graphs;

import vn.khanhpdt.playgrounds.datastructures.graphs.Graph;
import vn.khanhpdt.playgrounds.datastructures.linkedlists.LinkedLists;
import vn.khanhpdt.playgrounds.datastructures.linkedlists.SinglyLinkedList;
import vn.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;

import java.util.List;

/**
 * @author khanhpdt
 */
public class TopologicalSort<K, V> {

	private Graph<K, V> graph;

	private TopologicalSort(Graph<K, V> graph) {
		this.graph = graph;
	}

	private Graph<K, V> doSort() {
		if (DirectedCycle.checkExists(graph)) {
			throw new IllegalArgumentException("Graph has directed cycles. Topological sort is not possible.");
		}

		SinglyLinkedList<GraphVertex<K, V>> sortedVertices = new SinglyLinkedList<>();
		topologicalSort(graph.getVertex(0), sortedVertices);

		List<GraphVertex<K, V>> sortedVerticesList = LinkedLists.traverse(sortedVertices);
		return new Graph<>(sortedVerticesList);
	}

	private void topologicalSort(GraphVertex<K, V> vertex, SinglyLinkedList<GraphVertex<K, V>> sortedVertices) {
		if (vertex.isNotDiscovered()) {
			vertex.markDiscovered();
			vertex.getAdjacents().stream()
					.filter(GraphVertex::isNotDiscovered)
					.forEach(adj -> topologicalSort(adj, sortedVertices));

			// Because this traverse is depth-first, when a vertex is visited, all of its adjacents are already visited.
			// Thus, we add the vertex to the front of the linked list to make sure that it is positioned before its adjacents.
			vertex.markVisited();
			sortedVertices.insertFirst(vertex);
		}
	}

	public static <K, V> boolean verify(Graph<K, V> graph) {
		List<GraphVertex<K, V>> vertices = graph.getVertices();
		for (int vertexIndex = 0; vertexIndex < vertices.size(); vertexIndex++) {
			for (GraphVertex<K, V> adjacent : vertices.get(vertexIndex).getAdjacents()) {
				int adjacentIndex = vertices.indexOf(adjacent);
				// a graph is topologically sorted if any of its vertices stands before its adjacents in the vertices list
				if (vertexIndex > adjacentIndex) {
					return false;
				}
			}
		}
		return true;
	}

	public static <K, V> Graph<K, V> from(Graph<K, V> graph) {
		return new TopologicalSort<>(graph).doSort();
	}
}
