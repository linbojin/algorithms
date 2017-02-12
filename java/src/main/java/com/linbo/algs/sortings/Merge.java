package com.linbo.algs.sortings;

/**
 * Created by @linbojin on 16/1/17.
 *  This class provides static methods for sorting an
 *  array using mergesort.
 *  Ref: https://github.com/linbojin/algorithms/blob/master/doc/05-Mergesort.md#mergesort-1
 */
public class Merge {

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

  private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
    if (hi <= lo) return;
    int mid = (lo + hi) / 2;
    sort(a, aux, lo, mid);
    sort(a, aux, mid + 1, hi);
    merge(a, aux, lo, mid, hi);
  }

  public static void sort(Comparable[] a) {
    Comparable[] aux = new Comparable[a.length];
    sort(a, aux, 0, a.length - 1);
  }

  private static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  public static void main(String args[]) {
    Integer[] a = {6,11, 7, 12, 5, 4, 2,1,4};

    Merge.sort(a);

    for (int i: a) {
      System.out.print(i + " ");
    }
  }

}
