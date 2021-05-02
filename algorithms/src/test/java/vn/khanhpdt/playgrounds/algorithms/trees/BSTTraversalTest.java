package vn.khanhpdt.playgrounds.algorithms.trees;

import org.junit.Before;
import org.junit.Test;
import vn.khanhpdt.playgrounds.datastructures.nodes.BinarySearchTreeNode;
import vn.khanhpdt.playgrounds.datastructures.nodes.BinaryTreeNode;
import vn.khanhpdt.playgrounds.datastructures.trees.BinarySearchTree;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BSTTraversalTest {

	private static final List<Integer> IN_ORDER_INDEXES = Arrays.asList(4, 1, 7, 2, 5, 0, 3, 6);
	private static final List<Integer> PRE_ORDER_INDEXES = Arrays.asList(0, 1, 4, 2, 7, 5, 3, 6);
	private static final List<Integer> POST_ORDER_INDEXES = Arrays.asList(4, 7, 5, 2, 1, 6, 3, 0);

	private List<BinarySearchTreeNode<UUID, Integer>> defaultNodes;
	private BinarySearchTree<UUID, Integer> defaultBST;

	@Before
	public void init() {
		defaultNodes = getDefaultNodes();
		defaultBST = getDefaultTree(defaultNodes);
	}

	private List<BinarySearchTreeNode<UUID, Integer>> getDefaultNodes() {
		return Stream.of(30, 20, 25, 35, 15, 27, 40, 23)
				.map(value -> BinarySearchTreeNode.from(UUID.randomUUID(), value))
				.collect(Collectors.toList());
	}

	private BinarySearchTree<UUID, Integer> getDefaultTree(List<BinarySearchTreeNode<UUID, Integer>> defaultNodes) {
		BinarySearchTree<UUID, Integer> bst = new BinarySearchTree<>();
		defaultNodes.forEach(bst::insert);
		return bst;
	}

	@Test
	public void testInOrderTraversalRecursive() throws Exception {
		List<BinaryTreeNode<UUID, Integer>> inOrderNodes = InOrderTraversalRecursive.traverse(defaultBST.getRoot());
		IntStream.range(0, defaultNodes.size())
				.forEach(i -> assertThat(inOrderNodes.get(i), is(defaultNodes.get(IN_ORDER_INDEXES.get(i)))));
	}

	@Test
	public void testInOrderTraversalIterative() throws Exception {
		List<BinaryTreeNode<UUID, Integer>> inOrderNodes = InOrderTraversalIterative.traverse(defaultBST.getRoot());
		IntStream.range(0, defaultNodes.size())
				.forEach(i -> assertThat(inOrderNodes.get(i), is(defaultNodes.get(IN_ORDER_INDEXES.get(i)))));
	}

	@Test
	public void testTraversePreOrderIterative() throws Exception {
		List<BinaryTreeNode<UUID, Integer>> preOrderNodes = PreOrderTraversalIterative.traverse(defaultBST.getRoot());
		IntStream.range(0, defaultNodes.size())
				.forEach(i -> assertThat(preOrderNodes.get(i), is(defaultNodes.get(PRE_ORDER_INDEXES.get(i)))));
	}

	@Test
	public void testTraversePreOrderRecursive() throws Exception {
		List<BinaryTreeNode<UUID, Integer>> preOrderNodes = PreOrderTraversalRecursive.traverse(defaultBST.getRoot());
		IntStream.range(0, defaultNodes.size())
				.forEach(i -> assertThat(preOrderNodes.get(i), is(defaultNodes.get(PRE_ORDER_INDEXES.get(i)))));
	}

	@Test
	public void testTraversePostOrderIterative() throws Exception {
		List<BinaryTreeNode<UUID, Integer>> postOrderNodes = PostOrderTraversalIterative.traverse(defaultBST.getRoot());
		IntStream.range(0, defaultNodes.size())
				.forEach(i -> assertThat(postOrderNodes.get(i), is(defaultNodes.get(POST_ORDER_INDEXES.get(i)))));
	}

	@Test
	public void testTraversePostOrderRecursive() throws Exception {
		List<BinaryTreeNode<UUID, Integer>> postOrderNodes = PostOrderTraversalRecursive.traverse(defaultBST.getRoot());
		IntStream.range(0, defaultNodes.size())
				.forEach(i -> assertThat(postOrderNodes.get(i), is(defaultNodes.get(POST_ORDER_INDEXES.get(i)))));
	}

}
