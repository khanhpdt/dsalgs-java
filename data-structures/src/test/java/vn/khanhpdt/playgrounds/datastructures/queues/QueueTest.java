package vn.khanhpdt.playgrounds.datastructures.queues;

import org.junit.Test;
import vn.khanhpdt.playgrounds.datastructures.TestUtils;
import vn.khanhpdt.playgrounds.datastructures.nodes.DoublyLinkedNode;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class QueueTest {

	@Test
	public void testEnqueueRearToEmptyQueue() throws Exception {
		Queue<DoublyLinkedNode<UUID, Integer>> queue = new Queue();

		DoublyLinkedNode<UUID, Integer> newNode = TestUtils.randomDoublyLinkedNode();
		queue.enqueueRear(newNode);

		assertThat(queue.getRear(), is(newNode));
		assertThat(queue.getFront(), is(newNode));
	}

	@Test
	public void testEnqueueRearToNonEmptyQueue() throws Exception {
		List<DoublyLinkedNode<UUID, Integer>> nodes = TestUtils.randomDoublyNodes(10);
		Queue<DoublyLinkedNode<UUID, Integer>> queue = Queue.from(nodes);

		DoublyLinkedNode<UUID, Integer> newNode = TestUtils.randomDoublyLinkedNode();
		queue.enqueueRear(newNode);

		assertThat(queue.getRear(), is(newNode));
	}

	@Test
	public void testDequeueFront() throws Exception {
		List<DoublyLinkedNode<UUID, Integer>> nodes = TestUtils.randomDoublyNodes(10);
		Queue<DoublyLinkedNode<UUID, Integer>> queue = Queue.from(nodes);
		assertThat(queue.dequeueFront(), is(nodes.get(0)));
	}

	@Test
	public void testEnqueueRearAndDequeueFront() throws Exception {
		List<DoublyLinkedNode<UUID, Integer>> nodes = TestUtils.randomDoublyNodes(10);

		Queue<DoublyLinkedNode<UUID, Integer>> queue = new Queue<>();
		nodes.forEach(queue::enqueueRear);

		nodes.forEach(n -> assertThat(queue.dequeueFront(), is(n)));
	}

}
