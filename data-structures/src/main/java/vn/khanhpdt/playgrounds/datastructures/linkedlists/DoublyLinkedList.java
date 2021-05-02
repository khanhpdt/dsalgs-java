package vn.khanhpdt.playgrounds.datastructures.linkedlists;

import vn.khanhpdt.playgrounds.datastructures.nodes.DoublyLinkedNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DoublyLinkedList<K, V> implements LinkedList<DoublyLinkedNode<K, V>> {

	private DoublyLinkedNode<K, V> head;

	public void insert(DoublyLinkedNode<K, V> node) {
		// link nodes together
		if (head != null) {
			head.setPrevious(node);
		}
		node.setNext(head);

		// set new head
		head = node;
		head.setPrevious(null);
	}

	@Override
	public DoublyLinkedNode<K, V> getHead() {
		return head;
	}

	@Override
	public void setHead(DoublyLinkedNode<K, V> head) {
		this.head = head;
	}

	public static <K, V> DoublyLinkedList<K, V> from(List<DoublyLinkedNode<K, V>> nodes) {
		DoublyLinkedList<K, V> linkedList = new DoublyLinkedList<>();
		for (int i = nodes.size() - 1; i >= 0; i--) {
			linkedList.insert(nodes.get(i));
		}
		return linkedList;
	}

	public void remove(K removeKey) {
		while (head != null && head.getKey().equals(removeKey)) {
			head = head.getNext();
			if (head != null) {
				head.setPrevious(null);
			}
		}

		DoublyLinkedNode<K, V> currentNode = head;
		while (currentNode != null) {
			if (currentNode.getKey().equals(removeKey)) {
				currentNode.getPrevious().setNext(currentNode.getNext());
				if (currentNode.getNext() != null) {
					currentNode.getNext().setPrevious(currentNode.getPrevious());
				}
			}
			currentNode = currentNode.getNext();
		}
	}

	public List<DoublyLinkedNode<K, V>> traverse() {
		return LinkedLists.traverse(this);
	}

	public List<DoublyLinkedNode<K, V>> traverseBackward() {
		List<DoublyLinkedNode<K, V>> result = new ArrayList<>();

		// let the current node the last node and we traverse from there using the backward link
		List<DoublyLinkedNode<K, V>> linkedNodes = traverse();
		DoublyLinkedNode<K, V> currentNode = linkedNodes.get(linkedNodes.size() - 1);
		while (currentNode != null) {
			result.add(currentNode);
			currentNode = currentNode.getPrevious();
		}

		return result;
	}

	public DoublyLinkedList reverse() {
		DoublyLinkedNode<K, V> current = this.head;

		// reset this list
		this.head = null;

		// reverse in-place
		while (current != null) {
			DoublyLinkedNode<K, V> next = current.getNext();
			insert(current);

			current = next;
		}

		return this;
	}

	@Override
	public String toString() {
		return Arrays.toString(traverse().toArray());
	}

	public void insertNextTo(K key, V value, DoublyLinkedNode<K, V> node) {
		DoublyLinkedNode<K, V> newNode = DoublyLinkedNode.from(key, value);

		newNode.setPrevious(node);
		newNode.setNext(node.getNext());

		node.setNext(newNode);
	}
}
