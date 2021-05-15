package vn.khanhpdt.playgrounds.ctci.stacksandqueues;

import org.hamcrest.Matchers;
import org.junit.Test;
import vn.khanhpdt.playgrounds.ctci.stacksandqueues.TowersOfHanoi;
import vn.khanhpdt.playgrounds.datastructures.nodes.SinglyLinkedNode;
import vn.khanhpdt.playgrounds.datastructures.stacks.Stack;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author khanhpdt
 */
public class TowersOfHanoiTest {

	@Test
	public void test() {
		TowersOfHanoi towersOfHanoi = new TowersOfHanoi(20);

		towersOfHanoi.solve();

		assertThat("the first tower should now be empty", towersOfHanoi.getFirstTower().isEmpty(), is(true));

		// assert that the disks are in the correct order in the last tower
		Stack<SinglyLinkedNode<UUID, Integer>> lastTower = towersOfHanoi.getLastTower();
		assertThat("last tower should not be empty", lastTower.isEmpty(), is(false));

		SinglyLinkedNode<UUID, Integer> currentDisk = lastTower.pop();
		while (currentDisk != null) {
			SinglyLinkedNode<UUID, Integer> nextDisk = lastTower.pop();
			if (nextDisk != null) {
				assertThat("smaller disk is on top of larger disk", currentDisk.getValue(),
						Matchers.lessThanOrEqualTo(nextDisk.getValue()));
			}

			currentDisk = nextDisk;
		}
	}
}
