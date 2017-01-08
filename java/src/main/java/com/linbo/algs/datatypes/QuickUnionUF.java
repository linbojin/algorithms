package com.linbo.algs.datatypes;

/**
 * Created by @linbojin on 8/1/17.
 */
public class QuickUnionUF {
  private int[] parent;
  private int count;

  public QuickUnionUF(int n) {
    count = n;
    parent = new int[n];
    for (int i = 0; i < n; i++) {
      parent[i] = i;
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
    parent[rootP] = rootQ;
    count--;
  }


  public static void main(String args[]) {

    QuickUnionUF uf = new QuickUnionUF(10);
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
