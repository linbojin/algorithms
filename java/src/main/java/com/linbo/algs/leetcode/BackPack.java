package com.linbo.algs.leetcode;

import java.util.Stack;

/**
 * Created by @linbojin on 11/2/17.
 *  classic back pack problems
 */
public class BackPack {

  public static void solution(int[] w, int t) {
    int n = w.length;
    Stack<Integer> s = new Stack<Integer>();

    int i = 0;
    while (t > 0 && i <= n-1) {
      if ((t - w[i]) == 0 || (t-w[i] > 0 && i < n-1)) {
        s.push(i);
        t -= w[i];
      }
      if (t == 0) {
        System.out.println("indexes: " + s);
        System.out.print("values:  [");
        for (int m: s) {
          System.out.print(w[m] + "  ");
        }
        System.out.println("]");
        return;
      } else {
        if (i == n-1 && !s.isEmpty()) {
          i = s.pop();
          t += w[i];
        }
        i++;
      }
    }
    System.out.println("no solution!");
  }

  public static void main(String[] args) {
    int[] w = {4, 7, 3, 5, 4, 2};
    int t = 10;

    BackPack.solution(w, t);
  }
}
