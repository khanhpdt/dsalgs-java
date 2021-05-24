package vn.khanhpdt.playgrounds.datastructures.unionfind;

public class QuickFind extends UnionFind {

    /**
     * Node-based index. i.e., componentIds[i] = the component id of the ith node
     */
    protected int[] componentIds;

    public QuickFind(int n) {
        // on initialization, nodes are not connected, so they are their own components.
        componentIds = new int[n];
        for (var i = 0; i < n; i++) {
            componentIds[i] = i;
        }
    }

    @Override
    public void union(int p, int q) {
        // complexity: O(n)
        for (var i = 0; i < componentIds.length; i++) {
            // unite p's component and q's component.
            // we can set both p's component or q's component to either p's component or q's component. here we set to p's.
            if (componentIds[i] == componentIds[q]) {
                componentIds[i] = componentIds[p];
            }
        }
    }

    @Override
    public int find(int p) {
        // this is why it's called quick-find
        return componentIds[p];
    }

}
