package vn.khanhpdt.playgrounds.datastructures.tables;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import vn.khanhpdt.playgrounds.datastructures.nodes.Node;
import vn.khanhpdt.playgrounds.datastructures.tables.probings.ProbingMethodName;

import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author khanhpdt
 */
@RunWith(Parameterized.class)
public class HashTableTest {

	private static final int DEFAULT_CAPACITY = 11;

	private static final String HASH_TABLE_CHAINING = "HashTableChaining";
	private static final String HASH_TABLE_OPEN_ADDRESSING_LINEAR_PROBING = "HashTableOpenAddressing_LinearProbing";
	private static final String HASH_TABLE_OPEN_ADDRESSING_QUADRATIC_PROBING = "HashTableOpenAddressing_QuadraticProbing";

	@Parameterized.Parameters(name = "{index}: {0}")
	public static Iterable<? extends String> data() {
		return Arrays.asList(HASH_TABLE_CHAINING, HASH_TABLE_OPEN_ADDRESSING_LINEAR_PROBING, HASH_TABLE_OPEN_ADDRESSING_QUADRATIC_PROBING);
	}

	@Parameterized.Parameter
	public String hashTableType; // must be public

	private Random random = new Random();

	private HashTable hashTable;

	@Before
	public void before() {
		switch (hashTableType) {
			case HASH_TABLE_CHAINING:
				hashTable = new HashTableChaining();
				break;
			case HASH_TABLE_OPEN_ADDRESSING_LINEAR_PROBING:
				hashTable = new HashTableOpenAddressing(ProbingMethodName.LINEAR_PROBING);
				break;
			case HASH_TABLE_OPEN_ADDRESSING_QUADRATIC_PROBING:
				hashTable = new HashTableOpenAddressing(32, ProbingMethodName.QUADRATIC_PROBING);
				break;
			default:
				throw new UnsupportedOperationException(hashTableType + ": not supported");
		}
	}

	@Test
	public void testInsert() throws Exception {
		Node<UUID, Integer> item = new Node<>(UUID.randomUUID(), random.nextInt());

		hashTable.insert(item);

		Node<UUID, Integer> inserted = hashTable.search(item.getKey());
		assertThat(inserted, is(item));
	}

	@Test
	public void testInsertMultiple() throws Exception {
		for (int i = 0; i < DEFAULT_CAPACITY; i ++) {
			Node<UUID, Integer> item = new Node<>(UUID.randomUUID(), random.nextInt());

			hashTable.insert(item);

			Node<UUID, Integer> inserted = hashTable.search(item.getKey());
			assertThat(inserted, is(item));
		}
	}

	@Test
	public void testSizeIncreased() throws Exception {
		int size = DEFAULT_CAPACITY;

		for (int i = 0; i < size; i ++) {
			Node<UUID, Integer> item = new Node<>(UUID.randomUUID(), random.nextInt());
			hashTable.insert(item);
		}

		assertThat(hashTable.size(), is(size));
	}

	@Test
	public void testSizeDecreased() throws Exception {
		Node<UUID, Integer> item1 = new Node<>(UUID.randomUUID(), random.nextInt());
		insertTo(item1);
		Node<UUID, Integer> item2 = new Node<>(UUID.randomUUID(), random.nextInt());
		insertTo(item2);
		Node<UUID, Integer> item3 = new Node<>(UUID.randomUUID(), random.nextInt());
		insertTo(item3);

		assertThat(hashTable.size(), is(3));

		remove(item3);
		remove(item2);

		assertThat(hashTable.size(), is(1));
	}

	@Test
	public void testRemove() throws Exception {
		Node<UUID, Integer> item = new Node<>(UUID.randomUUID(), random.nextInt());
		hashTable.insert(item);

		hashTable.remove(item.getKey());

		assertThat(hashTable.search(item.getKey()), Matchers.nullValue());
	}

	@Test
	public void testCompositeActions() throws Exception {
		Node<UUID, Integer> item1 = new Node<>(UUID.randomUUID(), random.nextInt());
		insertTo(item1);
		Node<UUID, Integer> item2 = new Node<>(UUID.randomUUID(), random.nextInt());
		insertTo(item2);
		Node<UUID, Integer> item3 = new Node<>(UUID.randomUUID(), random.nextInt());
		insertTo(item3);

		remove(item3);
		remove(item1);

		Node<UUID, Integer> item4 = new Node<>(UUID.randomUUID(), random.nextInt());
		insertTo(item4);
		Node<UUID, Integer> item5 = new Node<>(UUID.randomUUID(), random.nextInt());
		insertTo(item5);

		remove(item2);
		remove(item4);
		remove(item5);
	}

	private void remove(Node<UUID, Integer> item) {
		hashTable.remove(item.getKey());
		assertThat(hashTable.search(item.getKey()), Matchers.nullValue());
	}

	private void insertTo(Node<UUID, Integer> item) {
		hashTable.insert(item);
		assertThat(hashTable.search(item.getKey()), is(item));
	}

}
