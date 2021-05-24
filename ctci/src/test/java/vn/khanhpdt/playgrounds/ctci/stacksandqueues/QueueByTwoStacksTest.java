package vn.khanhpdt.playgrounds.ctci.stacksandqueues;

import org.junit.Test;
import vn.khanhpdt.playgrounds.ctci.stacksandqueues.QueueByTwoStacks;
import vn.khanhpdt.playgrounds.datastructures.nodes.SinglyLinkedNode;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class QueueByTwoStacksTest {

	@Test
	public void test() {
		int nNodes = 17;
		List<SinglyLinkedNode<UUID, Integer>> nodes = new ArrayList<>(nNodes);
		for (int i = 0; i < nNodes; i++) {
			nodes.add(SinglyLinkedNode.fromKey(UUID.randomUUID()));
		}

		QueueByTwoStacks queueByTwoStacks = new QueueByTwoStacks();

		// enqueue 5 nodes
		for (int i = 0; i < 5; i++) {
			queueByTwoStacks.enqueue(nodes.get(i));
		}

		// dequeue 3 nodes
		for (int i = 0; i < 3; i++) {
			assertThat(queueByTwoStacks.dequeue(), is(nodes.get(i)));
		}

		// enqueue the remaining 15 nodes
		for (int i = 5; i < nNodes; i++) {
			queueByTwoStacks.enqueue(nodes.get(i));
		}

		// dequeue all
		for (int i = 3; i < nNodes; i++) {
			assertThat(queueByTwoStacks.dequeue(), is(nodes.get(i)));
		}
	}

}