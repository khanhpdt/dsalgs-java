package vn.khanhpdt.playgrounds.ctci.stacksandqueues;

/**
 * Problem 3.1: Implement three stacks with a single array.
 *
 */
public class NStacksSingleArray {

	private final int nStacks;

	private final int nItemsPerStack;

	/**
	 * A single array for three stacks
	 *
	 */
	private Object[] elements;

	private Stack[] stacks;

	public NStacksSingleArray(int nStacks) {
		this.nStacks = nStacks;

		// default value
		nItemsPerStack = 20;

		this.stacks = new Stack[nStacks];
		for (int i = 0; i < nStacks; i++) {
			this.stacks[i] = new Stack(i);
		}

		// 100 by default
		this.elements = new Object[100];
	}

	private class Stack {

		private final int stackIndex;

		private int nItems;

		private Stack(int stackIndex) {
			this.stackIndex = stackIndex;
			this.nItems = 0;
		}

		public void push(Object element) {
			elements[getPositionOnCommonArray()] = element;
			nItems++;
		}

		/**
		 * Gets the index of the element of this stack stored in the common array.
		 *
		 */
		private int getPositionOnCommonArray() {
			// the array area allocated to each stack is fixed
			return (stackIndex * nItemsPerStack) + nItems;
			// another approach is to interleave the elements in different stacks
			// e.g., element i of stack j can be put into the position at (nStacks * i + j)
		}

		public Object pop() {
			Object element = elements[getPositionOnCommonArray()];
			nItems--;
			return element;
		}
	}

	public Stack getStack(int stackIndex) {
		return stacks[stackIndex];
	}

}
