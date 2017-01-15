package com.linbo.algs.sortings;

import java.util.Comparator;

/**
 * Created by @linbojin on 15/1/17.
 *  This class provides static methods for sorting an
 *  array using insertion sort.
 *
 *  This implementation makes ~ 1/2 n^2 compares and exchanges in
 *  the worst case, so it is not suitable for sorting large arbitrary arrays.
 *  More precisely, the number of exchanges is exactly equal to the number
 *  of inversions. So, for example, it sorts a partially-sorted array
 *  in linear time.
 */
public class Insertion {

  public Insertion() { }

  public static void sort(Comparable[] a) {
    int n = a.length;
    for (int i = 0; i < n; i++) {
      for (int j = i; j > 0; j--) {
        if (less(a[j], a[j-1])) exch(a, j, j-1);
        else break;
      }
    }
  }

  public static void sort(Object[] a, Comparator comparator) {
    int n = a.length;
    for (int i = 0; i < n; i++) {
      for (int j = i; j > 0; j--) {
        if (less(comparator, a[j], a[j - 1])) exch(a, j, j - 1);
        else break;
      }
    }
  }

  private static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  private static boolean less(Comparator comparator, Object v, Object w) {
    return comparator.compare(v, w) < 0;
  }

  private static void exch(Object[] a, int i, int j) {
    Object swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }

  public static void main(String args[]) {
    Integer[] a = {6,2,1,4};

    Insertion.sort(a);

    for (int i: a) {
      System.out.print(i + " ");
    }

  }
}
