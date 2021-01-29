package com.junyi;

import javafx.util.Pair;
import org.junit.platform.commons.util.StringUtils;

import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.incrementExact;
import static java.lang.Math.max;
import static java.lang.Math.min;



public class LeetCodeSolution {

    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int N = group.length;
        int THRESHOLD = 1_000_000_007;
        int[][] dp = new int[n+1][minProfit+1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < N; i++) {
            for (int j = n; j >= group[i]; j--) {
                for (int k = minProfit; k >= 0; k--) {
                    int t = Math.max(k - profit[i], 0);
                    dp[j][k] += dp[j-group[i]][t];
                    dp[j][k] %= THRESHOLD;
                }
            }
        }
        return dp[n][minProfit];
    }

    public static void main(String[] argv) {
        LeetCodeSolution lcs = new LeetCodeSolution();
        int[] array = new int[]{1, 1, 1, 1, 1};
        Integer res = lcs.findTargetSumWays(array, 3);
        System.out.println(res);
    }
}




