package vn.khanhpdt.playgrounds.datastructures.nodes;

import vn.khanhpdt.playgrounds.datastructures.graphs.GraphEdge;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @param <K>  type of vertex key
 * @param <V>  type of vertex value
 */
public class GraphVertex<K, V> implements DoublyLinked<GraphVertex<K, V>> {

	private final Node<K, V> content;

	private List<GraphVertex<K, V>> adjacents = new ArrayList<>();

	// outgoing edges from this vertex
	private final Map<GraphVertex<K, V>, GraphEdge<K, V>> outgoingEdges = new HashMap<>();

	// for searching (BFS, DFS)
	private int distance = Integer.MAX_VALUE;
	private Color color = Color.WHITE;
	private GraphVertex<K, V> predecessor;
	private int discoveredTime;
	private int visitedTime;

	// we need to put this vertex into queue during BFS
	private GraphVertex<K, V> next;
	private GraphVertex<K, V> previous;

	// for finding minimum spanning tree. this represents the minimum weight among the edges connecting
	// this vertex to the vertices already in the MST
	private double minWeightToMST;

	public GraphVertex(K key) {
		this.content = new Node<>(key);
	}

	public GraphVertex(K key, V value) {
		this.content = new Node<>(key, value);
	}

	public GraphVertex(GraphVertex<K, V> other) {
		this.content = new Node<>(other.getContent().getKey(), other.getContent().getValue());
	}

	public Node<K, V> getContent() {
		return this.content;
	}

	public K getKey() {
		return content.getKey();
	}

	public Color getColor() {
		return color;
	}

	public List<GraphVertex<K, V>> getAdjacents() {
		return adjacents;
	}

	@Override
	public int hashCode() {
		int result = 17;
		int c = this.getKey().hashCode();
		return 31 + result * c;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof GraphVertex)) {
			return false;
		}

		GraphVertex otherNode = (GraphVertex) obj;
		return this.getKey().equals(otherNode.getKey());
	}

	public int getDistance() {
		return distance;
	}

	private void setDistance(int distance) {
		this.distance = distance;
	}

	@Override
	public GraphVertex<K, V> getNext() {
		return this.next;
	}

	@Override
	public void setNext(GraphVertex<K, V> next) {
		this.next = next;
	}

	@Override
	public GraphVertex<K, V> getPrevious() {
		return this.previous;
	}

	@Override
	public void setPrevious(GraphVertex<K, V> previous) {
		this.previous = previous;
	}

	public boolean isNotDiscovered() {
		return this.color == Color.WHITE;
	}

	public boolean isDiscovered() {
		return this.color != Color.WHITE;
	}

	public void markVisited() {
		setColor(Color.BLACK);
	}

	public void markVisited(int time) {
		setColor(Color.BLACK);
		setVisitedTime(time);
	}

	public void markDiscovered() {
		this.color = Color.GRAY;
	}

	public void markDiscovered(GraphVertex<K, V> predecessor, int time) {
		setPredecessor(predecessor);
		setDistance(predecessor == null ? 0 : predecessor.getDistance() + 1);
		setColor(Color.GRAY);
		setDiscoveredTime(time);
	}

	private void setColor(Color color) {
		this.color = color;
	}

	public boolean isNotVisited() {
		return this.color != Color.BLACK;
	}

	public void setAdjacents(List<GraphVertex<K, V>> adjacents) {
		this.adjacents = adjacents;
	}

	private void setDiscoveredTime(int discoveredTime) {
		this.discoveredTime = discoveredTime;
	}

	private void setVisitedTime(int visitedTime) {
		this.visitedTime = visitedTime;
	}

	public List<GraphEdge<K, V>> getOutgoingEdges() {
		return new ArrayList<>(outgoingEdges.values());
	}

	public void addEdge(GraphVertex<K, V> toVertex) {
		addEdge(toVertex, 0);
	}

	public void addEdge(GraphVertex<K, V> toVertex, int weight) {
		adjacents.add(toVertex);
		outgoingEdges.put(toVertex, new GraphEdge<>(this, toVertex, weight));
	}

	public double getWeightOfEdgeTo(GraphVertex<K, V> toVertex) {
		return outgoingEdges.get(toVertex).getWeight();
	}

	public GraphEdge<K, V> getEdgeTo(GraphVertex<K, V> toVertex) {
		return outgoingEdges.get(toVertex);
	}

	public double getMinWeightToMST() {
		return minWeightToMST;
	}

	public void setMinWeightToMST(double minWeightToMST) {
		this.minWeightToMST = minWeightToMST;
	}

	public void setPredecessor(GraphVertex<K, V> predecessor) {
		this.predecessor = predecessor;
	}

	public GraphVertex<K, V> getPredecessor() {
		return predecessor;
	}

	public void resetAfterTraverse() {
		distance = Integer.MAX_VALUE;
		color = Color.WHITE;
		predecessor = null;
		discoveredTime = 0;
		visitedTime = 0;
	}
}
