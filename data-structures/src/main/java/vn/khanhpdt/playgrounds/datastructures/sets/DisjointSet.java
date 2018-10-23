package vn.khanhpdt.playgrounds.datastructures.sets;

import vn.khanhpdt.playgrounds.datastructures.linkedlists.DoubleEndedLinkedList;
import vn.khanhpdt.playgrounds.datastructures.nodes.DisjointSetNode;

import java.util.UUID;

/**
 * @author khanhpdt
 */
public class DisjointSet<K, V> {

	private UUID key;

	private int size;

	private DoubleEndedLinkedList<DisjointSetNode<K, V>> doubleEndedLinkedList;

	public DisjointSet() {
		this.key = UUID.randomUUID();
		this.size = 0;
		this.doubleEndedLinkedList = new DoubleEndedLinkedList<>();
	}

	@Override
	public int hashCode() {
		int result = 17;
		int c = this.getKey().hashCode();
		return 31 + result * c;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof DisjointSet)) {
			return false;
		}

		DisjointSet otherSet = (DisjointSet) obj;
		return this.getKey().equals(otherSet.getKey());
	}

	public UUID getKey() {
		return key;
	}

	public DisjointSetNode<K, V> getHead() {
		if (doubleEndedLinkedList == null) {
			return null;
		}
		return doubleEndedLinkedList.getHead();
	}

	public DisjointSetNode<K, V> getTail() {
		return doubleEndedLinkedList.getTail();
	}

	public void insert(DisjointSetNode<K, V> node) {
		doubleEndedLinkedList.insertLast(node);
		node.setSet(this);
		size++;
	}

	public int size() {
		return size;
	}

	public void clear() {
		this.key = null;
		this.doubleEndedLinkedList = null;
		this.size = 0;
	}
}
