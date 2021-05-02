package vn.khanhpdt.playgrounds.algorithms.graphs;

import vn.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;
import vn.khanhpdt.playgrounds.datastructures.queues.Queue;

import java.util.ArrayList;
import java.util.List;

public class BreadthFirstSearch<K, V> extends GraphSearch<K, V> {

	@Override
	public List<GraphVertex<K, V>> doSearch(GraphVertex<K, V> sourceVertex) {
		List<GraphVertex<K, V>> reachableVertices = new ArrayList<>();
		Queue<GraphVertex<K, V>> queue = new Queue<>();

		queue.enqueueRear(sourceVertex);
		// BFS visits a node when the node is discovered
		sourceVertex.markDiscovered(null, time++);
		sourceVertex.markVisited(time);

		while (!queue.isEmpty()) {
			GraphVertex<K, V> current = queue.dequeueFront();

			current.getAdjacents().stream()
					.filter(GraphVertex::isNotDiscovered)
					.forEach(adj -> {
						// breadth-first: visits all nodes adjacent to the current node, but adds them to a queue
						// so that they will be processed BEFORE processing the adjacents of the nodes at the same distance
						// from the current vertex.
						adj.markDiscovered(current, time++);
						adj.markVisited(time);
						queue.enqueueRear(adj);
					});

			reachableVertices.add(current);
		}

		return reachableVertices;
	}

}
