package vn.khanhpdt.playgrounds.ctci.stacksandqueues;

import vn.khanhpdt.playgrounds.datastructures.nodes.DoublyLinkedNode;
import vn.khanhpdt.playgrounds.datastructures.queues.Queue;

import java.util.UUID;

/**
 * Problem 3.7
 *
 */
public class AnimalShelter {

	public enum AnimalType {
		DOG, CAT
	}

	private Queue<DoublyLinkedNode<UUID, AnimalType>> animalQueue;

	public AnimalShelter() {
		animalQueue = new Queue<>();
	}

	public UUID enqueue(AnimalType animal) {
		UUID uuid = UUID.randomUUID();
		animalQueue.enqueueRear(DoublyLinkedNode.from(uuid, animal));
		return uuid;
	}

	public DoublyLinkedNode<UUID, AnimalType> dequeueAny() {
		return animalQueue.dequeueFront();
	}

	public DoublyLinkedNode<UUID, AnimalType> dequeueDog() {
		return dequeue(AnimalType.DOG);
	}

	private DoublyLinkedNode<UUID, AnimalType> dequeue(AnimalType expectedAnimalType) {
		Queue<DoublyLinkedNode<UUID, AnimalType>> auxiliaryQueue = new Queue<>();

		// find the oldest animal of given type
		DoublyLinkedNode<UUID, AnimalType> oldestAnimalRunner = animalQueue.dequeueFront();
		while (oldestAnimalRunner != null && oldestAnimalRunner.getValue() != expectedAnimalType) {
			// save the dequeued animal since we still need to keep it
			auxiliaryQueue.enqueueRear(oldestAnimalRunner);
			oldestAnimalRunner = animalQueue.dequeueFront();
		}

		// not found
		if (oldestAnimalRunner == null) {
			// since we already moved all the animals to the temporary queue
			animalQueue = auxiliaryQueue;
			return null;
		}

		// found
		DoublyLinkedNode<UUID, AnimalType> result = oldestAnimalRunner;

		// Move the remaining animals to the temporary queue which is storing the oldest animals of other type.
		// We do this to preserve the current order of the animals.
		// A better way is to merge these two queues only by appropriately updating their rear and front pointers.
		// But here we assume that only enqueue and dequeue operations are provided.
		oldestAnimalRunner = animalQueue.dequeueFront();
		while (oldestAnimalRunner != null) {
			auxiliaryQueue.enqueueRear(oldestAnimalRunner);
			oldestAnimalRunner = animalQueue.dequeueFront();
		}
		animalQueue = auxiliaryQueue;

		return result;
	}

	public DoublyLinkedNode<UUID, AnimalType> dequeueCat() {
		return dequeue(AnimalType.CAT);
	}
}
