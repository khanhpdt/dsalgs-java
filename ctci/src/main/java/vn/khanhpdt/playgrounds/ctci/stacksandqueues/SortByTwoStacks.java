package vn.khanhpdt.playgrounds.ctci.stacksandqueues;

import vn.khanhpdt.playgrounds.datastructures.nodes.SinglyLinkedNode;
import vn.khanhpdt.playgrounds.datastructures.stacks.Stack;

import java.util.UUID;

/**
 * Problem 3.6: Write a program to sort a stack such that the biggest items are on the top. You can use
 * an additional temporary stack, but you may not copy the elements into any other data structure
 * (such as an array). The stack supports the following operations: push, pop, peek, and is Empty.
 *
 */
public class SortByTwoStacks {

	private Stack<SinglyLinkedNode<UUID, Integer>> sortedStack;

	private Stack<SinglyLinkedNode<UUID, Integer>> stack;

	public SortByTwoStacks(Stack<SinglyLinkedNode<UUID, Integer>> stack) {
		this.stack = stack;
		this.sortedStack = new Stack<>();

		sort();
	}

	private void sort() {
		while (!stack.isEmpty()) {
			SinglyLinkedNode<UUID, Integer> nodeToSort = stack.pop();

			// find the right place to insert the node to the sorted stack. the idea is similar to insertion sort.
			while (true) {
				SinglyLinkedNode<UUID, Integer> sortedCurrent = sortedStack.peek();
				// sorted stack is empty
				if (sortedCurrent == null) {
					sortedStack.push(nodeToSort);
					break;
				}
				// biggest items on top
				else if (nodeToSort.getValue().compareTo(sortedCurrent.getValue()) >= 0) {
					sortedStack.push(nodeToSort);
					break;
				}
				else {
					// checking the next node in the sorted stack.
					// however, we need to save the current node to find the right position for it later.
					// note that we are allowed to use only two stacks to do the sort.
					stack.push(sortedStack.pop());
				}
			}
		}
	}

	public Stack<SinglyLinkedNode<UUID, Integer>> get() {
		return sortedStack;
	}
}
