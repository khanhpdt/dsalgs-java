package vn.khanhpdt.playgrounds.ctci.treesandgraphs;

import org.junit.Test;
import vn.khanhpdt.playgrounds.ctci.treesandgraphs.MinimumHeightBST;
import vn.khanhpdt.playgrounds.datastructures.trees.BinarySearchTree;

import java.util.UUID;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author khanhpdt
 */
public class MinimumHeightBSTTest {

	@Test
	public void testCreate() {
		int[] sortedIntegers = IntStream.range(0, 7).toArray();

		BinarySearchTree<UUID, Integer> bst = MinimumHeightBST.create(sortedIntegers);

		assertThat(bst.getRoot().getValue(), is(3));
		assertThat(bst.getRoot().getLeft().getValue(), is(1));
		assertThat(bst.getRoot().getLeft().getLeft().getValue(), is(0));
		assertThat(bst.getRoot().getLeft().getRight().getValue(), is(2));
		assertThat(bst.getRoot().getRight().getValue(), is(5));
		assertThat(bst.getRoot().getRight().getLeft().getValue(), is(4));
		assertThat(bst.getRoot().getRight().getRight().getValue(), is(6));
	}

	@Test
	public void testCreate_expectedHeight_1() {
		int[] sortedIntegers = IntStream.range(0, 7).toArray();

		BinarySearchTree<UUID, Integer> bst = MinimumHeightBST.create(sortedIntegers);

		assertThat(bst.getHeight(), is(2));
	}

	@Test
	public void testCreate_expectedHeight_2() {
		int[] sortedIntegers = IntStream.range(0, 8).toArray();

		BinarySearchTree<UUID, Integer> bst = MinimumHeightBST.create(sortedIntegers);

		assertThat(bst.getHeight(), is(3));
	}

	@Test
	public void testCreate_expectedHeight_3() {
		int[] sortedIntegers = IntStream.range(0, 12).toArray();

		BinarySearchTree<UUID, Integer> bst = MinimumHeightBST.create(sortedIntegers);

		assertThat(bst.getHeight(), is(3));
	}

	@Test
	public void testCreate2() {
		int[] sortedIntegers = IntStream.range(0, 7).toArray();

		BinarySearchTree<UUID, Integer> bst = MinimumHeightBST.create2(sortedIntegers);

		assertThat(bst.getRoot().getValue(), is(3));
		assertThat(bst.getRoot().getLeft().getValue(), is(1));
		assertThat(bst.getRoot().getLeft().getLeft().getValue(), is(0));
		assertThat(bst.getRoot().getLeft().getRight().getValue(), is(2));
		assertThat(bst.getRoot().getRight().getValue(), is(5));
		assertThat(bst.getRoot().getRight().getLeft().getValue(), is(4));
		assertThat(bst.getRoot().getRight().getRight().getValue(), is(6));
	}

	@Test
	public void testCreate2_expectedHeight_1() {
		int[] sortedIntegers = IntStream.range(0, 7).toArray();

		BinarySearchTree<UUID, Integer> bst = MinimumHeightBST.create2(sortedIntegers);

		assertThat(bst.getHeight(), is(2));
	}

	@Test
	public void testCreate2_expectedHeight_2() {
		int[] sortedIntegers = IntStream.range(0, 8).toArray();

		BinarySearchTree<UUID, Integer> bst = MinimumHeightBST.create2(sortedIntegers);

		assertThat(bst.getHeight(), is(3));
	}

	@Test
	public void testCreate2_expectedHeight_3() {
		int[] sortedIntegers = IntStream.range(0, 12).toArray();

		BinarySearchTree<UUID, Integer> bst = MinimumHeightBST.create2(sortedIntegers);

		assertThat(bst.getHeight(), is(3));
	}

}