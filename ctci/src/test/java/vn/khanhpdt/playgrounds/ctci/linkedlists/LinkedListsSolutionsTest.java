package vn.khanhpdt.playgrounds.ctci.linkedlists;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;
import vn.khanhpdt.playgrounds.ctci.linkedlists.LinkedListsSolutions;
import vn.khanhpdt.playgrounds.datastructures.linkedlists.DoubleEndedDoublyLinkedList;
import vn.khanhpdt.playgrounds.datastructures.linkedlists.DoublyLinkedList;
import vn.khanhpdt.playgrounds.datastructures.linkedlists.LinkedLists;
import vn.khanhpdt.playgrounds.datastructures.linkedlists.SinglyLinkedList;
import vn.khanhpdt.playgrounds.datastructures.nodes.DoublyLinkedNode;
import vn.khanhpdt.playgrounds.datastructures.nodes.SinglyLinkedNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

public class LinkedListsSolutionsTest {

	private static final Random RANDOM = new Random();

	@Test
	public void testRemoveDuplicates() {
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> list = createSinglyLinkedListWithDuplicates();

		LinkedListsSolutions.removeDuplicates(list);

		List<SinglyLinkedNode<UUID, Integer>> nodes = LinkedLists.traverse(list);
		assertThat(nodes, hasSize(3));
	}

	private SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> createSinglyLinkedListWithDuplicates() {
		UUID uuid1 = UUID.randomUUID();
		UUID uuid2 = UUID.randomUUID();
		UUID uuid3 = UUID.randomUUID();

		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> list = new SinglyLinkedList<>();
		list.insertFirst(SinglyLinkedNode.fromKey(uuid1));
		list.insertFirst(SinglyLinkedNode.fromKey(uuid2));
		list.insertFirst(SinglyLinkedNode.fromKey(uuid3));
		list.insertFirst(SinglyLinkedNode.fromKey(uuid1));
		list.insertFirst(SinglyLinkedNode.fromKey(uuid2));
		list.insertFirst(SinglyLinkedNode.fromKey(uuid3));

		return list;
	}

	@Test
	public void testRemoveDuplicates_2() {
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> list = createSinglyLinkedListWithDuplicates();

		LinkedListsSolutions.removeDuplicates_2(list);

		List<SinglyLinkedNode<UUID, Integer>> nodes = LinkedLists.traverse(list);
		assertThat(nodes, hasSize(3));
	}

	@Test
	public void testGetKthToLastKnownLength() {
		int nItems = 10;
		List<SinglyLinkedNode<UUID, Integer>> items = randomItems(nItems);

		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> list = createSinglyLinkedList(items);

		for (int k = 0; k < nItems; k++) {
			SinglyLinkedNode<UUID, Integer> item = LinkedListsSolutions.getKthToLastKnownLength(list, k);
			assertThat(item, is(items.get(nItems - 1 - k)));
		}
	}

	@Test
	public void testGetKthToLastUnknownLength() {
		int nItems = 10;
		List<SinglyLinkedNode<UUID, Integer>> items = randomItems(nItems);

		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> list = createSinglyLinkedList(items);

		for (int k = 0; k < nItems; k++) {
			SinglyLinkedNode<UUID, Integer> item = LinkedListsSolutions.getKthToLastUnknownLength(list, k);
			assertThat("item " + k, item, is(items.get(nItems - 1 - k)));
		}
	}

	private List<SinglyLinkedNode<UUID, Integer>> randomItems(int nItems) {
		List<SinglyLinkedNode<UUID, Integer>> items = new ArrayList<>();
		IntStream.range(0, nItems).forEach(i -> items.add(SinglyLinkedNode.from(UUID.randomUUID(), RANDOM.nextInt())));
		return items;
	}

	private SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> createSinglyLinkedList(List<SinglyLinkedNode<UUID, Integer>> items) {
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> list = new SinglyLinkedList<>();
		items.forEach(list::insertLast);
		return list;
	}

	@Test
	public void testRemoveMiddle() throws Exception {
		int nItems = 10;
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> linkedList = createSinglyLinkedList(nItems);

		SinglyLinkedNode<UUID, Integer> removedItem = linkedList.get(3);
		UUID removedItemKey = removedItem.getKey();
		LinkedListsSolutions.removeMiddleItem(removedItem);

		List<SinglyLinkedNode<UUID, Integer>> remainingItems = LinkedLists.traverse(linkedList);
		assertThat(remainingItems, hasSize(nItems - 1));
		assertThat(remainingItems.stream().map(SinglyLinkedNode::getKey).collect(Collectors.toList()),
				not(hasItem(removedItemKey)));
	}

