package vn.khanhpdt.playgrounds.ctci.treesandgraphs;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;
import vn.khanhpdt.playgrounds.ctci.treesandgraphs.AllPathsToSum;
import vn.khanhpdt.playgrounds.datastructures.nodes.BinarySearchTreeNode;
import vn.khanhpdt.playgrounds.datastructures.nodes.BinaryTreeNode;
import vn.khanhpdt.playgrounds.datastructures.trees.BinarySearchTree;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

/**
 * @author khanhpdt
 */
public class AllPathsToSumTest {

	private BinarySearchTree<UUID, Integer> bst;

	@Before
	public void init() {
		initBST();
	}

	private void initBST() {
		bst = new BinarySearchTree<>();
	}

	@Test
	public void test_PathsFromRoot() {
		Stream.of(30, 20, 45, 15, 25, 10, 23, 28)
				.map(value -> BinarySearchTreeNode.from(UUID.randomUUID(), value))
				.forEach(bst::insert);

		List<List<BinaryTreeNode<UUID, Integer>>> paths = AllPathsToSum.find(bst, 75);

		assertThat(paths, hasSize(3));
		assertThat(paths, hasItem(hasNodes(30, 20, 15, 10)));
		assertThat(paths, hasItem(hasNodes(30, 20, 25)));
		assertThat(paths, hasItem(hasNodes(30, 45)));
	}

	@Test
	public void test_PathsInternalNodes() {
		Stream.of(30, 20, 45, 15, 25, 10, 23, 28)
				.map(value -> BinarySearchTreeNode.from(UUID.randomUUID(), value))
				.forEach(bst::insert);

		List<List<BinaryTreeNode<UUID, Integer>>> paths = AllPathsToSum.find(bst, 45);

		assertThat(paths, hasSize(3));
		assertThat(paths, hasItem(hasNodes(20, 15, 10)));
		assertThat(paths, hasItem(hasNodes(20, 25)));
		assertThat(paths, hasItem(hasNodes(45)));
	}

	private static Matcher<List<BinaryTreeNode<UUID, Integer>>> hasNodes(int... expectedNodeValues) {
		return new TypeSafeMatcher<List<BinaryTreeNode<UUID, Integer>>>() {
			@Override
			protected boolean matchesSafely(List<BinaryTreeNode<UUID, Integer>> nodes) {
				for (int i = 0; i < nodes.size(); i++) {
					if (nodes.get(i).getValue() != expectedNodeValues[i]) {
						return false;
					}
				}
				return true;
			}

			@Override
			public void describeTo(Description description) {

			}
		};
	}

	@Test
	public void test_onlyRoot() {
		Stream.of(30)
				.map(value -> BinarySearchTreeNode.from(UUID.randomUUID(), value))
				.forEach(bst::insert);

		List<List<BinaryTreeNode<UUID, Integer>>> paths = AllPathsToSum.find(bst, 30);

		assertThat(paths, hasSize(1));
		assertThat(paths, hasItem(hasNodes(30)));
	}

	@Test
	public void test_NegativeNumbers() {
		Stream.of(30, 20, 45, 15, 25, 10, 23, 28, -5, 5)
				.map(value -> BinarySearchTreeNode.from(UUID.randomUUID(), value))
				.forEach(bst::insert);

		List<List<BinaryTreeNode<UUID, Integer>>> paths = AllPathsToSum.find(bst, 75);

		assertThat(paths, hasSize(4));
		assertThat(paths, hasItem(hasNodes(30, 20, 25)));
		assertThat(paths, hasItem(hasNodes(30, 45)));
		assertThat(paths, hasItem(hasNodes(30, 20, 15, 10, -5, 5)));
	}

	@Test
	public void test_ZeroNumbers() {
		Stream.of(30, 20, 45, 15, 25, 10, 23, 28, 0)
				.map(value -> BinarySearchTreeNode.from(UUID.randomUUID(), value))
				.forEach(bst::insert);

		List<List<BinaryTreeNode<UUID, Integer>>> paths = AllPathsToSum.find(bst, 75);

		assertThat(paths, hasSize(4));
		assertThat(paths, hasItem(hasNodes(30, 20, 25)));
		assertThat(paths, hasItem(hasNodes(30, 45)));
		assertThat(paths, hasItem(hasNodes(30, 20, 15, 10, 0)));
	}

}