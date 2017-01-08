package com.linbo.algs.datatypes;

/**
 * Created by @linbojin on 8/1/17.
 */
public class WeightedQuickUnionUF {
  private int[] parent;
  private int[] size;     // size[i] = number of sites in subtree rooted at i
  private int count;

  public WeightedQuickUnionUF(int n) {
    count = n;
    parent = new int[n];
    size = new int[n];
    for (int i = 0; i < n; i++) {
      parent[i] = i;
      size[i] = 1;
    }
  }

  public int count() { return count; }

  // validate that p is a valid index
  private void validate(int p) {
    int n = parent.length;
    if (p < 0 || p >= n) {
      throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (n-1));
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

    if (size[rootP] < size[rootQ]) {
      parent[rootP] = rootQ;
      size[rootQ] += size[rootP];
    } else {
      parent[rootQ] = rootP;
      size[rootP] += size[rootQ];
    }

    count--;
  }


  public static void main(String args[]) {

    WeightedQuickUnionUF uf = new WeightedQuickUnionUF(10);
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
    uf.union(6,1);
    uf.union(1,0);
    System.out.println(uf.connected(0,7));
    System.out.println(uf.count() + " components");
  }
}
