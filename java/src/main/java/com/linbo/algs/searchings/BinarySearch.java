package com.linbo.algs.searchings;

import java.util.Arrays;

/**
 * Created by @linbojin on 1/2/17.
 *  This class provides a static method for binary
 *  searching for an integer in a sorted array of integers.
 */
public class BinarySearch {

  private BinarySearch() { }

  public static int indexOf(int[] a, int key) {
    int lo = 0;
    int hi = a.length - 1;
    while (lo <= hi) {
      int mid = (lo + hi) / 2;
      if (key < a[mid]) hi = mid - 1;
      else if (key > a[mid]) lo = mid + 1;
      else return mid;
    }
    return -1;
  }

  public static void main(String[] args) {

    int[] arr = {3, 23, 89, 12, 5};
    Arrays.sort(arr);
    // 3, 5, 12, 23, 89

    System.out.println(BinarySearch.indexOf(arr, 5)); // 1
    System.out.println(BinarySearch.indexOf(arr, 89)); // 4
    System.out.println(BinarySearch.indexOf(arr, 6)); // -1
  }

}
