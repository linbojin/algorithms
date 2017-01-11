package com.linbo.algs.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by @linbojin on 11/1/17.
 * Question: https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 */
public class TwoSumInOrder {

  // O(n log n) runtime, O(1) space
  // For each element x, we could look up if target – x exists in O(log n) time by applying
  // binary search over the sorted array. Total runtime complexity is O(n log n).
  public int[] twoSum(int[] nums, int target) {
    // Assume input is already sorted.
    for (int i = 0; i < nums.length; i++) {
      int j = bsearch(nums, target - nums[i], i + 1);
      if (j != -1) return new int[] {i + 1, j + 1};
    }
    throw new IllegalArgumentException("No two sum solution");
  }

  private int bsearch(int[] nums, int key, int start) {
    int left = start, right = nums.length - 1;
    while (left < right) {
      int middle = (left + right) / 2;
      if (nums[middle] < key) left = middle + 1;
      else if (nums[middle] > key) right = middle - 1;
      else return middle;
    }
    // need to check left == right, left may be equal to nums.length
    return (left == right && nums[left] == key) ? left : -1;
  }

  // O(n) runtime, O(1) space
  public int[] twoSum2(int[] nums, int target) {
    // Assume input is already sorted.
    int i = 0, j = nums.length - 1;
    while (i < j) {
      int sum = nums[i] + nums[j];
      if (sum < target) i++;
      else if (sum > target) j--;
      else return new int[] {i + 1, j + 1};
    }
    throw new IllegalArgumentException("No two sum solution");
  }

  public static void main(String args[]) {
    int[] nums = {2, 3, 4};
    int target = 6;

    TwoSumInOrder ts = new TwoSumInOrder();
    int[] result = ts.twoSum2(nums, target);
    System.out.print(result[0] + " " + result[1]);
  }
}

