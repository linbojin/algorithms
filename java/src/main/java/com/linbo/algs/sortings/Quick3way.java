package com.linbo.algs.sortings;

import com.linbo.algs.util.StdRandom;

/**
 * Created by @linbojin on 31/1/17.
 *  This class provides static methods for sorting an
 *  array using quicksort with 3-way partitioning.
 *  Ref: https://github.com/linbojin/algorithms/blob/master/doc/06-Quicksort.md#3-way-partitioning
 */
public class Quick3way {

  // This class should not be instantiated.
  private Quick3way() { }

  public static void sort(Comparable[] a) {
    StdRandom.shuffle(a);
    sort(a, 0, a.length - 1);
  }

  // quicksort the subarray a[lo .. hi] using 3-way partitioning
  private static void sort(Comparable[] a, int lo, int hi) {
    if (hi <= lo) return;
    int lt = lo, gt = hi;
    Comparable v = a[lo];
    int i = lo;
    while (i <= gt) {
      int cmp = a[i].compareTo(v);
      if      (cmp < 0) exch(a, lt++, i++);
      else if (cmp > 0) exch(a, i, gt--);
      else              i++;
    }

    // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
    sort(a, lo, lt-1);
    sort(a, gt+1, hi);
  }

  // exchange a[i] and a[j]
  private static void exch(Object[] a, int i, int j) {
    Object swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }

  public static void main(String args[]) {
    Integer[] arr = {10, 5, 1, 4, 5, 3, 2, 5, 3, 22};
    Quick3way.sort(arr);

    for (int i = 0; i < arr.length; i++) {
      System.out.println(arr[i]);
    }
  }

}

