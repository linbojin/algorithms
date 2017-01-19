package com.linbo.algs.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by @linbojin on 19/1/17.
 * Question: https://leetcode.com/problems/two-sum-iii-data-structure-design/
 */
public class TwoSumDataStructureDesign {

  // add O(1), find O(N), space O(N)
  private Map<Integer, Integer> table = new HashMap<Integer, Integer>();

  /** Initialize your data structure here. */
  public TwoSumDataStructureDesign() {
  }

  /** Add the number to an internal data structure.. */
  public void add(int input) {
    int count = table.containsKey(input) ? table.get(input) : 0;
    table.put(input, count + 1);
  }

  /** Find if there exists any pair of numbers which sum is equal to the value. */
  public boolean find(int val) {
    for (Map.Entry<Integer, Integer> entry : table.entrySet()) {
      int num = entry.getKey();
      int y = val - num;
      if (y == num) {
        // For duplicates, ensure there are at least two individual numbers.
        if (entry.getValue() >= 2) return true;
      } else if (table.containsKey(y)) {
        return true;
      }
    }
    return false;
  }

  public static void main(String args[]) {
    TwoSumDataStructureDesign ts = new TwoSumDataStructureDesign();
    ts.add(1);
    ts.add(2);
    ts.add(3);
    ts.add(4);
    ts.add(4);

    System.out.println(ts.find(6));
    System.out.println(ts.find(8));
    System.out.println(ts.find(9));
  }
}
