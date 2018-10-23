package vn.khanhpdt.playgrounds.datastructures.linkedlists;

import vn.khanhpdt.playgrounds.datastructures.nodes.DoublyLinked;

/**
 * @author khanhpdt
 */
public class DoubleEndedDoublyLinkedList<N extends DoublyLinked<N>> implements LinkedList<N> {

	private N head;

	private N tail;

	public void insertFirst(N node) {
		node.setNext(head);
		node.setPrevious(null);
		if (head != null) {
			head.setPrevious(node);
		}
		head = node;

		if (tail == null) {
			tail = node;
		}
	}

	public void insertLast(N node) {
		node.setPrevious(tail);
		node.setNext(null);
		if (tail != null) {
			tail.setNext(node);
		}
		tail = node;

		if (head == null) {
			head = node;
		}
	}

	@Override
	public N getHead() {
		return head;
	}

	@Override
	public void setHead(N head) {
		this.head = head;
	}

	public N getTail() {
		return tail;
	}

}
