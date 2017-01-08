package com.linbo.algs.datatypes;

/**
 * Created by @linbojin on 8/1/17.
 */
public class QuickFindUF {
  private int[] id;    // id[i] = component identifier of i
  private int count;   // number of components

  public QuickFindUF(int n) {
    count = n;
    id = new int[n];
    for (int i = 0; i < n; i++) {
      id[i] = i;
    }
  }

  public int count() { return count; }

  // validate that p is a valid index
  private void validate(int p) {
    int n = id.length;
    if (p < 0 || p >= n) {
      throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (n-1));
    }
  }

  public int find(int p) {
    validate(p);
    return id[p];
  }

  // 2 array accesses
  public boolean connected(int p, int q) {
    validate(p);
    validate(q);
    return id[p] == id[q];
  }

  /*
   * To merge components containing p and q, change all entries whose id equals id[p] to id[q].
   * at most 2N + 2 array accesses
   */
  public void union(int p, int q) {
    validate(p);
    validate(q);
    int pID = id[p];   // needed for correctness!
    int qID = id[q];   // to reduce the number of array accesses

    // p and q are already in the same component
    if (pID == qID) return;

    for (int i = 0; i < id.length; i++) {
      // change all entries with id[p] to id[q]
      if (id[i] == pID) id[i] = qID;
    }
    count--;
  }


  public static void main(String args[]) {

    QuickFindUF dc = new QuickFindUF(10);
    dc.union(4, 3);
    dc.union(3, 8);
    dc.union(6, 5);
    dc.union(9, 4);
    dc.union(2, 1);
    System.out.println(dc.connected(0, 7));
    System.out.println(dc.connected(8, 9));
    System.out.println(dc.count());

    dc.union(5,0);
    dc.union(7,2);
    dc.union(6,1);
    dc.union(6,1);
    dc.union(1,0);
    System.out.println(dc.connected(0,7));
    System.out.println(dc.count());
  }
}
