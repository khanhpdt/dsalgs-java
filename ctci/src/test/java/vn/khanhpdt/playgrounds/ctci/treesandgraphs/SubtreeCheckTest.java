package vn.khanhpdt.playgrounds.ctci.treesandgraphs;

import org.junit.Before;
import org.junit.Test;
import vn.khanhpdt.playgrounds.ctci.treesandgraphs.SubtreeCheck;
import vn.khanhpdt.playgrounds.datastructures.nodes.BinarySearchTreeNode;
import vn.khanhpdt.playgrounds.datastructures.trees.BinarySearchTree;

import java.util.UUID;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author khanhpdt
 */
public class SubtreeCheckTest {

	private BinarySearchTree<UUID, Integer> binaryTree;

	@Before
	public void init() {
		initTrees();
	}

	private void initTrees() {
		binaryTree = new BinarySearchTree<>();
		Stream.of(30, 20, 25, 35, 15, 32, 28, 34, 10, 23)
				.map(value -> BinarySearchTreeNode.from(UUID.randomUUID(), value))
				.forEach(binaryTree::insert);
	}

	@Test
	public void testSubtree() {
		BinarySearchTree<UUID, Integer> binarySubTree = new BinarySearchTree<>();
		Stream.of(20, 25, 15, 28, 10, 23)
				.map(value -> BinarySearchTreeNode.from(UUID.randomUUID(), value))
				.forEach(binarySubTree::insert);

		assertThat(SubtreeCheck.isSubtree(binaryTree, binarySubTree), is(true));
	}

	@Test
	public void testNotSubtree() {
		BinarySearchTree<UUID, Integer> binarySubTree = new BinarySearchTree<>();
		Stream.of(20, 25, 15, 28, 10)
				.map(value -> BinarySearchTreeNode.from(UUID.randomUUID(), value))
				.forEach(binarySubTree::insert);

		assertThat(SubtreeCheck.isSubtree(binaryTree, binarySubTree), is(false));
	}

}