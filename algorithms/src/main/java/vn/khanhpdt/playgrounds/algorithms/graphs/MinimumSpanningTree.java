package vn.khanhpdt.playgrounds.algorithms.graphs;

import vn.khanhpdt.playgrounds.datastructures.graphs.Graph;
import vn.khanhpdt.playgrounds.datastructures.graphs.GraphEdge;

import java.util.List;

/**
 * Main idea is based on Corollary 23.2 in Introduction to algorithms, 3rd.
 * <p>
 * Basically, the MST algorithm forms an MST by adding the light edge connecting two different components into the MST.
 * The added light edge is also called the safe edge.
 * </p>
 *
 */
public abstract class MinimumSpanningTree<K, V> {

	private Graph<K, V> graph;

	protected MinimumSpanningTree(Graph<K, V> graph) {
		this.graph = graph;
	}

	public Graph<K, V> getGraph() {
		return graph;
	}

	protected abstract List<GraphEdge<K, V>> get();

}
