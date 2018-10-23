package vn.khanhpdt.playgrounds.datastructures.nodes;

/**
 * @author khanhpdt
 */
public interface ForwardLinked<N extends ForwardLinked<N>> {

	N getNext();

	void setNext(N next);

}
