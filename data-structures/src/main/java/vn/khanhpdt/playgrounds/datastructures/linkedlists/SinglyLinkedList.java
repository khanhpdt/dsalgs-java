package vn.khanhpdt.playgrounds.datastructures.linkedlists;

import vn.khanhpdt.playgrounds.datastructures.nodes.ForwardLinked;

import java.util.Arrays;
import java.util.List;

public class SinglyLinkedList<N extends ForwardLinked<N>> implements LinkedList<N> {

	private N head;

	public void insertFirst(N node) {
		// new node is inserted at the beginning of the list
		node.setNext(head);
		// new node becomes the new head
		head = node;
	}

	public void insertLast(N node) {
		N current = head;

		// list is currently empty
		if (current == null) {
			head = node;
			head.setNext(null);
			return;
		}

		// go to the last node
		while (current.getNext() != null) {
			current = current.getNext();
		}
		// current is the current last node. now make the new node the last one.
		current.setNext(node);
		node.setNext(null);
	}

	public N search(N node) {
		N currentNode = this.head;
		while (currentNode != null) {
			// found
			if (currentNode.equals(node)) {
				break;
			}
			currentNode = currentNode.getNext();
		}
		return currentNode;
	}

	public void removeAll(N removeNode) {
		LinkedLists.removeAll(this, removeNode);
	}

	public void remove(N removeNode) {
		LinkedLists.remove(this, removeNode);
	}

	@Override
	public N getHead() {
		return head;
	}

	@Override
	public void setHead(N head) {
		this.head = head;
	}

	public static <N extends ForwardLinked<N>> SinglyLinkedList<N> from(List<N> nodes) {
		SinglyLinkedList<N> linkedList = new SinglyLinkedList<>();
		for (int i = nodes.size() - 1; i >= 0; i--) {
			linkedList.insertFirst(nodes.get(i));
		}
		return linkedList;
	}

	public N getKthToLast(int k) {
		int size = size();
		return getKthToLast(k, size);
	}

	private N getKthToLast(int k, int size) {
		if (k < 0 || k > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		return get(size - 1 - k, size);
	}

	public N get(int k) {
		return get(k, size());
	}

	private N get(int k, int size) {
		if (k < 0 || k > size - 1) {
			throw new IndexOutOfBoundsException();
		}

		N current = head;
		for (int i = 1; i <= k; i++) {
			current = current.getNext();
		}
		return current;
	}

	public int size() {
		int result = 0;
		N current = head;
		while (current != null) {
			result++;
			current = current.getNext();
		}
		return result;
	}

	/**
	 * Note: this method changes the structure of the list.
	 */
	public SinglyLinkedList<N> reverse() {
		// save the head before resetting the list
		N current = head;

		// reset this list
		head = null;

		// reverse in-place
		while (current != null) {
			N next = current.getNext();
			insertFirst(current);
			current = next;
		}
		return this;
	}

	public N removeFirst() {
		// empty empty
		if (head == null) {
			return null;
		}
		N removed = head;
		head = head.getNext();
		return removed;
	}

	@Override
	public String toString() {
		List<N> nodes = LinkedLists.traverse(this);
		return Arrays.toString(nodes.toArray());
	}

}
