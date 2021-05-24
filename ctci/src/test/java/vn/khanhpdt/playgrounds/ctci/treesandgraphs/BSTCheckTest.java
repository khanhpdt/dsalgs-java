package vn.khanhpdt.playgrounds.ctci.treesandgraphs;

import org.junit.Before;
import org.junit.Test;
import vn.khanhpdt.playgrounds.ctci.treesandgraphs.BSTCheck;
import vn.khanhpdt.playgrounds.datastructures.nodes.BinarySearchTreeNode;
import vn.khanhpdt.playgrounds.datastructures.nodes.BinaryTreeNode;
import vn.khanhpdt.playgrounds.datastructures.trees.BinarySearchTree;

import java.util.UUID;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BSTCheckTest {

	private BinarySearchTree<UUID, Integer> bst;

	@Before
	public void init() {
		initBST();
	}

	private void initBST() {
		bst = new BinarySearchTree<>();
		Stream.of(30, 20, 25, 35, 15, 32, 28, 34)
				.map(value -> BinarySearchTreeNode.from(UUID.randomUUID(), value))
				.forEach(bst::insert);
	}

	@Test
	public void testBST() {
		assertThat(BSTCheck.isBST(bst.getRoot()), is(true));
	}

	@Test
	public void testNonBST() {
		// make a node smaller than its left node to break the bst properties
		BinaryTreeNode<UUID, Integer> node = bst.getRoot().getLeft().getRight();
		node.setLeft(BinarySearchTreeNode.from(UUID.randomUUID(), node.getValue() + 1));

		assertThat(BSTCheck.isBST(bst.getRoot()), is(false));
	}

	@Test
	public void testNonBST_2() {
		// make node 20 larger than one node in its right subtree
		BinaryTreeNode<UUID, Integer> node = bst.getRoot().getLeft().getRight();
		node.setLeft(BinarySearchTreeNode.from(UUID.randomUUID(), 18));

		assertThat(BSTCheck.isBST(bst.getRoot()), is(false));
	}

	@Test
	public void testBST2() {
		assertThat(BSTCheck.isBST2(bst.getRoot()), is(true));
	}

	@Test
	public void testNonBST2() {
		// make a node smaller than its left node to break the bst properties
		BinaryTreeNode<UUID, Integer> node = bst.getRoot().getLeft().getRight();
		node.setLeft(BinarySearchTreeNode.from(UUID.randomUUID(), node.getValue() + 1));

		assertThat(BSTCheck.isBST2(bst.getRoot()), is(false));
	}

	@Test
	public void testNonBST2_2() {
		// make node 20 larger than one node in its right subtree
		BinaryTreeNode<UUID, Integer> node = bst.getRoot().getLeft().getRight();
		node.setLeft(BinarySearchTreeNode.from(UUID.randomUUID(), 18));

		assertThat(BSTCheck.isBST2(bst.getRoot()), is(false));
	}

}