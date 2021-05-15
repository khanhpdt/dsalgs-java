package vn.khanhpdt.playgrounds.ctci.stacksandqueues;

import org.junit.Test;
import vn.khanhpdt.playgrounds.ctci.stacksandqueues.SetOfStacks;
import vn.khanhpdt.playgrounds.datastructures.nodes.SinglyLinkedNode;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author khanhpdt
 */
public class SetOfStacksTest {

	@Test
	public void testPushAndPop() {
		int stackCapacity = 5;
		int nNodes = 37;

		// given
		SetOfStacks stackSet = new SetOfStacks(stackCapacity);
		for (int i = 0; i < nNodes; i++) {
			stackSet.push(SinglyLinkedNode.from(UUID.randomUUID(), i));
		}

		// then
		for (int i = 36; i >= 0; i--) {
			assertThat("item " + i, stackSet.pop().getValue(), is(i));
		}
	}

	@Test
	public void testPopAt() {
		int stackCapacity = 5;
		int nNodes = 37;

		// given
		SetOfStacks stackSet = new SetOfStacks(stackCapacity);
		for (int i = 0; i < nNodes; i++) {
			stackSet.push(SinglyLinkedNode.from(UUID.randomUUID(), i));
		}

		// then
		for (int i = 29; i >= 25; i--) {
			assertThat("item " + i, stackSet.popAt(5, false).getValue(), is(i));
		}
		for (int i = 34; i >= 30; i--) {
			assertThat("item " + i, stackSet.popAt(5, false).getValue(), is(i));
		}
		for (int i = 36; i >= 35; i--) {
			assertThat("item " + i, stackSet.popAt(5, false).getValue(), is(i));
		}
	}

	@Test
	public void testCapacityAfterPopAt() {
		int stackCapacity = 3;
		int nNodes = 12;

		// given
		SetOfStacks stackSet = new SetOfStacks(stackCapacity);
		for (int i = 0; i < nNodes; i++) {
			stackSet.push(SinglyLinkedNode.from(UUID.randomUUID(), i));
		}

		// when
		stackSet.popAt(2, true);

		// then
		assertThat(stackSet.getStack(2).size(), is(stackCapacity));
		assertThat(stackSet.getStack(1).size(), is(stackCapacity));
		assertThat(stackSet.getStack(0).size(), is(stackCapacity - 1));
	}

	@Test
	public void testRemoveEmptyStackAfterPopAt() {
		int stackCapacity = 3;
		int nNodes = 12;

		// given
		SetOfStacks stackSet = new SetOfStacks(stackCapacity);
		for (int i = 0; i < nNodes; i++) {
			stackSet.push(SinglyLinkedNode.from(UUID.randomUUID(), i));
		}

		// when
		for (int i = 0; i < stackCapacity; i++) {
			stackSet.popAt(2, true);
		}

		// then
		assertThat("stack 2 should already be removed", stackSet.countStacks(), is(3));
	}

}
