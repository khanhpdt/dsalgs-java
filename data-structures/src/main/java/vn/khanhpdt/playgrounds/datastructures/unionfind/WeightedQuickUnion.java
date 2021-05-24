package vn.khanhpdt.playgrounds.datastructures.unionfind;

public class WeightedQuickUnion extends QuickUnion {

    private final int[] componentSizes;

    public WeightedQuickUnion(int n) {
        super(n);

        componentSizes = new int[n];
        for (int i = 0; i < n; i++) {
            componentSizes[i] = 1;
        }
    }

    @Override
    public void union(int p, int q) {
        var pRoot = find(p);
        var qRoot = find(q);

        if (pRoot == qRoot) return;

        var smallerRoot = componentSizes[pRoot] <= componentSizes[qRoot] ? pRoot : qRoot;
        var largerRoot = componentSizes[pRoot] <= componentSizes[qRoot] ? qRoot : pRoot;

        // make smaller component point to larger component. reason: we want the tree of the connected nodes
        // as balanced as possible, b/c that will help the find() operation. See Proposition G, Algorithms 4th edition.
        neighborIds[smallerRoot] = largerRoot;

        componentSizes[largerRoot] += componentSizes[smallerRoot];
    }

}
