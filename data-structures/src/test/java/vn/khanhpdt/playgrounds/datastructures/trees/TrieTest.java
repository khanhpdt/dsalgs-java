package vn.khanhpdt.playgrounds.datastructures.trees;

import org.junit.Test;
import vn.khanhpdt.playgrounds.datastructures.alphabets.LowerCaseLetterAlphabet;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

public class TrieTest {

	private List<String> defaultKeys = Arrays.asList("by", "sea", "sells", "she", "shells", "the");
	private List<Integer> defaultValues = Arrays.asList(4, 2, 1, 0, 3, 5);
	private Trie<Integer> defaultTrie = defaultTrie();

	private Trie<Integer> defaultTrie() {
		Trie<Integer> trie = new Trie<>(LowerCaseLetterAlphabet.INSTANCE);
		for (int i = 0; i < defaultKeys.size(); i++) {
			trie.insert(defaultKeys.get(i), defaultValues.get(i));
		}
		return trie;
	}

	@Test
	public void testSearch_found() {
		for (int i = 0; i < defaultKeys.size(); i++) {
			assertThat(defaultTrie.search(defaultKeys.get(i)), is(defaultValues.get(i)));
		}
	}

	@Test
	public void testSearch_notFound() {
		assertThat(defaultTrie.search("shell"), is(nullValue()));
		assertThat(defaultTrie.search("notExist"), is(nullValue()));
	}

	@Test
	public void testRemove() {
		String key = "shells";
		assertThat(defaultTrie.search(key), is(3));

		defaultTrie.remove(key);

		assertThat(defaultTrie.search(key), is(nullValue()));
	}

	@Test
	public void testGetKeys() {
		assertThat(defaultTrie.getKeys(), containsInAnyOrder(defaultKeys.toArray()));
	}

	@Test
	public void testGetKeysAfterRemove() {
		String key = "by";
		defaultTrie.remove(key);
		assertThat(defaultTrie.getKeys(), containsInAnyOrder("sea", "sells", "she", "shells", "the"));
	}
}
