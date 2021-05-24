package vn.khanhpdt.playgrounds.ctci.treesandgraphs;

import org.junit.Before;
import org.junit.Test;
import vn.khanhpdt.playgrounds.ctci.treesandgraphs.LinkedListEachDepth;
import vn.khanhpdt.playgrounds.datastructures.linkedlists.SinglyLinkedList;
import vn.khanhpdt.playgrounds.datastructures.nodes.BinarySearchTreeNode;
import vn.khanhpdt.playgrounds.datastructures.nodes.BinaryTreeNode;
import vn.khanhpdt.playgrounds.datastructures.trees.BinarySearchTree;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class LinkedListEachDepthTest {

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
	public void test() {
		List<SinglyLinkedList<BinaryTreeNode<UUID, Integer>>> linkedLists = LinkedListEachDepth.build(bst);

		assertLinkedList(linkedLists.get(0), 30);
		assertLinkedList(linkedLists.get(1), 20, 35);
		assertLinkedList(linkedLists.get(2), 15, 25, 32);
		assertLinkedList(linkedLists.get(3), 28, 34);
	}

	private void assertLinkedList(SinglyLinkedList<BinaryTreeNode<UUID, Integer>> linkedList, int... nodeValues) {
		BinaryTreeNode<UUID, Integer> current = linkedList.getHead();
		int i = 0;
		while (current != null) {
			assertThat(current.getValue(), is(nodeValues[i]));

			current = current.getNext();
			i++;
		}
	}

}