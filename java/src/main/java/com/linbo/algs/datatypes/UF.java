package com.linbo.algs.datatypes;

/**
 * Created by @linbojin on 8/1/17.
 * This implementation uses weighted quick union by rank with path compression
 */
public class UF {
  private int[] parent;
  private int[] rank;     // rank[i] = rank of subtree rooted at i (never more than 31)
  private int count;

  public UF(int n) {
    count = n;
    parent = new int[n];
    rank = new int[n];
    for (int i = 0; i < n; i++) {
      parent[i] = i;
      rank[i] = 0;
    }
  }

  /*
   * Returns the component identifier for the component containing site p
   * Same as the root of p
   */
  private int find(int p) {
    validate(p);
    // chase parent pointers until reach root
    // depth of i array accesses
    while(p != parent[p]) {
      parent[p] = parent[parent[p]];    // path compression by halving
      p = parent[p];
    }
    return p;
  }

  // depth of p and q array accesses
  public boolean connected(int p, int q) {
    return find(p) == find(q);
  }

  // change root of p to point to root of q
  // depth of p and q array accesses
  public void union(int p, int q) {
    int rootP = find(p);
    int rootQ = find(q);
    if (rootP == rootQ) return;

    // make root of smaller rank point to root of larger rank
    if      (rank[rootP] < rank[rootQ]) parent[rootP] = rootQ;
    else if (rank[rootP] > rank[rootQ]) parent[rootQ] = rootP;
    else {
      parent[rootQ] = rootP;
      rank[rootP]++;
    }
    count--;
  }

  public int count() { return count; }

  // validate that p is a valid index
  private void validate(int p) {
    int n = parent.length;
    if (p < 0 || p >= n) {
      throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (n-1));
    }
  }


  public static void main(String args[]) {

    UF uf = new UF(10);
    uf.union(4, 3);
    uf.union(3, 8);
    uf.union(6, 5);
    uf.union(9, 4);
    uf.union(2, 1);
    System.out.println(uf.connected(0, 7));
    System.out.println(uf.connected(8, 9));
    System.out.println(uf.count() + " components");

    uf.union(5,0);
    uf.union(7,2);
    uf.union(6,1);
    uf.union(1,0);
    System.out.println(uf.connected(0,7));
    System.out.println(uf.count() + " components");
  }
}

