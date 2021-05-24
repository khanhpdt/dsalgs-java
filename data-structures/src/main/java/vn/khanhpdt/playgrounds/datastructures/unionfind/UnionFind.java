package vn.khanhpdt.playgrounds.datastructures.unionfind;

public abstract class UnionFind {

    /**
     * Union node p and q into the same component. i.e., connect p and q.
     */
    public abstract void union(int p, int q);

    /**
     * @return the component p is in
     */
    public abstract int find(int p);

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

}
