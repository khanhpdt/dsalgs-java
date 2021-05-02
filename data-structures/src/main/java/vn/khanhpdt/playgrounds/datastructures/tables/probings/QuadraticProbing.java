package vn.khanhpdt.playgrounds.datastructures.tables.probings;

class QuadraticProbing<K> implements ProbingMethod {

	private final int nSlots;

	private final int initialSlotIndex;

	private int sequenceNumber;

	QuadraticProbing(K key, int nSlots) {
		this.nSlots = nSlots;
		this.initialSlotIndex = Math.abs(key.hashCode() % this.nSlots);
		this.sequenceNumber = 0;
	}

	@Override
	public int probe() {
		// quadratic probing: the probing step is quadratic to the sequence number.
		// However, this implementation works best when the number of slots is a prime number or a power of two.
		// see Problem 11-3 in [1]
		int result = (initialSlotIndex + (sequenceNumber * sequenceNumber + sequenceNumber) / 2) % nSlots;
		sequenceNumber++;
		return result;
	}

}