	private SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> createSinglyLinkedList(int nItems) {
		return createSinglyLinkedList(randomItems(nItems));
	}

	@Test
	public void testPartition() throws Exception {
		// given
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> linkedList = createSinglyLinkedList(15);

		// when
		Integer partitioningValue = linkedList.get(5).getContent().getValue();
		LinkedListsSolutions.partition(linkedList, partitioningValue);

		// then
		SinglyLinkedNode<UUID, Integer> current = linkedList.getHead();
		boolean smaller = current.getValue() < partitioningValue;
		while (current != null) {
			if (smaller && current.getValue() >= partitioningValue) {
				smaller = false;
			}
			if (smaller) {
				assertThat(current.getValue(), Matchers.lessThan(partitioningValue));
			} else {
				assertThat(current.getValue(), Matchers.greaterThanOrEqualTo(partitioningValue));
			}

			current = current.getNext();
		}
	}

	@Test
	public void testSumBackwardDigits_1() throws Exception {
		// 617
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> firstNumber = joinDigitsBackward(6, 1, 7);

		// 295
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> secondNumber = joinDigitsBackward(2, 9, 5);

		// 617 + 295 = 912
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> sum = LinkedListsSolutions.sumBackwardDigits(firstNumber, secondNumber);
		assertThat(sum, singlyListContainingDigitsInOrder(2, 1, 9));
	}

	private static Matcher<SinglyLinkedList<SinglyLinkedNode<UUID, Integer>>> singlyListContainingDigitsInOrder(Integer... digits) {
		return new TypeSafeMatcher<SinglyLinkedList<SinglyLinkedNode<UUID, Integer>>>() {
			@Override
			protected boolean matchesSafely(SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> list) {
				for (int i = 0; i < digits.length; i++) {
					if (!list.get(i).getValue().equals(digits[i])) {
						return false;
					}
				}
				return true;
			}

			@Override
			public void describeTo(Description description) {
				description.appendValueList("a list with ", ", ", "", digits);
			}
		};
	}

	private SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> joinDigitsBackward(Integer... digits) {
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> result = new SinglyLinkedList<>();
		for (Integer digit : digits) {
			result.insertFirst(SinglyLinkedNode.from(UUID.randomUUID(), digit));
		}
		return result;
	}

	@Test
	public void testSumBackwardDigits_2() throws Exception {
		// 1548
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> firstNumber = joinDigitsBackward(1,5,4,8);

		// 295
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> secondNumber = joinDigitsBackward(2, 9, 5);

		// 1548 + 295 = 1843
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> sum = LinkedListsSolutions.sumBackwardDigits(firstNumber, secondNumber);
		assertThat(sum, singlyListContainingDigitsInOrder(3, 4, 8, 1));
	}

	@Test
	public void testSumForwardDigits_1() throws Exception {
		// 617
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> firstNumber = joinDigitsForward(6, 1, 7);

		// 295
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> secondNumber = joinDigitsForward(2, 9, 5);

		// 617 + 295 = 912
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> sum = LinkedListsSolutions.sumForwardDigits(firstNumber, secondNumber);
		assertThat(sum, singlyListContainingDigitsInOrder(9, 1, 2));
	}

	private SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> joinDigitsForward(Integer... digits) {
		return joinDigitsBackward(digits).reverse();
	}

	@Test
	public void testSumForwardDigits_2() throws Exception {
		// 1548
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> firstNumber = joinDigitsForward(1, 5, 4, 8);

		// 295
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> secondNumber = joinDigitsForward(2, 9, 5);

		// 1548 + 295 = 1843
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> sum = LinkedListsSolutions.sumForwardDigits(firstNumber, secondNumber);
		assertThat(sum, singlyListContainingDigitsInOrder(1, 8, 4, 3));
	}

	@Test
	public void testSumForwardDigits2_1() throws Exception {
		// 617
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> firstNumber = joinDigitsForward(6, 1, 7);

		// 285
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> secondNumber = joinDigitsForward(2, 8, 5);

		// 617 + 285 = 902
		DoublyLinkedList sum = LinkedListsSolutions.sumForwardDigits_2(firstNumber, secondNumber);
		assertThat(sum, doublyListContainingDigitsInOrder(9, 0, 2));
	}

