package vn.khanhpdt.playgrounds.datastructures.nodes;

/**
 * @author khanhpdt
 */
public interface DoublyLinked<N extends DoublyLinked<N>> extends ForwardLinked<N>, BackwardLinked<N> {
}
