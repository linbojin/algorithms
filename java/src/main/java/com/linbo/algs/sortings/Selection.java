package com.linbo.algs.sortings;

import java.util.Comparator;

/**
 * Created by @linbojin on 15/1/17.
 * This class provides static methods for sorting an
 *  array using selection sort.
 */
public class Selection {

  // This class should not be instantiated.
  private Selection() { }

  public static void sort(Comparable[] a) {
    int n = a.length;
    for (int i = 0; i < n; i++) {
      int min = i;
      for (int j = i+1; j < n; j++) {
        if (less(a[j], a[min])) min = j;
      }
      exch(a, i, min);
    }
  }

  public static void sort(Comparable[] a, Comparator comparator) {
    int n = a.length;
    for (int i = 0; i < n; i++) {
      int min = i;
      for (int j = i+1; j < n; j++) {
        if (less(comparator, a[j], a[min])) min = j;
      }
      exch(a, i, min);
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

    Selection.sort(a);

    for (int i: a) {
      System.out.print(i + " ");
    }
  }

}
