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

    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m+1][n+1];
        for (String str : strs) {
            int[] result = cal(str);
            int zeroCount = result[0];
            int oneCount = result[1];
            for (int j = m; j >= zeroCount; j--) {
                for (int k = n; k >= oneCount; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j-zeroCount][k-oneCount] + 1);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 计算字符串的0和1数量
     * @param str
     * @return 数组索引0对应0的数量，索引1对应1的数量
     */
    private int[] cal(String str) {
        int[] result = new int[2];
        for (char c : str.toCharArray()) {
            result[c-'0']++;
        }
        return result;
    }


    public static void main(String[] argv) {
        LeetCodeSolution lcs = new LeetCodeSolution();
        String[] arr = new String[]{"What","must","be","acknowledgment","shall","be"};
        List<Integer> res = lcs.splitIntoFibonacci("123456579");
        System.out.println(res);·
    }
}




