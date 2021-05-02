package vn.khanhpdt.playgrounds.datastructures.graphs;

import org.hamcrest.Matchers;
import org.junit.Test;
import vn.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;

import java.awt.Color;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class GraphTest {

	@Test
	public void testAddVertex() throws Exception {
		Graph<UUID, Integer> graph = new Graph<>();

		UUID vertexKey = UUID.randomUUID();
		graph.addVertex(vertexKey);

		GraphVertex<UUID, Integer> vertex = graph.getVertex(vertexKey);
		assertThat(vertex.getColor(), is(Color.WHITE));
	}

	@Test
	public void testAddEdge() throws Exception {
		Graph<UUID, Integer> graph = new Graph<>();
		UUID vertex1Key = UUID.randomUUID();
		GraphVertex<UUID, Integer> vertex1 = graph.addVertex(vertex1Key);
		UUID vertex2Key = UUID.randomUUID();
		GraphVertex<UUID, Integer> vertex2 = graph.addVertex(vertex2Key);

		graph.addEdge(vertex1, vertex2);

		assertThat(vertex1.getAdjacents(), Matchers.hasItem(vertex2));
		assertThat(vertex2.getAdjacents(), Matchers.hasItem(vertex1));
	}

	@Test
	public void testAddEdgeByIndexes() throws Exception {
		Graph<UUID, Integer> graph = new Graph<>();
		UUID vertex1Key = UUID.randomUUID();
		GraphVertex<UUID, Integer> vertex1 = graph.addVertex(vertex1Key);
		UUID vertex2Key = UUID.randomUUID();
		GraphVertex<UUID, Integer> vertex2 = graph.addVertex(vertex2Key);

		graph.addEdge(0, 1);

		assertThat(vertex1.getAdjacents(), Matchers.hasItem(vertex2));
		assertThat(vertex2.getAdjacents(), Matchers.hasItem(vertex1));
	}

}
