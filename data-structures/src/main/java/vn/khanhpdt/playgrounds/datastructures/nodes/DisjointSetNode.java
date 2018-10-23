package vn.khanhpdt.playgrounds.datastructures.nodes;

import vn.khanhpdt.playgrounds.datastructures.sets.DisjointSet;

/**
 * @author khanhpdt
 */
public class DisjointSetNode<K, V> implements ForwardLinked<DisjointSetNode<K, V>> {
	
	private Node<K, V> content;

	private DisjointSetNode<K, V> next;

	private DisjointSet<K, V> set;

	public DisjointSetNode(K key) {
		this.content = new Node<>(key);
	}

	public K getKey() {
		return content.getKey();
	}

	@Override
	public DisjointSetNode<K, V> getNext() {
		return next;
	}

	@Override
	public void setNext(DisjointSetNode<K, V> next) {
		this.next = next;
	}

	@Override
	public int hashCode() {
		int result = 17;

		int c = this.getKey().hashCode();

		return 31 + result * c;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof DisjointSetNode)) {
			return false;
		}

		@SuppressWarnings("unchecked")
		DisjointSetNode<K, V> otherNode = (DisjointSetNode<K, V>) obj;
		return this.getKey().equals(otherNode.getKey());
	}

	public DisjointSet<K, V> getSet() {
		return set;
	}

	public void setSet(DisjointSet<K, V> set) {
		this.set = set;
	}
}
