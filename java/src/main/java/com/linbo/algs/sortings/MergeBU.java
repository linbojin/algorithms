package com.linbo.algs.sortings;

/**
 * Created by @linbojin on 16/1/17.
 *  This class provides static methods for sorting an
 *  array using bottom-up mergesort.
 */
public class MergeBU {

  // This class should not be instantiated
  private MergeBU() { }

  private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
    for (int k = lo; k <= hi; k++) {
      aux[k] = a[k];
    }

    int i = lo, j = mid + 1;
    for (int k = lo; k <= hi; k++) {
      if (i > mid) a[k] = aux[j++];
      else if (j > hi) a[k] = aux[i++];
      else if (less(aux[j], aux[i])) a[k] = aux[j++];
      else a[k] = aux[i++];
    }
  }

  public static void sort(Comparable[] a) {
    int n = a.length;
    Comparable[] aux = new Comparable[n];
    for (int len = 1; len < n; len *= 2) {
      for (int lo = 0; lo < n-len; lo += 2 * len ) {
        int mid = lo + len - 1;
        int hi = Math.min(lo+len*2-1, n-1);
        merge(a, aux, lo, mid, hi);
      }
    }
  }

  private static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  public static void main(String args[]) {
    Integer[] a = {6,11, 7, 12, 5, 4, 2,1,4};

    MergeBU.sort(a);

    for (int i: a) {
      System.out.print(i + " ");
    }
  }

}
