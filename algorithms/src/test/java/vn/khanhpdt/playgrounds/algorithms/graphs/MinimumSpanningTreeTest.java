package vn.khanhpdt.playgrounds.algorithms.graphs;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;
import vn.khanhpdt.playgrounds.datastructures.graphs.Graph;
import vn.khanhpdt.playgrounds.datastructures.graphs.GraphEdge;
import vn.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;

public class MinimumSpanningTreeTest {

    @Test
    public void testKruskal() throws Exception {
        Graph<UUID, Integer> graph = createGraph();

        List<GraphEdge<UUID, Integer>> mstEdges = new KruskalMST<>(graph).get();

        assertThat(mstEdges, Matchers.hasSize(8));
        assertThat(mstEdges, Matchers.hasItem(isEdgeWith(graph, new int[]{6, 7, 1})));
        assertThat(mstEdges, Matchers.hasItem(isEdgeWith(graph, new int[]{2, 8, 2})));
        assertThat(mstEdges, Matchers.hasItem(isEdgeWith(graph, new int[]{5, 6, 2})));
        assertThat(mstEdges, Matchers.hasItem(isEdgeWith(graph, new int[]{0, 1, 4})));
        assertThat(mstEdges, Matchers.hasItem(isEdgeWith(graph, new int[]{2, 5, 4})));
        assertThat(mstEdges, Matchers.hasItem(isEdgeWith(graph, new int[]{2, 3, 7})));
        assertThat(mstEdges, Matchers.hasItem(isEdgeWith(graph, new int[]{0, 7, 8})));
        assertThat(mstEdges, Matchers.hasItem(isEdgeWith(graph, new int[]{3, 4, 9})));
    }

    @Test
    public void testPrim() throws Exception {
        Graph<UUID, Integer> graph = createGraph();

        List<GraphEdge<UUID, Integer>> mstEdges = new PrimMST<>(graph).get();

        assertThat(mstEdges, Matchers.hasSize(8));
        assertThat(mstEdges, Matchers.hasItem(isEdgeWith(graph, new int[]{0, 1, 4})));

        Iterable<Matcher<? super Iterable<? super GraphEdge<UUID, Integer>>>> matchers = List.of(
                Matchers.hasItem(isEdgeWith(graph, new int[]{1, 2, 8})),
                Matchers.hasItem(isEdgeWith(graph, new int[]{0, 7, 8}))
        );

        assertThat(mstEdges, Matchers.anyOf(matchers));
        assertThat(mstEdges, Matchers.hasItem(isEdgeWith(graph, new int[]{2, 8, 2})));
        assertThat(mstEdges, Matchers.hasItem(isEdgeWith(graph, new int[]{2, 5, 4})));
        assertThat(mstEdges, Matchers.hasItem(isEdgeWith(graph, new int[]{5, 6, 2})));
        assertThat(mstEdges, Matchers.hasItem(isEdgeWith(graph, new int[]{6, 7, 1})));
        assertThat(mstEdges, Matchers.hasItem(isEdgeWith(graph, new int[]{2, 3, 7})));
        assertThat(mstEdges, Matchers.hasItem(isEdgeWith(graph, new int[]{3, 4, 9})));
    }

    private Graph<UUID, Integer> createGraph() {
        Graph<UUID, Integer> graph = new Graph<>();
        IntStream.range(0, 9).forEach(i -> graph.addVertex(UUID.randomUUID(), i));
        graph.addEdges(new int[][]{
                {0, 1, 4}, {0, 7, 8}, {1, 2, 8}, {1, 7, 11}, {2, 3, 7},
                {2, 5, 4}, {2, 8, 2}, {3, 4, 9}, {3, 5, 14}, {4, 5, 10},
                {5, 6, 2}, {6, 7, 1}, {6, 8, 6}, {7, 8, 7}});
        return graph;
    }

    private static Matcher<? super GraphEdge> isEdgeWith(Graph graph, int[] edgeDescription) {
        return new TypeSafeMatcher<GraphEdge>() {
            @Override
            protected boolean matchesSafely(GraphEdge edge) {
                GraphVertex vertex1 = graph.getVertex(edgeDescription[0]);
                GraphVertex vertex2 = graph.getVertex(edgeDescription[1]);
                int weight = edgeDescription[2];

                boolean matchedVertices = (edge.getFromVertex().equals(vertex1) && edge.getToVertex().equals(vertex2))
                        || (edge.getFromVertex().equals(vertex2) && edge.getToVertex().equals(vertex1));
                boolean matchedWeight = edge.getWeight() == weight;

                return matchedVertices && matchedWeight;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("edge with the specified description " + Arrays.toString(edgeDescription));
            }
        };
    }
}
