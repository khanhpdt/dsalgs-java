package vn.khanhpdt.playgrounds.ctci.treesandgraphs;

import org.junit.Before;
import org.junit.Test;
import vn.khanhpdt.playgrounds.ctci.treesandgraphs.NextNodeFinder;
import vn.khanhpdt.playgrounds.datastructures.nodes.BinarySearchTreeNode;
import vn.khanhpdt.playgrounds.datastructures.trees.BinarySearchTree;

import java.util.UUID;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author khanhpdt
 */
public class NextNodeFinderTest {

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
	public void testNext() {
		assertThat(NextNodeFinder.find(bst, bst.findNodeByValue(34)).getValue(), is(35));
		assertThat(NextNodeFinder.find(bst, bst.findNodeByValue(28)).getValue(), is(30));
		assertThat(NextNodeFinder.find(bst, bst.findNodeByValue(30)).getValue(), is(32));
	}

	@Test
	public void testNext_notExist() {
		assertThat(NextNodeFinder.find(bst, bst.findNodeByValue(35)).isNull(), is(true));
	}

}