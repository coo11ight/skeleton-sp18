public class UnionFind {

    // TODO - Add instance variables?
    private int[] parent;
    private int[] setSize;
    private int length;


    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        parent = new int[n];
        setSize = new int[n];
        length = n;
        for (int i = 0; i < n; i++) {
            parent[i] = - 1 ;

            setSize[i] = 1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        // TODO
        if (vertex >= length || vertex < 0) throw new RuntimeException("not valid vertex");
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        int p = find(v1);
        return setSize[p];

    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        if (parent[v1] == -1) return sizeOf(v1) * (-1) ;
        return parent[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
      // validate(v1);
        // validate(v2);
       if (parent(v1) == -1){
           return find(v2) == v1;
       }
       if (parent(v2) == -1){
           return find(v1) == v2;
       }
       return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a
       vertex with itself or vertices that are already connected should not
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        if (find(v1) == find(v2)) return;
        int s1 = sizeOf(v1);
        int s2 = sizeOf(v2);
        if (s1 > s2){
            parent[find(v2)] = find(v1);
            setSize[find(v1)] += 1;
        }
        else{
            parent[find(v1)] = find(v2);
            setSize[find(v2)] += 1;
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        if(parent[vertex] == -1) return vertex ;
        int p = vertex;
        while (parent[p] != -1){
            p = parent[p];
        }
        return p;
    }

    public static void main(String args[]){
        UnionFind u1 = new UnionFind(10);
        u1.union(0,2);
        u1.union(3,1);
        u1.union(1,0);
        System.out.println(u1.connected(1,4));
        System.out.println(u1.find(2));
        System.out.println(u1.parent(3));
        System.out.println(u1.parent(0));


    }

}
