package vn.khanhpdt.playgrounds.datastructures.alphabets;

/**
 * @param <E>  type of each element in the alphabet
 *
 * @author khanhpdt
 */
public interface Alphabet<E> {

	int toIndex(E key);

	E fromIndex(int index);

	int size();

}
