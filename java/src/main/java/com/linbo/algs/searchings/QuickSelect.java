package com.linbo.algs.searchings;

import com.linbo.algs.util.StdRandom;

import java.util.Arrays;

/**
 * Created by @linbojin on 4/2/17.
 *  This class provides a static method to find the median (top kth)
 *  in a un-sorted array of integers.
 */
public class QuickSelect {

  private QuickSelect() { }

  public static Comparable search(Comparable[] a, int k) {
    StdRandom.shuffle(a);
    int lo = 0, hi = a.length - 1;
    while (lo < hi) {
      int j = partition(a, lo, hi);
      if (j < k) lo = j + 1;
      else if (j > k) hi = j - 1;
      else return a[k];
    }

    return a[k];

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


  public static void main(String[] args) {
    Integer[] arr = {8, 1, 7, 5, 3};
    int median = (Integer) QuickSelect.search(arr, arr.length / 2);
    System.out.println("median: " + median);
    System.out.print("top half:");
    for (int i = arr.length - 1; i >= arr.length / 2; i--) {
      System.out.printf("%2d", arr[i]);
    }
    System.out.println("\n******");

    Integer[] arr22 = {8, 1, 7, 5, 3, 2, 4, 11, 10, 13, 25, 76, 0, 9, 12, 15, 17, 18, 14, 100, 99, 66};
    int k = arr22.length - 5;
    int fifth = (Integer) QuickSelect.search(arr22, k);
    System.out.println("fifth: " + fifth);
    System.out.print("top 5s:");
    for (int i = arr22.length - 1; i >= k; i--) {
      System.out.printf("%5d", arr22[i]);
    }
    System.out.println("\n******");

    // if length n is even, median = (a[n/2-1] + a[n/2]) / 2
    Integer[] arr6 = {8, 1, 7, 5, 3, 9, 10, 11};
    int a = (Integer) QuickSelect.search(arr6, arr6.length / 2);
    Integer[] arr3 = new Integer[arr6.length / 2];

    for (int i = 0; i < arr3.length; i++) {
      arr3[i] = arr6[i];
    }
    int b = (Integer) QuickSelect.search(arr3, arr3.length - 1);
    System.out.println("median: " + (a + b) / 2.0);
  }

}
