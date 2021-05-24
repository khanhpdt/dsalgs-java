package vn.khanhpdt.playgrounds.datastructures.nodes;

/**
 * @param <K>  type of node key
 * @param <V>  type of node value
 */
public class DoublyLinkedNode<K, V> implements DoublyLinked<DoublyLinkedNode<K, V>> {

	private Node<K, V> content;

	private DoublyLinkedNode<K, V> previous;

	private DoublyLinkedNode<K, V> next;

	public DoublyLinkedNode(Node<K, V> content) {
		this.content = content;
	}

	@Override
	public DoublyLinkedNode<K, V> getPrevious() {
		return previous;
	}

	@Override
	public void setPrevious(DoublyLinkedNode<K, V> previous) {
		this.previous = previous;
	}

	public Node<K, V> getContent() {
		return this.content;
	}

	public K getKey() {
		return getContent().getKey();
	}

	public V getValue() {
		return getContent().getValue();
	}

	@Override
	public DoublyLinkedNode<K, V> getNext() {
		return this.next;
	}

	@Override
	public void setNext(DoublyLinkedNode<K, V> next) {
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
		if (!(obj instanceof DoublyLinkedNode)) {
			return false;
		}

		@SuppressWarnings("unchecked")
		DoublyLinkedNode<K, V> otherNode = (DoublyLinkedNode<K, V>) obj;
		return this.getKey().equals(otherNode.getKey());
	}

	@Override
	public String toString() {
		return "DoublyLinkedNode: {" + getKey().toString() + ", " + getContent().getValue().toString() + "}";
	}

	public static <K, V> DoublyLinkedNode<K, V> from(K key, V value) {
		return new DoublyLinkedNode<>(new Node<>(key, value));
	}
}
