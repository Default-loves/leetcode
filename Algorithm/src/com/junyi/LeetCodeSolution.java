package com.junyi;

import javafx.util.Pair;
import org.junit.platform.commons.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.incrementExact;
import static java.lang.Math.max;
import static java.lang.Math.min;



public class LeetCodeSolution {

    public int findTargetSumWays(int[] nums, int S) {
        int sum = Arrays.stream(nums).sum();
        if (Math.abs(S) > Math.abs(sum)) {
            return 0;
        }
        int t = sum*2+1;    // dp的列数，包括了负数、0、正数
        int[][] dp = new int[nums.length+1][t];
        dp[0][sum] = 1;     // 初始化
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j < t; j++) {
                int part1 = j - sum - nums[i-1] >= 0? dp[i-1][j-sum-nums[i-1]]: 0;
                int part2 = j - sum + nums[i-1] >= 0? dp[i-1][j-sum+nums[i-1]]: 0;
                dp[i][j] = part1 + part2;
            }
        }
        return dp[nums.length][S];
    }


    public static void main(String[] argv) {
        LeetCodeSolution lcs = new LeetCodeSolution();
        String[] arr = new String[]{"What","must","be","acknowledgment","shall","be"};
        List<Integer> res = lcs.splitIntoFibonacci("123456579");
        System.out.println(res);
    }
}




