package vn.khanhpdt.playgrounds.datastructures.linkedlists;

import org.junit.Test;
import vn.khanhpdt.playgrounds.datastructures.TestUtils;
import vn.khanhpdt.playgrounds.datastructures.nodes.SinglyLinkedNode;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.core.Is.is;

/**
 * @author khanhpdt
 */
public class DoubleEndedLinkedListTest {

    @Test
    public void testInsertFirstToEmptyList() {
        DoubleEndedLinkedList<SinglyLinkedNode<UUID, Integer>> linkedList = new DoubleEndedLinkedList<>();

        SinglyLinkedNode<UUID, Integer> newNode = TestUtils.randomSinglyNode();
        linkedList.insertFirst(newNode);

        assertThat("head points to the first item", linkedList.getHead(), is(newNode));
        assertThat("tail points to the first item", linkedList.getTail(), is(newNode));
    }

    @Test
    public void testInsertFirstToNonEmptyList() {
        DoubleEndedLinkedList<SinglyLinkedNode<UUID, Integer>> linkedList = new DoubleEndedLinkedList<>();

        SinglyLinkedNode<UUID, Integer> firstNode = TestUtils.randomSinglyNode();
        linkedList.insertFirst(firstNode);

        SinglyLinkedNode<UUID, Integer> secondNode = TestUtils.randomSinglyNode();
        linkedList.insertFirst(secondNode);

        assertThat(linkedList.getHead(), is(secondNode));
        assertThat(linkedList.getTail(), is(firstNode));
    }

    @Test
    public void testInsertLastToEmptyList() {
        DoubleEndedLinkedList<SinglyLinkedNode<UUID, Integer>> linkedList = new DoubleEndedLinkedList<>();

        SinglyLinkedNode<UUID, Integer> newNode = TestUtils.randomSinglyNode();
        linkedList.insertLast(newNode);

        assertThat("head points to the first item", linkedList.getHead(), is(newNode));
        assertThat("tail points to the first item", linkedList.getTail(), is(newNode));
    }

    @Test
    public void testInsertLastToNonEmptyList() {
        DoubleEndedLinkedList<SinglyLinkedNode<UUID, Integer>> linkedList = new DoubleEndedLinkedList<>();

        SinglyLinkedNode<UUID, Integer> firstNode = TestUtils.randomSinglyNode();
        linkedList.insertFirst(firstNode);

        SinglyLinkedNode<UUID, Integer> secondNode = TestUtils.randomSinglyNode();
        linkedList.insertLast(secondNode);

        assertThat(linkedList.getHead(), is(firstNode));
        assertThat(linkedList.getTail(), is(secondNode));
    }

    @Test
    public void testRemove() {
        List<SinglyLinkedNode<UUID, Integer>> nodes = TestUtils.randomSinglyNodes(10);

        UUID removeKey = UUID.randomUUID();
        nodes.add(0, SinglyLinkedNode.fromKey(removeKey));
        nodes.add(7, SinglyLinkedNode.fromKey(removeKey));
        nodes.add(SinglyLinkedNode.fromKey(removeKey));

        DoubleEndedLinkedList<SinglyLinkedNode<UUID, Integer>> linkedList = DoubleEndedLinkedList.from(nodes);

        linkedList.remove(SinglyLinkedNode.fromKey(removeKey));

        nodes.removeIf(n -> n.getKey().equals(removeKey));

        assertThat("duplicated key nodes are removed", LinkedLists.traverse(linkedList), contains(nodes.toArray()));
        assertThat("correct tail", linkedList.getTail(), is(nodes.get(nodes.size() - 1)));
    }

}
