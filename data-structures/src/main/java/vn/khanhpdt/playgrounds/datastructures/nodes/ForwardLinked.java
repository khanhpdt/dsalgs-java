package vn.khanhpdt.playgrounds.datastructures.nodes;

public interface ForwardLinked<N extends ForwardLinked<N>> {

	N getNext();

	void setNext(N next);

}
