package vn.khanhpdt.playgrounds.datastructures.nodes;

/**
 * @param <N> type of the nodes linked with this node
 * @author khanhpdt
 */
public interface BackwardLinked<N extends BackwardLinked<N>> {

	N getPrevious();

	void setPrevious(N previous);

}
