package com.linbo.algs.examples;

/**
 * Created by @linbojin on 21/1/17.
 *  This class provides a client for reading in a sequence of strings
 *  and shuffling them using the Knuth (or Fisher-Yates) shuffling algorithm.
 *  This algorithm guarantees to rearrange the elements in
 *  uniformly random order, under
 *  the assumption that Math.random() generates independent and
 *  uniformly distributed numbers between 0 and 1.
 */
public class KnuthShuffle {

  // this class should not be instantiated
  private KnuthShuffle() { }

  public static void shuffle(int[] a) {
    int n = a.length;
    for (int i = 0; i < n; i++) {
      // int r = (int) (Math.random() * (i + 1));      // between 0 and i
      int r = i + (int) (Math.random() * (n - i));     // choose index uniformly in [i, n-1]
      int temp = a[i];
      a[i] = a[r];
      a[r] = temp;
    }
  }

  public static void main(String args[]) {
    int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    KnuthShuffle.shuffle(a);

    for (int i = 0; i < a.length; i++) {
      System.out.print(a[i] + " ");
    }
  }
}
