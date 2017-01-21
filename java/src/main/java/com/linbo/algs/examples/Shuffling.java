package com.linbo.algs.examples;

import com.linbo.algs.util.StdRandom;

/**
 * Created by @linbojin on 21/1/17.
 */
public class Shuffling {

  public static void shuffle(int[] a) {
    int n = a.length;
    for (int i = 0; i < n; i++) {
      // int r = StdRandom.uniform(i + 1);       // between 0 and i
      int r = i + StdRandom.uniform(n-i);     // between i and n-1
      int temp = a[i];
      a[i] = a[r];
      a[r] = temp;
    }
  }

  public static void main(String args[]) {
    int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    Shuffling.shuffle(a);

    for (int i = 0; i < a.length; i++) {
      System.out.print(a[i] + " ");
    }

  }
}
