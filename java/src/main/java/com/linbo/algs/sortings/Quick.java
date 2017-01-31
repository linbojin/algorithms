package com.linbo.algs.sortings;

import com.linbo.algs.util.StdRandom;

/**
 * Created by @linbojin on 21/1/17.
 */
public class Quick {

  private Quick() { }

  public static void sort(Comparable[] a) {
    // shuffle needed for performance guarantee
    StdRandom.shuffle(a);
    sort(a, 0, a.length - 1);
  }

  private static void sort(Comparable[] a, int lo, int hi) {
    if (hi <= lo) return;
    int j = partition(a, lo, hi);
    sort(a, lo, j-1);
    sort(a, j+1, hi);
  }

  // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
  // and return the index j.
  private static int partition(Comparable[] a, int lo, int hi) {
    int i = lo;
    int j = hi + 1;
    Comparable v = a[lo];

    while (true) {

      while (less(a[++i], v))
        if (i == hi) break;

      while (less(v, a[--j]))
        if (j == lo) break;

      // check if pointers cross
      if (i >= j) break;

      exch(a, i, j);
    }

    // put partitioning item v at a[j]
    exch(a, lo, j);

    // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
    return j;
  }

  private static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  private static void exch(Object[] a, int i, int j) {
    Object swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }

  public static void main(String args[]) {
    Integer[] arr = {10, 5, 1, 4, 5, 3, 2, 5, 3, 22};
    Quick.sort(arr);

    for (int i = 0; i < arr.length; i++) {
      System.out.println(arr[i]);
    }
  }

}