	private static Matcher<DoublyLinkedList> doublyListContainingDigitsInOrder(Integer... digits) {
		return new TypeSafeMatcher<DoublyLinkedList>() {
			@Override
			protected boolean matchesSafely(DoublyLinkedList list) {
				DoublyLinkedNode current = list.getHead();
				int i = 0;
				while (current != null) {
					if (!current.getContent().getValue().equals(digits[i])) {
						return false;
					}
					current = current.getNext();
					i++;
				}
				return true;
			}

			@Override
			public void describeTo(Description description) {
				description.appendValueList("a doubly linked list with ", ", ", "", digits);
			}
		};
	}

	@Test
	public void testSumForwardDigits2_2() throws Exception {
		// 12617
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> firstNumber = joinDigitsForward(1, 2, 6, 1, 7);

		// 285
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> secondNumber = joinDigitsForward(2, 8, 5);

		// 12617 + 285 = 12902
		DoublyLinkedList sum = LinkedListsSolutions.sumForwardDigits_2(firstNumber, secondNumber);
		assertThat(sum, doublyListContainingDigitsInOrder(1, 2, 9, 0, 2));
	}

	@Test
	public void testSumForwardDigits2_3() throws Exception {
		// 90
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> firstNumber = joinDigitsForward(9, 0);

		// 10
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> secondNumber = joinDigitsForward(1, 0);

		// 90 + 10 = 100
		DoublyLinkedList sum = LinkedListsSolutions.sumForwardDigits_2(firstNumber, secondNumber);
		assertThat(sum, doublyListContainingDigitsInOrder(1, 0, 0));
	}

	@Test
	public void testSumForwardDigits2_4() throws Exception {
		// 9999
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> firstNumber = joinDigitsForward(9, 9, 9, 9);

		// 1
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> secondNumber = joinDigitsForward(1);

		// 9999 + 1 = 10000
		DoublyLinkedList sum = LinkedListsSolutions.sumForwardDigits_2(firstNumber, secondNumber);
		assertThat(sum, doublyListContainingDigitsInOrder(1, 0, 0, 0, 0));
	}

	private static Matcher<DoubleEndedDoublyLinkedList<DoublyLinkedNode<UUID, Integer>>> doubleEndedDoublyListContainingDigitsInOrder(Integer... digits) {
		return new TypeSafeMatcher<DoubleEndedDoublyLinkedList<DoublyLinkedNode<UUID, Integer>>>() {
			@Override
			protected boolean matchesSafely(DoubleEndedDoublyLinkedList<DoublyLinkedNode<UUID, Integer>> list) {
				DoublyLinkedNode<UUID, Integer> current = list.getHead();
				int i = 0;
				while (current != null) {
					if (!current.getContent().getValue().equals(digits[i])) {
						return false;
					}
					current = current.getNext();
					i++;
				}
				return true;
			}

			@Override
			public void describeTo(Description description) {
				description.appendValueList("a double ended list with ", ", ", "", digits);
			}
		};
	}

	@Test
	public void testSumForwardDigits3_1() throws Exception {
		// 617
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> firstNumber = joinDigitsForward(6, 1, 7);

		// 285
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> secondNumber = joinDigitsForward(2, 8, 5);

		// 617 + 285 = 902
		DoubleEndedDoublyLinkedList<DoublyLinkedNode<UUID, Integer>> sum = LinkedListsSolutions.sumForwardDigits_3(firstNumber, secondNumber);
		assertThat(sum, doubleEndedDoublyListContainingDigitsInOrder(9, 0, 2));
	}

	@Test
	public void testSumForwardDigits3_2() throws Exception {
		// 12617
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> firstNumber = joinDigitsForward(1, 2, 6, 1, 7);

		// 285
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> secondNumber = joinDigitsForward(2, 8, 5);

		// 12617 + 285 = 12902
		DoubleEndedDoublyLinkedList<DoublyLinkedNode<UUID, Integer>> sum = LinkedListsSolutions.sumForwardDigits_3(firstNumber, secondNumber);
		assertThat(sum, doubleEndedDoublyListContainingDigitsInOrder(1, 2, 9, 0, 2));
	}

	@Test
	public void testSumForwardDigits3_3() throws Exception {
		// 90
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> firstNumber = joinDigitsForward(9, 0);

		// 10
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> secondNumber = joinDigitsForward(1, 0);

		// 90 + 10 = 100
		DoubleEndedDoublyLinkedList<DoublyLinkedNode<UUID, Integer>> sum = LinkedListsSolutions.sumForwardDigits_3(firstNumber, secondNumber);
		assertThat(sum, doubleEndedDoublyListContainingDigitsInOrder(1, 0, 0));
	}

