package vn.khanhpdt.playgrounds.datastructures.sets;

import org.hamcrest.Matchers;
import org.junit.Test;
import vn.khanhpdt.playgrounds.datastructures.nodes.DisjointSetNode;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author khanhpdt
 */
public class DisjointSetTest {

	@Test
	public void testMakeSet() throws Exception {
		DisjointSetNode<UUID, Integer> node = randomNode();

		DisjointSet<UUID, Integer> set = DisjointSets.makeSet(Collections.singletonList(node));

		assertThat(set.getHead(), is(node));
		assertThat(set.getTail(), is(node));
		assertThat(node.getSet(), is(set));
	}

	private DisjointSetNode<UUID, Integer> randomNode() {
		return new DisjointSetNode<>(UUID.randomUUID());
	}

	@Test
	public void testFindSet() throws Exception {
		DisjointSetNode<UUID, Integer> node = randomNode();
		DisjointSet<UUID, Integer> set = DisjointSets.makeSet(Collections.singletonList(node));

		DisjointSet<UUID, Integer> enclosingSet = DisjointSets.findSet(node);

		assertThat(enclosingSet, is(set));
	}

	@Test
	public void testUnion() throws Exception {
		DisjointSetNode<UUID, Integer> node1 = randomNode();
		DisjointSets.makeSet(Collections.singletonList(node1));
		DisjointSetNode<UUID, Integer> node2 = randomNode();
		DisjointSets.makeSet(Collections.singletonList(node2));

		DisjointSet<UUID, Integer> union = DisjointSets.union(node1, node2);

		assertThat(node1.getSet(), is(union));
		assertThat(node2.getSet(), is(union));
	}

	@Test
	public void testUnion_resultSetIsTheLongerLongerSet() throws Exception {
		DisjointSetNode<UUID, Integer> node1 = randomNode();
		DisjointSetNode<UUID, Integer> node2 = randomNode();
		DisjointSetNode<UUID, Integer> node3 = randomNode();

		DisjointSet<UUID, Integer> set1 = DisjointSets.makeSet(Arrays.asList(node1, node2));
		DisjointSets.makeSet(Collections.singletonList(node3));

		DisjointSet<UUID, Integer> union = DisjointSets.union(node1, node3);

		assertThat(union, is(set1));
	}

	@Test
	public void testUnion_shorterSetDestroyed() throws Exception {
		DisjointSetNode<UUID, Integer> node1 = randomNode();
		DisjointSetNode<UUID, Integer> node2 = randomNode();
		DisjointSetNode<UUID, Integer> node3 = randomNode();

		DisjointSets.makeSet(Arrays.asList(node1, node2));
		DisjointSet<UUID, Integer> set2 = DisjointSets.makeSet(Collections.singletonList(node3));

		DisjointSets.union(node1, node3);

		assertThat(set2.size(), is(0));
		assertThat(set2.getHead(), Matchers.nullValue());
	}
}
