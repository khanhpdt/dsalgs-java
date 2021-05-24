package vn.khanhpdt.playgrounds.algorithms.graphs.mst;

import vn.khanhpdt.playgrounds.datastructures.graphs.Graph;
import vn.khanhpdt.playgrounds.datastructures.graphs.GraphEdge;
import vn.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;
import vn.khanhpdt.playgrounds.datastructures.queues.MinPriorityQueue;

import java.util.ArrayList;
import java.util.List;

public class PrimMST<K, V> extends MinimumSpanningTree<K, V> {

	public PrimMST(Graph<K, V> graph) {
		super(graph);
	}

	@Override
	public List<GraphEdge<K, V>> get() {
		// the value of each vertex will represent the minimum weight among the edges connecting the vertex
		// to the vertices already in the MST
		getGraph().getVertices().forEach(v -> v.setMinWeightToMST(Double.MAX_VALUE));

		// use a min-priority queue to accelerate the process of finding the safe edge and also to hold
		// the vertices not yet in the MST
		MinPriorityQueue<GraphVertex<K, V>> minPriorityQueue = new MinPriorityQueue<>(getGraph().getVertices(),
				(v1, v2) -> Double.compare(v1.getMinWeightToMST(), v2.getMinWeightToMST()));

		List<GraphEdge<K, V>> result = new ArrayList<>();
		while (minPriorityQueue.isNotEmpty()) {
			// this vertex is not in the MST and the edge connecting it to a vertex in the MST is the minimum.
			// in other words, it is the vertex in the safe edge.
			GraphVertex<K, V> v = minPriorityQueue.extractMin();

			if (v.getPredecessor()!= null) {
				// safe edge added
				result.add(v.getPredecessor().getEdgeTo(v));
			}

			v.getAdjacents().forEach(adj -> {
				double edgeWeight = v.getWeightOfEdgeTo(adj);
				// make sure that the value of each vertex represents the minimum weight among the edges
				// connecting the vertex to the vertices already in the MST
				if (minPriorityQueue.contains(adj) && adj.getMinWeightToMST() > edgeWeight) {
					adj.setPredecessor(v);
					minPriorityQueue.decreaseKey(adj, edgeWeight, GraphVertex::setMinWeightToMST);
				}
			});
		}

		return result;
	}

}