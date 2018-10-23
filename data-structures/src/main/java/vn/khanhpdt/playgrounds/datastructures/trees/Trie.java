package vn.khanhpdt.playgrounds.datastructures.trees;

import vn.khanhpdt.playgrounds.datastructures.alphabets.Alphabet;
import vn.khanhpdt.playgrounds.datastructures.nodes.TrieNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <V>  type of the value associated with the key
 *
 * @author khanhpdt
 */
public class Trie<V> {

	private final Alphabet<Character> alphabet;

	private TrieNode<V> root;

	public Trie(Alphabet<Character> alphabet) {
		this.alphabet = alphabet;
		this.root = new TrieNode<>(alphabet.size());
	}

	public void insert(String key, V value) {
		TrieNode<V> current = root;
		for (int i = 0; i < key.length(); i++) {
			// main idea of tries: each element in the key is used to guide the search for the key
			int linkIndex = alphabet.toIndex(key.charAt(i));

			// no link associated yet
			if (current.getLink(linkIndex) == null) {
				current.addLink(linkIndex);
			}

			// store value associated with the key to the last node in the chain
			if (i == key.length() - 1) {
				current.getLink(linkIndex).setValue(value);
			}

			// keep moving until all elements in the key are considered
			current = current.getLink(linkIndex);
		}
	}

	public V search(String key) {
		TrieNode<V> current = root;
		for (int i = 0; i < key.length(); i++) {
			// not found
			if (current == null) {
				return null;
			}
			current = current.getLink(alphabet.toIndex(key.charAt(i)));
		}

		// found the link chain forming the given key
		return current.getValue();
	}

	public void remove(String key) {
		remove(root, key, 0);
	}

	private boolean remove(TrieNode<V> node, String key, int currentMatchLength) {
		// key not found
		if (node == null) {
			return false;
		}

		if (currentMatchLength == key.length()) {
			// key found
			if (node.getValue() != null) {
				// remove the value
				node.setValue(null);
				return true;
			}
			// key not found
			else {
				return false;
			}
		}

		int linkIndex = alphabet.toIndex(key.charAt(currentMatchLength));
		TrieNode<V> nextNode = node.getLink(linkIndex);
		boolean found = remove(nextNode, key, currentMatchLength + 1);

		// remove a node if it does not contribute to any key
		if (found && nextNode.getValue() == null && !nextNode.hasActiveLink()) {
			node.removeLink(linkIndex);
		}

		return found;
	}

	public List<String> getKeys() {
		List<String> result = new ArrayList<>();
		findKeysFrom(root, "", result);
		return result;
	}

	private void findKeysFrom(TrieNode<V> node, String currentKey, List<String> keys) {
		if (node == null) {
			return;
		}

		// found a key
		if (node.getValue() != null) {
			keys.add(currentKey);
		}

		// this kind of traverse is similar to depth-first search in graphs.
		for (int i = 0; i < node.getLinkCapacity(); i++) {
			if (node.getLink(i) != null) {
				// might causes excessive string creations
				findKeysFrom(node.getLink(i), currentKey + alphabet.fromIndex(i), keys);
			}
		}
	}

}
