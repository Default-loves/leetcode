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

    public int shortestSubarray(int[] A, int K) {
        int result = Integer.MAX_VALUE;
        for (int i = 1; i < A.length; i++) {
            A[i] += A[i-1];
        }
        int[] queue = new int[A.length];
        int l = 0, r = -1;
        for (int i = 0; i < A.length; i++) {
            while (l <= r && A[queue[r]] > A[i]) {
                r--;
            }
            queue[++r] = i;
            while (A[queue[r]] - A[queue[l]] >= K) {
                result = Math.min(result, queue[r]-queue[l]);
                l++;
            }
            if (A[i] >= K) {
                result = Math.min(result, i+1);
            }
        }
        return result == Integer.MAX_VALUE? -1: result;
    }

    public static void main(String[] argv) {
        LeetCodeSolution lcs = new LeetCodeSolution();
        int[]  array = new int[]{1, 3, -1, 3};
        lcs.shortestSubarray(array, 5);
        System.out.println(1+2+"34");
    }
}




