package vn.khanhpdt.playgrounds.datastructures.tables;

import vn.khanhpdt.playgrounds.datastructures.linkedlists.SinglyLinkedList;
import vn.khanhpdt.playgrounds.datastructures.nodes.Node;
import vn.khanhpdt.playgrounds.datastructures.nodes.SinglyLinkedNode;

import java.util.function.Function;

public class HashTableChaining<K, V> extends HashTable<K, V> {

	private final Function<K, Integer> hashFunction;

	/**
	 * The chaining to handle collision.
	 */
	private final SinglyLinkedList<SinglyLinkedNode<K, V>>[] slots;

	@SuppressWarnings("unchecked")
	public HashTableChaining() {
		super();
		this.slots = (SinglyLinkedList<SinglyLinkedNode<K, V>>[]) new SinglyLinkedList[this.nSlots];
		this.hashFunction = this::defaultHash;
	}

	private Integer defaultHash(K itemKey) {
		// make sure the index is in range
		return Math.abs(itemKey.hashCode() % nSlots);
	}

	@Override
	public void insert(Node<K, V> item) {
		int slotIndex = hashFunction.apply(item.getKey());
		SinglyLinkedList<SinglyLinkedNode<K, V>> slot = slots[slotIndex];

		// first item in the chaining
		if (slot == null) {
			slot = new SinglyLinkedList<>();
			slots[slotIndex] = slot;
		}

		// the new item is always inserted into the head of the chaining
		slot.insertFirst(new SinglyLinkedNode<>(item));

		nItems++;
	}

	private SinglyLinkedList<SinglyLinkedNode<K, V>> getSlotFor(K itemKey) {
		int slotIndex = hashFunction.apply(itemKey);
		return slots[slotIndex];
	}

	@Override
	public Node<K, V> search(K itemKey) {
		SinglyLinkedList<SinglyLinkedNode<K, V>> slot = getSlotFor(itemKey);

		// no slot found, which means item is never in the hash table
		if (slot == null) {
			return null;
		}

		SinglyLinkedNode<K, V> slotItem = slot.search(SinglyLinkedNode.fromKey(itemKey));
		if (slotItem == null) {
			return null;
		}
		return slotItem.getContent();
	}

	@Override
	public void remove(K itemKey) {
		SinglyLinkedList<SinglyLinkedNode<K, V>> slot = getSlotFor(itemKey);

		if (slot == null) {
			return;
		}

		slot.removeAll(new SinglyLinkedNode<>(new Node<>(itemKey, null)));

		nItems--;
	}
}