	@Test
	public void testSumForwardDigits3_4() throws Exception {
		// 9999
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> firstNumber = joinDigitsForward(9, 9, 9, 9);

		// 1
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> secondNumber = joinDigitsForward(1);

		// 9999 + 1 = 10000
		DoubleEndedDoublyLinkedList<DoublyLinkedNode<UUID, Integer>> sum = LinkedListsSolutions.sumForwardDigits_3(firstNumber, secondNumber);
		assertThat(sum, doubleEndedDoublyListContainingDigitsInOrder(1, 0, 0, 0, 0));
	}

	@Test
	public void testFirstOfTheLoop_1() throws Exception {
		SinglyLinkedNode<UUID, Integer> theFirstOfTheLoop = SinglyLinkedNode.fromKey(UUID.randomUUID());
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> linkedList = new SinglyLinkedList<>();
		linkedList.insertFirst(theFirstOfTheLoop);
		linkedList.insertFirst(SinglyLinkedNode.fromKey(UUID.randomUUID()));
		linkedList.insertFirst(SinglyLinkedNode.fromKey(UUID.randomUUID()));
		linkedList.insertFirst(theFirstOfTheLoop);
		linkedList.insertFirst(SinglyLinkedNode.fromKey(UUID.randomUUID()));
		linkedList.insertFirst(SinglyLinkedNode.fromKey(UUID.randomUUID()));

		SinglyLinkedNode<UUID, Integer> firstOfTheLoop = LinkedListsSolutions.getFirstOfTheLoop(linkedList);

		assertThat(firstOfTheLoop, is(theFirstOfTheLoop));
	}

	@Test
	public void testFirstOfTheLoop_2() throws Exception {
		SinglyLinkedNode<UUID, Integer> theFirstOfTheFirstLoop = SinglyLinkedNode.fromKey(UUID.randomUUID());
		SinglyLinkedNode<UUID, Integer> theFirstOfTheSecondLoop = SinglyLinkedNode.fromKey(UUID.randomUUID());
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> linkedList = new SinglyLinkedList<>();
		linkedList.insertFirst(theFirstOfTheSecondLoop);
		linkedList.insertFirst(theFirstOfTheFirstLoop);
		linkedList.insertFirst(SinglyLinkedNode.fromKey(UUID.randomUUID()));
		linkedList.insertFirst(SinglyLinkedNode.fromKey(UUID.randomUUID()));
		linkedList.insertFirst(theFirstOfTheSecondLoop);
		linkedList.insertFirst(theFirstOfTheFirstLoop);
		linkedList.insertFirst(SinglyLinkedNode.fromKey(UUID.randomUUID()));
		linkedList.insertFirst(SinglyLinkedNode.fromKey(UUID.randomUUID()));

		SinglyLinkedNode<UUID, Integer> firstOfTheLoop = LinkedListsSolutions.getFirstOfTheLoop(linkedList);

		assertThat(firstOfTheLoop, is(theFirstOfTheFirstLoop));
	}

	@Test
	public void testFirstOfTheLoop_3() throws Exception {
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> linkedList = new SinglyLinkedList<>();
		linkedList.insertFirst(SinglyLinkedNode.fromKey(UUID.randomUUID()));
		linkedList.insertFirst(SinglyLinkedNode.fromKey(UUID.randomUUID()));
		linkedList.insertFirst(SinglyLinkedNode.fromKey(UUID.randomUUID()));
		linkedList.insertFirst(SinglyLinkedNode.fromKey(UUID.randomUUID()));

		SinglyLinkedNode<UUID, Integer> firstOfTheLoop = LinkedListsSolutions.getFirstOfTheLoop(linkedList);

		assertThat(firstOfTheLoop, is(nullValue()));
	}

	@Test
	public void testFirstOfTheLoop2_1() throws Exception {
		SinglyLinkedNode<UUID, Integer> theFirstOfTheLoop = SinglyLinkedNode.fromKey(UUID.randomUUID());
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> linkedList = new SinglyLinkedList<>();
		linkedList.insertFirst(theFirstOfTheLoop);
		linkedList.insertFirst(SinglyLinkedNode.fromKey(UUID.randomUUID()));
		linkedList.insertFirst(SinglyLinkedNode.fromKey(UUID.randomUUID()));
		linkedList.insertFirst(theFirstOfTheLoop);
		linkedList.insertFirst(SinglyLinkedNode.fromKey(UUID.randomUUID()));
		linkedList.insertFirst(SinglyLinkedNode.fromKey(UUID.randomUUID()));

		SinglyLinkedNode<UUID, Integer> firstOfTheLoop = LinkedListsSolutions.getFirstOfTheLoop_2(linkedList);

		assertThat(firstOfTheLoop, is(theFirstOfTheLoop));
	}

