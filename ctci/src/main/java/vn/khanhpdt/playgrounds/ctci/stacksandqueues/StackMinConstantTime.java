package vn.khanhpdt.playgrounds.ctci.stacksandqueues;

import vn.khanhpdt.playgrounds.datastructures.nodes.SinglyLinkedNode;
import vn.khanhpdt.playgrounds.datastructures.stacks.Stack;

import java.util.UUID;

/**
 * Problem 3.2
 *
 * @author khanhpdt
 */
public class StackMinConstantTime {

	private Stack<SinglyLinkedNode<UUID, Comparable>> elements;

	/**
	 * Store the minimum elements.
	 *
	 */
	private Stack<SinglyLinkedNode<UUID, Comparable>> mins;

	public StackMinConstantTime() {
		this.elements = new Stack<>();
		this.mins = new Stack<>();
	}

	public void push(Comparable object) {
		SinglyLinkedNode<UUID, Comparable> newNode = SinglyLinkedNode.from(UUID.randomUUID(), object);
		elements.push(newNode);

		// keep track of the minimum element
		SinglyLinkedNode<UUID, Comparable> currentMin = mins.peek();
		// first node
		if (currentMin == null) {
			mins.push(newNode);
		}
		// new min
		// note that the new min is also the one equal to the current min. otherwise, we will lose track of the minimum
		// element when we pop the minimum out.
		else if (newNode.getValue().compareTo(currentMin.getValue()) <= 0) {
			mins.push(newNode);
		}
	}

	public Comparable pop() {
		Comparable result = elements.pop().getValue();

		// remove the corresponding min
		if (result.equals(mins.peek().getValue())) {
			mins.pop();
		}

		return result;
	}

	public Comparable min() {
		return mins.peek().getValue();
	}

}
