package vn.khanhpdt.playgrounds.datastructures.tables;

import vn.khanhpdt.playgrounds.datastructures.nodes.Node;

/**
 * @param <K>  type of item key
 * @param <V>  type of item value
 */
public abstract class HashTable<K, V> {

	private static final int DEFAULT_NUMBER_OF_SLOTS = 11;

	/**
	 * Number of available slots in the hash table.
	 */
	protected int nSlots;

	/**
	 * Number of items currently stored in the hash table.
	 */
	protected int nItems;

	protected HashTable() {
		this.nSlots = DEFAULT_NUMBER_OF_SLOTS;
		this.nItems = 0;
	}

	public int size() {
		return nItems;
	}

	public abstract void insert(Node<K, V> item);

	public abstract Node<K, V> search(K itemKey);

	public abstract void remove(K itemKey);

}
