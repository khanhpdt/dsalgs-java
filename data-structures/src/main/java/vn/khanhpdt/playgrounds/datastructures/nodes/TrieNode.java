package vn.khanhpdt.playgrounds.datastructures.nodes;

public class TrieNode<V> {

	private V value;

	private final int linkCapacity;

	private TrieNode<V>[] links;

	private int nActiveLinks;

	public TrieNode(int linkCapacity) {
		this.linkCapacity = linkCapacity;
	}

	@SuppressWarnings("unchecked")
	private TrieNode<V>[] getLinks() {
		if (links == null) {
			// this causes excessive memory usage
			links = (TrieNode<V>[]) new TrieNode[linkCapacity];
		}
		return links;
	}

	public V getValue() {
		return value;
	}

	public TrieNode<V> getLink(int linkIndex) {
		return getLinks()[linkIndex];
	}

	public void addLink(int linkIndex) {
		// assume that each node has the same number of outgoing links
		links[linkIndex] = new TrieNode<>(linkCapacity);
		nActiveLinks++;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public boolean hasActiveLink() {
		return nActiveLinks > 0;
	}

	public void removeLink(int linkIndex) {
		links[linkIndex] = null;
		nActiveLinks--;
	}

	public int getLinkCapacity() {
		return linkCapacity;
	}
}
