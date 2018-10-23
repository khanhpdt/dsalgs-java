package vn.khanhpdt.playgrounds.datastructures.linkedlists;

import org.junit.Test;
import vn.khanhpdt.playgrounds.datastructures.nodes.SinglyLinkedNode;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * @author khanhpdt
 */
public class SinglyLinkedNodeTest {

	@Test
	public void testEqualNodesHaveSameHashcode() throws Exception {
		SinglyLinkedNode node1 = SinglyLinkedNode.fromKey(UUID.randomUUID());
		SinglyLinkedNode node2 = SinglyLinkedNode.fromKey(node1.getKey());

		assertThat("equal nodes", node1, equalTo(node2));
		assertThat("same hash code", node1.hashCode(), is(node2.hashCode()));
	}
}