	@Test
	public void testFirstOfTheLoop2_2() throws Exception {
		SinglyLinkedNode<UUID, Integer> theFirstOfTheFirstLoop = SinglyLinkedNode.fromKey(UUID.randomUUID());
		SinglyLinkedNode<UUID, Integer> theFirstOfTheSecondLoop = SinglyLinkedNode.fromKey(UUID.randomUUID());
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> linkedList = new SinglyLinkedList<>();
		linkedList.insertFirst(theFirstOfTheSecondLoop);
		linkedList.insertFirst(theFirstOfTheFirstLoop);
		linkedList.insertFirst(SinglyLinkedNode.fromKey(UUID.randomUUID()));
		linkedList.insertFirst(SinglyLinkedNode.fromKey(UUID.randomUUID()));
		linkedList.insertFirst(theFirstOfTheSecondLoop);
		linkedList.insertFirst(theFirstOfTheFirstLoop);
		linkedList.insertFirst(SinglyLinkedNode.fromKey(UUID.randomUUID()));
		linkedList.insertFirst(SinglyLinkedNode.fromKey(UUID.randomUUID()));

		SinglyLinkedNode<UUID, Integer> firstOfTheLoop = LinkedListsSolutions.getFirstOfTheLoop_2(linkedList);

		assertThat(firstOfTheLoop, is(theFirstOfTheFirstLoop));
	}

	@Test
	public void testFirstOfTheLoop2_3() throws Exception {
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> linkedList = new SinglyLinkedList<>();
		linkedList.insertFirst(SinglyLinkedNode.fromKey(UUID.randomUUID()));
		linkedList.insertFirst(SinglyLinkedNode.fromKey(UUID.randomUUID()));
		linkedList.insertFirst(SinglyLinkedNode.fromKey(UUID.randomUUID()));
		linkedList.insertFirst(SinglyLinkedNode.fromKey(UUID.randomUUID()));

		SinglyLinkedNode<UUID, Integer> firstOfTheLoop = LinkedListsSolutions.getFirstOfTheLoop_2(linkedList);

		assertThat(firstOfTheLoop, is(nullValue()));
	}

	@Test
	public void testCheckPalindrome_1() throws Exception {
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> linkedList = new SinglyLinkedList<>();
		linkedList.insertFirst(SinglyLinkedNode.from(UUID.randomUUID(), 1));
		linkedList.insertFirst(SinglyLinkedNode.from(UUID.randomUUID(), 2));
		linkedList.insertFirst(SinglyLinkedNode.from(UUID.randomUUID(), 3));
		linkedList.insertFirst(SinglyLinkedNode.from(UUID.randomUUID(), 4));
		linkedList.insertFirst(SinglyLinkedNode.from(UUID.randomUUID(), 3));
		linkedList.insertFirst(SinglyLinkedNode.from(UUID.randomUUID(), 2));
		linkedList.insertFirst(SinglyLinkedNode.from(UUID.randomUUID(), 1));

		assertThat(LinkedListsSolutions.checkPalindrome(linkedList), is(true));
	}

	@Test
	public void testCheckPalindrome_2() throws Exception {
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> linkedList = new SinglyLinkedList<>();
		linkedList.insertFirst(SinglyLinkedNode.from(UUID.randomUUID(), 1));
		linkedList.insertFirst(SinglyLinkedNode.from(UUID.randomUUID(), 2));
		linkedList.insertFirst(SinglyLinkedNode.from(UUID.randomUUID(), 3));
		linkedList.insertFirst(SinglyLinkedNode.from(UUID.randomUUID(), 4));
		linkedList.insertFirst(SinglyLinkedNode.from(UUID.randomUUID(), 5));
		linkedList.insertFirst(SinglyLinkedNode.from(UUID.randomUUID(), 2));
		linkedList.insertFirst(SinglyLinkedNode.from(UUID.randomUUID(), 1));

		assertThat(LinkedListsSolutions.checkPalindrome(linkedList), is(false));
	}
}