package vn.khanhpdt.playgrounds.algorithms.graphs;

import vn.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;
import vn.khanhpdt.playgrounds.datastructures.stacks.Stack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author khanhpdt
 */
public class DepthFirstSearchIterative<K, V> extends GraphSearch<K, V> {

	@Override
	protected List<GraphVertex<K, V>> doSearch(GraphVertex<K, V> source) {
		Stack<GraphVertex<K, V>> discoveredStack = new Stack<>();
		Stack<GraphVertex<K, V>> visitedStack = new Stack<>();

		source.markDiscovered(null, time++);
		discoveredStack.push(source);

		while (!discoveredStack.isEmpty()) {
			GraphVertex<K, V> current = discoveredStack.pop();

			current.getAdjacents().stream()
					.filter(GraphVertex::isNotDiscovered)
					.forEach(adj -> {
						// depth-first: discovers all nodes adjacent to the current node, but adds them to a stack
						// so that they will be processed AFTER processing the adjacents of the nodes at the same distance.
						adj.markDiscovered(current, time++);
						discoveredStack.push(adj);
					});

			// put to a stack so that this node will be visited after we visit all of its adjacents
			visitedStack.push(current);
		}

		// the stack now contains the reachable vertices in depth-first search order
		List<GraphVertex<K, V>> reachableVertices = new ArrayList<>();
		while (!visitedStack.isEmpty()) {
			GraphVertex<K, V> current = visitedStack.pop();

			current.markVisited(time++);
			reachableVertices.add(current);
		}

		return reachableVertices;
	}

}
