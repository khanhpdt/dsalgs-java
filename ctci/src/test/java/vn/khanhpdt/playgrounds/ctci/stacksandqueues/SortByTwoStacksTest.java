package vn.khanhpdt.playgrounds.ctci.stacksandqueues;

import org.hamcrest.Matchers;
import org.junit.Test;
import vn.khanhpdt.playgrounds.ctci.stacksandqueues.SortByTwoStacks;
import vn.khanhpdt.playgrounds.datastructures.nodes.SinglyLinkedNode;
import vn.khanhpdt.playgrounds.datastructures.stacks.Stack;

import java.util.Random;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SortByTwoStacksTest {

	@Test
	public void test() {
		int nNodes = 20;
		Stack<SinglyLinkedNode<UUID, Integer>> stack = new Stack<>();
		Random random = new Random();
		for (int i = 0; i < nNodes; i++) {
			stack.push(SinglyLinkedNode.from(UUID.randomUUID(), random.nextInt()));
		}

		Stack<SinglyLinkedNode<UUID, Integer>> sortedStack = new SortByTwoStacks(stack).get();
		assertThat("sorted stack must have items", sortedStack.size(), is(nNodes));

		SinglyLinkedNode<UUID, Integer> current = sortedStack.pop();
		while (current != null) {
			SinglyLinkedNode<UUID, Integer> next = sortedStack.pop();
			if (next != null) {
				assertThat("top nodes are biggest nodes", current.getValue(), Matchers.greaterThanOrEqualTo(next.getValue()));
			}
			current = next;
		}

	}

}