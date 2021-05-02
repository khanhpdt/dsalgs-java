package vn.khanhpdt.playgrounds.algorithms.graphs;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;
import vn.khanhpdt.playgrounds.datastructures.graphs.Graph;
import vn.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

public class StronglyConnectedComponentsTest {

	@Test
	public void testNumberOfStronglyConnectedComponents() throws Exception {
		Graph<UUID, Integer> graph = new Graph<>();
		IntStream.range(0, 8).forEach(i -> graph.addVertex(UUID.randomUUID(), i));
		graph.addDirectedEdges(new int[][]{ {0, 1}, {1, 4}, {1, 5}, {1, 2}, {2, 3}, {2, 6},
				{3, 2}, {3, 7}, {4, 0}, {4, 5}, {5, 6}, {6, 5}, {6, 7}, {7, 7} });

		List<Graph<UUID, Integer>> stronglyConnectedComponents = StronglyConnectedComponents.of(graph);

		assertThat(stronglyConnectedComponents, hasSize(4));
	}

	@Test
	public void testStronglyConnectedComponents() throws Exception {
		Graph<UUID, Integer> graph = new Graph<>();
		IntStream.range(0, 8).forEach(i -> graph.addVertex(UUID.randomUUID(), i));
		graph.addDirectedEdges(new int[][]{ {0, 1}, {1, 4}, {1, 5}, {1, 2}, {2, 3}, {2, 6},
				{3, 2}, {3, 7}, {4, 0}, {4, 5}, {5, 6}, {6, 5}, {6, 7}, {7, 7} });

		List<Graph<UUID, Integer>> stronglyConnectedComponents = StronglyConnectedComponents.of(graph);

		assertThat(stronglyConnectedComponents, hasItem(hasVertices(graph.getVertices(new int[]{0, 1, 4}))));
		assertThat(stronglyConnectedComponents, hasItem(hasVertices(graph.getVertices(new int[]{2, 3}))));
		assertThat(stronglyConnectedComponents, hasItem(hasVertices(graph.getVertices(new int[]{5, 6}))));
		assertThat(stronglyConnectedComponents, hasItem(hasVertices(graph.getVertices(new int[]{7}))));
	}

	private static Matcher<? super Graph<UUID, Integer>> hasVertices(List<GraphVertex<UUID, Integer>> vertices) {
		return new TypeSafeMatcher<Graph<UUID, Integer>>() {
			@Override
			protected boolean matchesSafely(Graph<UUID, Integer> graph) {
				return vertices.stream().allMatch(graph::hasVertex);
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("a graph containing given vertices");
			}
		};
	}
}