package vn.khanhpdt.playgrounds.ctci.treesandgraphs;

import org.junit.Before;
import org.junit.Test;
import vn.khanhpdt.playgrounds.datastructures.nodes.BinarySearchTreeNode;
import vn.khanhpdt.playgrounds.datastructures.trees.BinarySearchTree;

import java.util.UUID;
import java.util.stream.Stream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FirstCommonAncestorFinderTest {

	private BinarySearchTree<UUID, Integer> bst;

	@Before
	public void init() {
		initBST();
	}

	private void initBST() {
		bst = new BinarySearchTree<>();
		Stream.of(30, 20, 25, 35, 15, 32, 28, 34, 10, 23)
				.map(value -> BinarySearchTreeNode.from(UUID.randomUUID(), value))
				.forEach(bst::insert);
	}

	@Test
	public void test_ancestorFound() {
		assertThat(FirstCommonAncestorFinder.find(bst.findNodeByValue(15), bst.findNodeByValue(28)).getValue(), is(20));
		assertThat(FirstCommonAncestorFinder.find(bst.findNodeByValue(25), bst.findNodeByValue(34)).getValue(), is(30));
		assertThat(FirstCommonAncestorFinder.find(bst.findNodeByValue(25), bst.findNodeByValue(28)).getValue(), is(20));
		assertThat(FirstCommonAncestorFinder.find(bst.findNodeByValue(20), bst.findNodeByValue(28)).getValue(), is(30));
		assertThat(FirstCommonAncestorFinder.find(bst.findNodeByValue(10), bst.findNodeByValue(23)).getValue(), is(20));
		assertThat(FirstCommonAncestorFinder.find(bst.findNodeByValue(32), bst.findNodeByValue(34)).getValue(), is(35));
	}

	@Test
	public void test_oneNodeIsRoot_nullAncestor() {
		BinarySearchTreeNode<UUID, Integer> root = bst.findNodeByValue(30);
		assertThat(FirstCommonAncestorFinder.find(root, bst.findNodeByValue(28)).isNull(), is(true));
		assertThat(FirstCommonAncestorFinder.find(bst.findNodeByValue(32), root).isNull(), is(true));
	}

}