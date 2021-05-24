package vn.khanhpdt.playgrounds.datastructures.linkedlists;

import vn.khanhpdt.playgrounds.datastructures.nodes.ForwardLinked;

/**
 * @param <N>  type of node
 */
public interface LinkedList<N extends ForwardLinked<N>> {

	N getHead();

	void setHead(N head);
}
