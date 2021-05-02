package vn.khanhpdt.playgrounds.datastructures.queues;

import org.junit.Test;
import vn.khanhpdt.playgrounds.datastructures.TestUtils;
import vn.khanhpdt.playgrounds.datastructures.nodes.DoublyLinkedNode;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DequeTest {

	@Test
	public void testEnqueueFrontToEmptyQueue() throws Exception {
		Deque<DoublyLinkedNode<UUID, Integer>> queue = new Deque<>();

		DoublyLinkedNode<UUID, Integer> newNode = TestUtils.randomDoublyLinkedNode();
		queue.enqueueFront(newNode);

		assertThat(queue.getRear(), is(newNode));
		assertThat(queue.getFront(), is(newNode));
	}

	@Test
	public void testEnqueueFrontToNonEmptyQueue() throws Exception {
		Deque<DoublyLinkedNode<UUID, Integer>> queue = new Deque<>();

		DoublyLinkedNode<UUID, Integer> firstInsertedNode = TestUtils.randomDoublyLinkedNode();
		queue.enqueueFront(firstInsertedNode);

		DoublyLinkedNode<UUID, Integer> secondInsertedNode = TestUtils.randomDoublyLinkedNode();
		queue.enqueueFront(secondInsertedNode);

		assertThat(queue.getFront(), is(secondInsertedNode));
	}

	@Test
	public void testDequeueRear() throws Exception {
		List<DoublyLinkedNode<UUID, Integer>> nodes = TestUtils.randomDoublyNodes(10);
		Deque<DoublyLinkedNode<UUID, Integer>> queue = Deque.from(nodes);
		assertThat(queue.dequeueRear(), is(nodes.get(nodes.size() - 1)));
	}

	@Test
	public void testEnqueueFrontAndDequeueRear() throws Exception {
		List<DoublyLinkedNode<UUID, Integer>> nodes = TestUtils.randomDoublyNodes(10);

		Deque<DoublyLinkedNode<UUID, Integer>> queue = new Deque<>();
		nodes.forEach(queue::enqueueFront);

		nodes.forEach(n -> assertThat(queue.dequeueRear(), is(n)));
	}
}
