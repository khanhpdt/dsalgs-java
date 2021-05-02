package vn.khanhpdt.playgrounds.datastructures.trees;

import org.junit.Test;
import vn.khanhpdt.playgrounds.datastructures.nodes.Node;
import vn.khanhpdt.playgrounds.datastructures.nodes.RedBlackTreeNode;

import java.awt.Color;
import java.util.*;

import static java.awt.Color.BLACK;
import static java.awt.Color.RED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RedBlackTreeTest {

	private static final Random RANDOM = new Random();

	private RedBlackTree<UUID, Integer> createRBT(int... nodeValues) {
		RedBlackTree<UUID, Integer> result = new RedBlackTree<>();
		for (int nodeValue : nodeValues) {
			result.insert(createRBTNode(nodeValue));
		}
		return result;
	}

	private RedBlackTreeNode<UUID, Integer> createRBTNode(int value) {
		return new RedBlackTreeNode<>(new Node<>(UUID.randomUUID(), value));
	}

	private RedBlackTreeNode<UUID, Integer> randomRBTNode() {
		return new RedBlackTreeNode<>(new Node<>(UUID.randomUUID(), RANDOM.nextInt()));
	}

	@Test
	public void testInsert_firstNode() {
		RedBlackTree<UUID, Integer> rbt = new RedBlackTree<>();

		rbt.insert(randomRBTNode());

		assertThat(rbt.getRoot().getColor(), is(BLACK));
	}

	@Test
	public void testInsert_parentBlack() {
		RedBlackTree<UUID, Integer> rbt = createRBT(11, 2, 14, 1);

		RedBlackTreeNode<UUID, Integer> newNode = createRBTNode(7);
		rbt.insert(newNode);

		RedBlackTreeNode<UUID, Integer> insertedNode = rbt.findNodeByValue(newNode.getValue());
		assertThat(insertedNode.getColor(), is(RED));
		assertThat(insertedNode.getParent().getValue(), is(2));
		assertThat(insertedNode.getParent().getColor(), is(BLACK));
	}

	@Test
	public void testInsert_parentRed_uncleRed() {
		RedBlackTree<UUID, Integer> rbt = createRBT(11, 2, 14);

		RedBlackTreeNode<UUID, Integer> newNode = createRBTNode(1);
		rbt.insert(newNode);

		RedBlackTreeNode<UUID, Integer> insertedNode = rbt.findNodeByValue(newNode.getValue());
		assertThat(insertedNode.getColor(), is(RED));
		assertThat(insertedNode.getParent().getValue(), is(2));
		assertThat(insertedNode.getParent().getColor(), is(BLACK));
	}

	@Test
	public void testInsert_nodeColors() {
		RedBlackTree<UUID, Integer> rbt = new RedBlackTree<>();

		rbt.insert(createRBTNode(11));
		assertNodeColors(rbt, Collections.singletonList(11), Collections.singletonList(BLACK));

		rbt.insert(createRBTNode(2));
		assertNodeColors(rbt, Arrays.asList(11, 2), Arrays.asList(BLACK, RED));

		rbt.insert(createRBTNode(14));
		assertNodeColors(rbt, Arrays.asList(11, 2, 14), Arrays.asList(BLACK, RED, RED));

		rbt.insert(createRBTNode(1));
		assertNodeColors(rbt, Arrays.asList(11, 2, 14, 1), Arrays.asList(BLACK, BLACK, BLACK, RED));

		rbt.insert(createRBTNode(7));
		assertNodeColors(rbt, Arrays.asList(11, 2, 14, 1, 7), Arrays.asList(BLACK, BLACK, BLACK, RED, RED));

		rbt.insert(createRBTNode(5));
		assertNodeColors(rbt, Arrays.asList(11, 2, 14, 1, 7, 5), Arrays.asList(BLACK, RED, BLACK, BLACK, BLACK, RED));

		rbt.insert(createRBTNode(8));
		assertNodeColors(rbt, Arrays.asList(11, 2, 14, 1, 7, 5, 8), Arrays.asList(BLACK, RED, BLACK, BLACK, BLACK, RED, RED));

		rbt.insert(createRBTNode(12));
		assertNodeColors(rbt, Arrays.asList(11, 2, 14, 1, 7, 5, 8, 12), Arrays.asList(BLACK, RED, BLACK, BLACK, BLACK, RED, RED, RED));

		rbt.insert(createRBTNode(15));
		assertNodeColors(rbt, Arrays.asList(11, 2, 14, 1, 7, 5, 8, 12, 15), Arrays.asList(BLACK, RED, BLACK, BLACK, BLACK, RED, RED, RED, RED));

		rbt.insert(createRBTNode(10));
		assertNodeColors(rbt, Arrays.asList(11, 2, 14, 1, 7, 5, 8, 12, 15, 10), Arrays.asList(RED, RED, BLACK, BLACK, BLACK, BLACK, BLACK, RED, RED, RED));
	}

	private static void assertNodeColors(RedBlackTree<UUID, Integer> rbt, List<Integer> nodeValues, List<Color> nodeColors) {
		for (int i = 0; i < nodeValues.size(); i++) {
			assertThat("node " + nodeValues.get(i) + " : " + nodeColors.get(i),
					rbt.findNodeByValue(nodeValues.get(i)).getColor(), is(nodeColors.get(i)));
		}
	}

	@Test
	public void testInsert_treeStructure() {
		RedBlackTree<UUID, Integer> rbt = createRBT(11, 2, 14, 1, 7, 5, 8, 12, 15, 10);

		assertThat(rbt.getRoot().getValue(), is(7));
		assertThat(rbt.getRoot().getLeft().getValue(), is(2));
		assertThat(rbt.getRoot().getLeft().getLeft().getValue(), is(1));
		assertThat(rbt.getRoot().getLeft().getRight().getValue(), is(5));
		assertThat(rbt.getRoot().getRight().getValue(), is(11));
		assertThat(rbt.getRoot().getRight().getLeft().getValue(), is(8));
		assertThat(rbt.getRoot().getRight().getLeft().getRight().getValue(), is(10));
		assertThat(rbt.getRoot().getRight().getRight().getValue(), is(14));
		assertThat(rbt.getRoot().getRight().getRight().getLeft().getValue(), is(12));
		assertThat(rbt.getRoot().getRight().getRight().getRight().getValue(), is(15));
	}

	@Test
	public void testRemove() {
		RedBlackTree<UUID, Integer> rbt = createRBT(11, 2, 14, 1, 7, 5);

		rbt.remove(11);
		assertNodeColors(rbt, Arrays.asList(2, 14, 1, 7, 5), Arrays.asList(BLACK, BLACK, BLACK, RED, BLACK));
		assertThat(rbt.getRoot().getValue(), is(2));
		assertThat(rbt.getRoot().getLeft().getValue(), is(1));
		assertThat(rbt.getRoot().getRight().getValue(), is(7));
		assertThat(rbt.getRoot().getRight().getLeft().getValue(), is(5));
		assertThat(rbt.getRoot().getRight().getRight().getValue(), is(14));

		rbt.remove(7);
		assertNodeColors(rbt, Arrays.asList(2, 14, 1, 5), Arrays.asList(BLACK, BLACK, BLACK, RED));
		assertThat(rbt.getRoot().getValue(), is(2));
		assertThat(rbt.getRoot().getLeft().getValue(), is(1));
		assertThat(rbt.getRoot().getRight().getValue(), is(14));
		assertThat(rbt.getRoot().getRight().getLeft().getValue(), is(5));

		rbt.remove(2);
		assertNodeColors(rbt, Arrays.asList(14, 1, 5), Arrays.asList(BLACK, BLACK, BLACK));
		assertThat(rbt.getRoot().getValue(), is(5));
		assertThat(rbt.getRoot().getLeft().getValue(), is(1));
		assertThat(rbt.getRoot().getRight().getValue(), is(14));

		rbt.remove(1);
		assertNodeColors(rbt, Arrays.asList(14, 5), Arrays.asList(RED, BLACK));
		assertThat(rbt.getRoot().getValue(), is(5));
		assertThat(rbt.getRoot().getRight().getValue(), is(14));

		rbt.remove(14);
		assertNodeColors(rbt, Collections.singletonList(5), Collections.singletonList(BLACK));
		assertThat(rbt.getRoot().getValue(), is(5));
	}

}
