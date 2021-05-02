package vn.khanhpdt.playgrounds.datastructures.linkedlists;

import vn.khanhpdt.playgrounds.datastructures.nodes.ForwardLinked;
import vn.khanhpdt.playgrounds.datastructures.nodes.SinglyLinkedNode;

import java.util.List;

public class DoubleEndedLinkedList<N extends ForwardLinked<N>> implements LinkedList<N> {

	private N head;

	private N tail;

	public void insertFirst(N node) {
		node.setNext(head);
		head = node;

		if (tail == null) {
			tail = node;
		}
	}

	public void insertLast(N node) {
		if (tail != null) {
			tail.setNext(node);
		}
		tail = node;

		if (head == null) {
			head = node;
		}
	}

	public void remove(N removeNode) {
		LinkedLists.removeAll(this, removeNode);
		tail = getLastNode();
	}

	private N getLastNode() {
		if (head == null) {
			return null;
		}

		N currentNode = head;
		// only last node has null "next" reference
		while (currentNode.getNext() != null) {
			currentNode = currentNode.getNext();
		}
		return currentNode;
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

	public static <K, V> DoubleEndedLinkedList<SinglyLinkedNode<K, V>> from(List<SinglyLinkedNode<K, V>> nodes) {
		DoubleEndedLinkedList<SinglyLinkedNode<K, V>> linkedList = new DoubleEndedLinkedList<>();
		for (int i = nodes.size() - 1; i >= 0; i--) {
			linkedList.insertFirst(nodes.get(i));
		}
		return linkedList;
	}
}
