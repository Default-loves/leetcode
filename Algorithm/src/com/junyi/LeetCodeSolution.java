package com.junyi;


import org.junit.jupiter.api.Test;

import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class LeetCodeSolution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Node {
        public int x;
        public int y;
        public int depth;

        public Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.depth = d;
        }
    }

    public int maxLength(List<String> arr) {
        int n = arr.size();


        return 0;
    }

    public int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int l = 0, r = nums.length-1;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] < nums[mid+1]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }


    public int largestMagicSquare(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int maxSize = 1;      // 最大幻方的尺寸，也即是结果

        int[][] rowSum = new int[n][m+1];     // 行的前缀和
        int[][] columnSum = new int[n+1][m];     // 列的前缀和
        // 计算前缀和
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                rowSum[i][j+1] = rowSum[i][j] + grid[i][j];
                columnSum[i+1][j] = columnSum[i][j] + grid[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = maxSize + 1; i + k <= n && j + k <= m; k++) {    // 对于每个格子，不必每次都从尺寸1开始
                    if (isMagic(grid, rowSum, columnSum, i, j, k)) {
                        maxSize = k;
                    }
                }
            }
        }
        return maxSize;
    }

    private boolean isMagic(int[][] grid, int[][] rowSum, int[][] columnSum, int a, int b, int size) {
        int sum = 0;        // 左斜对角和
        int otherSum = 0;   // 右斜对角和
        for (int i = 0; i < size; i++) {
            sum += grid[a + i][b + i];
            otherSum += grid[a + i][b + size - 1 - i];
        }
        if (sum != otherSum) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (sum != rowSum[a + i][b + size] - rowSum[a + i][b]) {     // 判断每一行
                return false;
            }
            if (sum != columnSum[a + size][b + i] - columnSum[a][b + i]) {      // 判断每一列
                return false;
            }
        }
        return true;
    }

    public int minimumSize(int[] nums, int maxOperations) {
        int left = 0, right = 1_000_000_000;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (check(nums, mid, maxOperations)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


//
    public int triangleNumber(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int answer = 0;
        for (int i = n-1; i >= 2; i--) {
            int left = 0, right = i-1;
            while (left < right) {
                if (nums[left] + nums[right] > nums[i]) {
                    answer += right - left;
                    right--;
                } else {
                    left++;
                }
            }
        }
        return answer;
    }


    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        return noGreater(nums, right) - noGreater(nums, left - 1);
    }

    /** 计算连续子数组中最大值不大于k的子数组数量 */
    private int noGreater(int[] nums, int k) {
        int pre = 0;
        int answer = 0;
        for (int num : nums) {
            if (num <= k) {
                pre++;
            } else {
                pre = 0;
            }
            answer += pre;
        }
        return answer;
    }

    public int subarraysWithKDistinct(int[] nums, int k) {
        return solve(nums, k) - solve(nums, k - 1);
    }

    /**
     * 连续子数组不同整数个数不超过k个的子数组个数
     * @param fruits
     * @param k: 连续子数组不同整数的个数
     * @return
     */
    public int solve(int[] fruits, int k) {
        int preIndex = 0;   // 索引
        int answer = 0;     // 结果
        // Key: 树类型     Value：数量
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < fruits.length; i++) {
            int v = map.getOrDefault(fruits[i], 0);
            if (v == 0) {
                k--;
            }
            map.put(fruits[i], v + 1);
            while (k < 0) {
                map.put(fruits[preIndex], map.get(fruits[preIndex]) - 1);
                if (map.get(fruits[preIndex]) == 0) {
                    k++;
                }
                preIndex++;
            }
            answer += i - preIndex + 1;
        }
        return answer;
    }


    public int bulbSwitch(int n) {
        if(n == 1) {
            return 1;
        }

    }



    @Test
    public void test() {
        LeetCodeSolution lcs = new LeetCodeSolution();

        int[] array = {1000000000};
        int result = lcs.minimumSize(array, 1);
        System.out.println(result);
    }
}