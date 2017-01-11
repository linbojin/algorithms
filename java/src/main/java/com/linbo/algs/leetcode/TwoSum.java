package com.linbo.algs.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by @linbojin on 11/1/17.
 * Question: https://leetcode.com/problems/two-sum/
 */
public class TwoSum {

  // O(n) runtime, O(n) space
  public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    for (int i = 0; i < nums.length; i++) {
      int key = nums[i];
      int pairKey = target - key;
      if (map.containsKey(pairKey)) {
        return new int[] { map.get(pairKey), i};
      }
      // update at the end to handle cases key == pairKey
      map.put(key, i);
    }
    throw new IllegalArgumentException("No two sum solution");
  }

  public static void main(String args[]) {
    int[] nums = {3, 2, 4};
    int target = 6;

    TwoSum ts = new TwoSum();
    int[] result = ts.twoSum(nums, target);
    System.out.print(result[0] + " " + result[1]);
  }
}
