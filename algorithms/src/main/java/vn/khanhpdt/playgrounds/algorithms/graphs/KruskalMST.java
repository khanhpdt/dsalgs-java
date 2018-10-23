package vn.khanhpdt.playgrounds.algorithms.graphs;

import vn.khanhpdt.playgrounds.datastructures.graphs.Graph;
import vn.khanhpdt.playgrounds.datastructures.graphs.GraphEdge;
import vn.khanhpdt.playgrounds.datastructures.nodes.DisjointSetNode;
import vn.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;
import vn.khanhpdt.playgrounds.datastructures.sets.DisjointSets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author khanhpdt
 */
public class KruskalMST<K, V> extends MinimumSpanningTree<K, V> {

	public KruskalMST(Graph<K, V> graph) {
		super(graph);
	}

	@Override
	public List<GraphEdge<K, V>> get() {
		Map<GraphVertex<K, V>, DisjointSetNode<K, V>> disjointSetNodes = getGraph().getVertices().stream()
				.collect(Collectors.toMap(Function.identity(), v -> new DisjointSetNode<>(v.getKey())));

		disjointSetNodes.values().forEach(node -> DisjointSets.makeSet(Collections.singletonList(node)));

		// sort the edges to make it easy to loop over them in ascending order
		List<GraphEdge<K, V>> edges = getGraph().getEdges();
		Collections.sort(edges, (e1, e2) -> Double.compare(e1.getWeight(), e2.getWeight()));

		List<GraphEdge<K, V>> result = new ArrayList<>();
		edges.forEach(e -> {
			DisjointSetNode nodeForFromVertex = disjointSetNodes.get(e.getFromVertex());
			DisjointSetNode nodeForToVertex = disjointSetNodes.get(e.getToVertex());
			// Only add the new edge if it is formed by vertices in different sets. If nodes are in the same set, they
			// are in the same connected component, meaning that there is already a link between them. Now if we add a
			// direct link between them to the tree, a cycle will appear, which makes the tree not a tree anymore.
			if (!DisjointSets.findSet(nodeForFromVertex).equals(DisjointSets.findSet(nodeForToVertex))) {
				result.add(e);
				DisjointSets.union(nodeForFromVertex, nodeForToVertex);
			}
		});

		return result;
	}
}
