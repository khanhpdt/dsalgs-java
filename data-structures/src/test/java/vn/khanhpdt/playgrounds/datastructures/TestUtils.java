package vn.khanhpdt.playgrounds.datastructures;

import vn.khanhpdt.playgrounds.datastructures.nodes.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

public class TestUtils {

	private static final Random RANDOM = new Random();

	public static List<SinglyLinkedNode<UUID, Integer>> randomSinglyNodes(int size) {
		List<SinglyLinkedNode<UUID, Integer>> nodes = new ArrayList<>();
		IntStream.rangeClosed(0, size).forEach(i -> nodes.add(SinglyLinkedNode.fromKey(UUID.randomUUID())));
		return nodes;
	}

	public static SinglyLinkedNode<UUID, Integer> randomSinglyNode() {
		return SinglyLinkedNode.fromKey(UUID.randomUUID());
	}

	public static List<DoublyLinkedNode<UUID, Integer>> randomDoublyNodes(int size) {
		List<DoublyLinkedNode<UUID, Integer>> nodes = new ArrayList<>();
		IntStream.rangeClosed(0, size).forEach(i -> nodes.add(randomDoublyLinkedNode()));
		return nodes;
	}

	public static BinarySearchTreeNode<UUID, Integer> randomBinaryTreeNode() {
		return BinarySearchTreeNode.from(UUID.randomUUID(), RANDOM.nextInt());
	}

	public static DoublyLinkedNode<UUID, Integer> randomDoublyLinkedNode() {
		return DoublyLinkedNode.from(UUID.randomUUID(), new Random().nextInt());
	}

}
