package vn.khanhpdt.playgrounds.datastructures.stacks;

import vn.khanhpdt.playgrounds.datastructures.nodes.ForwardLinked;

/**
 * @param <N> type of node
 */
public class Stack<N extends ForwardLinked<N>> {

	private N head;

	private int size;

	public Stack() {
		this.head = null;
		this.size = 0;
	}

	public void push(N node) {
		node.setNext(head);
		head = node;

		size++;
	}

	public N pop() {
		if (head == null) {
			return null;
		}

		N currentHead = head;
		head = head.getNext();

		size--;

		return currentHead;
	}

	public N peek() {
		return head;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public int size() {
		return size;
	}

	public N removeBottom() {
		// find stack bottom and its previous
		// it is more time-efficient if the stack is a double-ended stack
		N previous = null;
		N current = head;
		while (current != null && current.getNext() != null) {
			previous = current;
			current = current.getNext();
		}

		N result = null;
		// current is the bottom
		if (current != null) {
			// current is also the top
			if (previous == null) {
				result = pop();
			}
			else {
				// remove the bottom from the stack
				previous.setNext(null);
				size--;
				result = current;
			}
		}

		return result;
	}
}
