package vn.khanhpdt.playgrounds.datastructures.trees;

import org.junit.Before;
import org.junit.Test;
import vn.khanhpdt.playgrounds.datastructures.TestUtils;
import vn.khanhpdt.playgrounds.datastructures.nodes.BinarySearchTreeNode;
import vn.khanhpdt.playgrounds.datastructures.nodes.BinarySearchTreeNullNode;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author khanhpdt
 */
public class BinarySearchTreeTest {

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
	public void testInsertFirstNode() throws Exception {
		BinarySearchTree<UUID, Integer> bst = new BinarySearchTree<>();

		BinarySearchTreeNode<UUID, Integer> firstNode = TestUtils.randomBinaryTreeNode();
		bst.insert(firstNode);

		assertThat(bst.getRoot(), is(firstNode));
	}

	@Test
	public void testSimpleInsert() throws Exception {
		assertThat(defaultBST.getRoot().getValue(), is(30));
		assertThat(defaultBST.getRoot().getLeft().getValue(), is(20));
		assertThat(defaultBST.getRoot().getLeft().getLeft().getValue(), is(15));
		assertThat(defaultBST.getRoot().getLeft().getRight().getValue(), is(25));
		assertThat(defaultBST.getRoot().getLeft().getRight().getLeft().getValue(), is(23));
		assertThat(defaultBST.getRoot().getLeft().getRight().getRight().getValue(), is(27));
		assertThat(defaultBST.getRoot().getRight().getValue(), is(35));
		assertThat(defaultBST.getRoot().getRight().getRight().getValue(), is(40));
	}

	@Test
	public void testParentsAfterInsert() throws Exception {
		assertThat(defaultBST.getRoot().getParent(), is(BinarySearchTreeNullNode.getInstance()));
		assertThat(defaultBST.getRoot().getLeft().getParent().getValue(), is(30));
		assertThat(defaultBST.getRoot().getLeft().getLeft().getParent().getValue(), is(20));
		assertThat(defaultBST.getRoot().getLeft().getRight().getParent().getValue(), is(20));
		assertThat(defaultBST.getRoot().getLeft().getRight().getLeft().getParent().getValue(), is(25));
		assertThat(defaultBST.getRoot().getLeft().getRight().getRight().getParent().getValue(), is(25));
		assertThat(defaultBST.getRoot().getRight().getParent().getValue(), is(30));
		assertThat(defaultBST.getRoot().getRight().getRight().getParent().getValue(), is(35));
	}

	@Test
	public void testRemoveRoot() throws Exception {
		defaultBST.remove(30);
		assertInOrderTraversal(defaultBST, 15, 20, 23, 25, 27, 35, 40);
	}

	private void assertInOrderTraversal(BinarySearchTree<UUID, Integer> bst, Integer... nodeValues) {
		List<BinarySearchTreeNode<UUID, Integer>> nodes = bst.traverseInOrder();
		for (int i = 0; i < nodes.size(); i++) {
			assertThat(nodes.get(i).getValue(), is(nodeValues[i]));
		}
	}

	@Test
	public void testRemoveLeaf() throws Exception {
		defaultBST.remove(15);
		assertInOrderTraversal(defaultBST, 20, 23, 25, 27, 30, 35, 40);
	}

	@Test
	public void testRemoveNodeWithOneChild() throws Exception {
		defaultBST.remove(35);
		assertInOrderTraversal(defaultBST, 15, 20, 23, 25, 27, 30, 40);
	}

	@Test
	public void testRemoveNodeWithTwoChildren() throws Exception {
		defaultBST.remove(20);
		assertInOrderTraversal(defaultBST, 15, 23, 25 , 27, 30, 35, 40);
	}

	@Test
	public void testParentAfterRemoveNodeWithTwoChildren() throws Exception {
		defaultBST.remove(20);

		assertThat(defaultBST.findNodeByValue(23).getParent().getValue(), is(30));
		assertThat(defaultBST.findNodeByValue(25).getParent().getValue(), is(23));
		assertThat(defaultBST.findNodeByValue(15).getParent().getValue(), is(23));
	}

	@Test
	public void testFindSuccessor_goDown() {
		BinarySearchTreeNode<UUID, Integer> successor = defaultBST.findSuccessorOf(defaultBST.findNodeByValue(20));
		assertThat(successor.getValue(), is(23));
	}

	@Test
	public void testFindSuccessor_goUp() {
		BinarySearchTreeNode<UUID, Integer> successor = defaultBST.findSuccessorOf(defaultBST.findNodeByValue(27));
		assertThat(successor.getValue(), is(30));
	}

	@Test
	public void testFindSuccessor_noneFound() {
		BinarySearchTreeNode<UUID, Integer> successor = defaultBST.findSuccessorOf(defaultBST.findNodeByValue(40));
		assertThat(successor, is(BinarySearchTreeNullNode.getInstance()));
	}

	@Test
	public void testFindPredecessor_goDown() {
		BinarySearchTreeNode<UUID, Integer> successor = defaultBST.findPredecessorOf(defaultBST.findNodeByValue(30));
		assertThat(successor.getValue(), is(27));
	}

	@Test
	public void testFindPredecessor_goUp() {
		BinarySearchTreeNode<UUID, Integer> successor = defaultBST.findPredecessorOf(defaultBST.findNodeByValue(23));
		assertThat(successor.getValue(), is(20));
	}

	@Test
	public void testFindPredecessor_noneFound() {
		BinarySearchTreeNode<UUID, Integer> successor = defaultBST.findPredecessorOf(defaultBST.findNodeByValue(15));
		assertThat(successor, is(BinarySearchTreeNullNode.getInstance()));
	}

}
