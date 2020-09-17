package com.junyi;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.incrementExact;
import static java.lang.Math.max;
import static java.lang.Math.min;



public class LeetCodeSolution {
    private static final int INT = 1000000007;
    public int kInversePairs(int n, int k) {
        int[][] dp = new int[n+1][k+1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k && j <= i * (i-1) / 2; j++) {
                if (j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                int tmp = (dp[i-1][j] + INT - (j >= i? dp[i-1][j-i]: 0)) % INT;
                dp[i][j] = (tmp +dp[i][j-1]) % INT;
            }
        }
        return dp[n][k];
    }


    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    public static void main(String[] argv) {
        LeetCodeSolution lcs = new LeetCodeSolution();
        int[] arr = new int[]{1,7,5,2,4,3,9};
        int res = lcs.kInversePairs(10, 10);
        System.out.println(-20 % 3);
//        System.out.println(res);
    }
    }




