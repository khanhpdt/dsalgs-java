package vn.khanhpdt.playgrounds.ctci.stacksandqueues;

import vn.khanhpdt.playgrounds.datastructures.nodes.SinglyLinkedNode;
import vn.khanhpdt.playgrounds.datastructures.stacks.Stack;

import java.util.*;

/**
 * Problem 3.4
 *
 */
public class TowersOfHanoi {

	private static final int N_TOWERS = 3;

	private List<SinglyLinkedNode<UUID, Integer>> disks;

	private List<Stack<SinglyLinkedNode<UUID, Integer>>> towers;

	public TowersOfHanoi(int nDisks) {
		initDisks(nDisks);
		initTowers();
		initSetup();
	}

	private void initDisks(int nDisks) {
		this.disks = new ArrayList<>();
		for (int i = 0; i < nDisks; i++) {
			// i is also the size of the disk
			this.disks.add(SinglyLinkedNode.from(UUID.randomUUID(), i));
		}
	}

	private void initTowers() {
		this.towers = new ArrayList<>();
		for (int i = 0; i < N_TOWERS; i++) {
			this.towers.add(new Stack<>());
		}
	}

	private void initSetup() {
		// push the stacks to the first tower in ascending order
		Stack<SinglyLinkedNode<UUID, Integer>> firstTower = towers.get(0);
		for (int i = disks.size() - 1; i >= 0; i--) {
			firstTower.push(disks.get(i));
		}
	}

	public Stack<SinglyLinkedNode<UUID, Integer>> getLastTower() {
		return towers.get(towers.size() - 1);
	}

	public Stack<SinglyLinkedNode<UUID, Integer>> getFirstTower() {
		return towers.get(0);
	}

	public void solve() {
		moveDisks(disks.size(), 0, 2);
	}

	private void moveDisks(int nDisks, int towerFrom, int towerTo) {
		if (nDisks == 1) {
			// move disk
			towers.get(towerTo).push(towers.get(towerFrom).pop());
		} else {
			int otherTower = getOtherTower(towerFrom, towerTo);
			moveDisks(nDisks - 1, towerFrom, otherTower);
			moveDisks(1, towerFrom, towerTo);
			moveDisks(nDisks - 1, otherTower, towerTo);
		}
	}

	private int getOtherTower(Integer... towerIndices) {
		Set<Integer> excludingTowers = new HashSet<>(Arrays.asList(towerIndices));
		for (int i = 0; i < 3; i++) {
			if (!excludingTowers.contains(i)) {
				return i;
			}
		}
		throw new IllegalArgumentException("Could not infer the other tower.");
	}

}
