package com.linbo.algs.examples;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by @linbojin on 13/1/17.
 */
public class Permutation {

  public static void main(String[] args) {
    int k = Integer.parseInt(args[0]);

    if (k > 0) {
      int count = 1;
      RandomizedQueue<String> queue = new RandomizedQueue<String>();
      while (!StdIn.isEmpty()) {
        String item = StdIn.readString();

        if (count <= k) {
          queue.enqueue(item);
        } else {
          int j = StdRandom.uniform(0, count);
          if (j < k) {
            queue.dequeue();
            queue.enqueue(item);
          }
        }
        count++;
      }

      for (int i = 0; i < k; i++) {
        StdOut.println(queue.dequeue());
      }
    }
  }
}
