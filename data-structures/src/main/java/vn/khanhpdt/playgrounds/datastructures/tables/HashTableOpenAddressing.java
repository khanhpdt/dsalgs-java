package vn.khanhpdt.playgrounds.datastructures.tables;

import vn.khanhpdt.playgrounds.datastructures.nodes.Node;
import vn.khanhpdt.playgrounds.datastructures.tables.probings.ProbingMethod;
import vn.khanhpdt.playgrounds.datastructures.tables.probings.ProbingMethodName;
import vn.khanhpdt.playgrounds.datastructures.tables.probings.ProbingMethods;

import java.util.Arrays;

/**
 * @author khanhpdt
 */
class HashTableOpenAddressing<K, V> extends HashTable<K, V> {

	private Node<K, V>[] slots;

	private ProbingMethodName probingMethodName;

	private SlotStatus[] slotStatuses;

	HashTableOpenAddressing(ProbingMethodName probingMethodName) {
		super();
		init(probingMethodName);
	}

	@SuppressWarnings("unchecked")
	private void init(ProbingMethodName probingMethodName) {
		this.probingMethodName = probingMethodName;
		this.slots = (Node<K, V>[]) new Node[nSlots];
		this.slotStatuses = new SlotStatus[nSlots];
		Arrays.fill(slotStatuses, SlotStatus.AVAILABLE);
	}

	HashTableOpenAddressing(int nSlots, ProbingMethodName probingMethodName) {
		super();
		this.nSlots = nSlots;
		init(probingMethodName);
	}

	@Override
	public void insert(Node<K, V> item) {
		if (nItems == nSlots) {
			throw new IllegalStateException("The hash table is already full.");
		}

		final ProbingMethod probingMethod = ProbingMethods.create(probingMethodName, item.getKey(), nSlots);

		// keep searching until either finding an available slot or all the slots are probed
		int slotIndex = probingMethod.probe();
		int nProbes = 1;
		while (slotStatuses[slotIndex] == SlotStatus.ALLOCATED && nProbes <= nSlots) {
			slotIndex = probingMethod.probe();
			nProbes++;
		}

		if (slotStatuses[slotIndex] == SlotStatus.AVAILABLE) {
			insertItem(item, slotIndex);
		}
		else {
			throw new IllegalStateException("Could not find any available slot.");
		}
	}

	private void insertItem(Node<K, V> item, int slotIndex) {
		slots[slotIndex] = item;
		slotStatuses[slotIndex] = SlotStatus.ALLOCATED;
		nItems++;
	}

	@Override
	public Node<K, V> search(K itemKey) {
		int slotIndex = searchSlot(itemKey);

		if (slotIndex == -1) {
			return null;
		}
		return slots[slotIndex];
	}

	private int searchSlot(K itemKey) {
		final ProbingMethod probingMethod = ProbingMethods.create(probingMethodName, itemKey, nSlots);

		int slotIndex = probingMethod.probe();
		int nProbes = 1;

		// if the slot at slotIndex is still available, the item is not in the hash table, because otherwise it would have
		// been inserted into the slot
		while (slotStatuses[slotIndex] == SlotStatus.ALLOCATED && !isSlotWithKey(slotIndex, itemKey) && nProbes <= nSlots) {
			slotIndex = probingMethod.probe();
			nProbes++;
		}

		// found an existing slot with the given key
		if (slotStatuses[slotIndex] == SlotStatus.ALLOCATED && isSlotWithKey(slotIndex, itemKey)) {
			return slotIndex;
		}
		// not found
		return -1;
	}

	private boolean isSlotWithKey(int slotIndex, K key) {
		return slots[slotIndex] != null && slots[slotIndex].getKey().equals(key);
	}

	@Override
	public void remove(K itemKey) {
		int slotIndex = searchSlot(itemKey);
		// item found
		if (slotIndex >= 0) {
			removeItem(slotIndex);
		}
	}

	private void removeItem(int slotIndex) {
		slots[slotIndex] = null;
		slotStatuses[slotIndex] = SlotStatus.AVAILABLE;
		nItems--;
	}

}
