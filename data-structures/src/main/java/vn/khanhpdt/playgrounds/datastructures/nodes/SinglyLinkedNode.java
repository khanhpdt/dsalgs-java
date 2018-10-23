package vn.khanhpdt.playgrounds.datastructures.nodes;

/**
 * @author khanhpdt
 */
public class SinglyLinkedNode<K, V> implements ForwardLinked<SinglyLinkedNode<K, V>> {

	private Node<K, V> content;

	private SinglyLinkedNode<K, V> next;

	public SinglyLinkedNode(Node<K, V> content) {
		this.content = content;
	}

	public static <K, V> SinglyLinkedNode<K, V> fromKey(K key) {
		return new SinglyLinkedNode<>(new Node<>(key, null));
	}

	public static <K, V> SinglyLinkedNode<K, V> from(K key, V value) {
		return new SinglyLinkedNode<>(new Node<>(key, value));
	}

	public Node<K, V> getContent() {
		return content;
	}

	public K getKey() {
		return content.getKey();
	}

	public V getValue() {
		return getContent().getValue();
	}

	public void cloneContent(SinglyLinkedNode<K, V> other) {
		this.content = other.getContent();
	}

	public void removeContent() {
		this.content = null;
	}

	@Override
	public SinglyLinkedNode<K, V> getNext() {
		return next;
	}

	@Override
	public void setNext(SinglyLinkedNode<K, V> next) {
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
		if (!(obj instanceof SinglyLinkedNode)) {
			return false;
		}

		SinglyLinkedNode otherNode = (SinglyLinkedNode) obj;
		return this.getKey().equals(otherNode.getKey());
	}

	@Override
	public String toString() {
		return "SinglyLinkedNode: {" + getKey().toString() + ", " + getValue().toString() + "}";
	}
}
