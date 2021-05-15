package vn.khanhpdt.playgrounds.algorithms;

import java.util.List;

public abstract class Commons {

	public static <T> void swap(T[] elements, int i, int j) {
		T temp = elements[i];
		elements[i] = elements[j];
		elements[j] = temp;
	}

	public static <T> void swap(List<T> elements, int i, int j) {
		T temp = elements.get(i);
		elements.set(i, elements.get(j));
		elements.set(j, temp);
	}

}
