package vn.khanhpdt.playgrounds.datastructures.unionfind;

public class QuickUnion extends UnionFind {
	/*
	Compared to QuickFind, QuickUnion has the same worst-case complexity of O(n), where n is the number of nodes.
	On average, its complexity depends on the average number of nodes in the same component.
	So if this number is much smaller than n, the performance of this algorithm is better than QuickFind.
	To make that number small, we need to distribute the nodes to components in a balanced way.
	This is the motivation behind WeightedQuickUnion.
	 */

    // node-based index. store the id of the other node in same component (i.e., the other connected node).
    // e.g., neighborIds[i] = some other node in same component with the ith node
	protected final int[] neighborIds;

	public QuickUnion(int n) {
	    neighborIds = new int[n];
		for (var i = 0; i < n; i++) {
		    // node connected to itself at the beginning
			neighborIds[i] = i;
		}
	}

	@Override
	public void union(int p, int q) {
		var pRoot = find(p);
		var qRoot = find(q);
        if (pRoot != qRoot) {
			neighborIds[pRoot] = qRoot;
		}
	}

	@Override
	public int find(int p) {
        var neighbor = neighborIds[p];
        // this will loop until we find the root of the component.
        // the union will guarantee that the root exists.
        while (neighborIds[neighbor] != neighbor) {
            // neighbor of neighbor
            neighbor = neighborIds[neighbor];
        }
        return neighbor;
	}

}
