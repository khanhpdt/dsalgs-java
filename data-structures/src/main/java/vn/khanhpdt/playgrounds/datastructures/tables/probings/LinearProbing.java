package vn.khanhpdt.playgrounds.datastructures.tables.probings;

class LinearProbing<K> implements ProbingMethod {

	private final int nSlots;

	private final int initialHash;

	private int sequenceNumber;

	LinearProbing(K key, int nSlots) {
		this.nSlots = nSlots;
		this.initialHash = Math.abs(key.hashCode() % this.nSlots);
		this.sequenceNumber = 0;
	}

	@Override
	public int probe() {
		// linear probing: the probing step is linear to the sequence number
		int result = (initialHash + sequenceNumber) % nSlots;
		sequenceNumber++;
		return result;
	}

}
