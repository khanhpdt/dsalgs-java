package vn.khanhpdt.playgrounds.ctci.stacksandqueues;

import vn.khanhpdt.playgrounds.datastructures.nodes.SinglyLinkedNode;
import vn.khanhpdt.playgrounds.datastructures.stacks.Stack;

import java.util.UUID;

/**
 * Problem 3.5: Implement a queue using two stacks.
 *
 */
public class QueueByTwoStacks {

	private Stack<SinglyLinkedNode<UUID, Integer>> enqueuingStack;

	private Stack<SinglyLinkedNode<UUID, Integer>> dequeuingStack;

	public QueueByTwoStacks() {
		enqueuingStack = new Stack<>();
		dequeuingStack = new Stack<>();
	}

	public void enqueue(SinglyLinkedNode<UUID, Integer> node) {
		enqueuingStack.push(node);
	}

	public SinglyLinkedNode<UUID, Integer> dequeue() {
		// to avoid unnecessary shifts when we can still find the first-come node in the dequeuing stack
		if (dequeuingStack.isEmpty()) {
			transferStack(enqueuingStack, dequeuingStack);
		}
		return dequeuingStack.pop();
	}

	private void transferStack(Stack<SinglyLinkedNode<UUID, Integer>> stackFrom, Stack<SinglyLinkedNode<UUID, Integer>> stackTo) {
		SinglyLinkedNode<UUID, Integer> current = stackFrom.pop();
		while (current != null) {
			stackTo.push(current);
			current = stackFrom.pop();
		}
	}
}
