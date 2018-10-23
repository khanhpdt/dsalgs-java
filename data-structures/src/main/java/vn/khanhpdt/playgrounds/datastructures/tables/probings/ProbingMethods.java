package vn.khanhpdt.playgrounds.datastructures.tables.probings;

/**
 * @author khanhpdt
 */
public class ProbingMethods {

	public static <K> ProbingMethod create(ProbingMethodName probingMethodName, K key, int nSlots) {
		switch (probingMethodName) {
			case LINEAR_PROBING:
				return new LinearProbing<>(key, nSlots);
			case QUADRATIC_PROBING:
				return new QuadraticProbing<>(key, nSlots);
			default:
				throw new UnsupportedOperationException("Not supported yet.");
		}
	}

}
