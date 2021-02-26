package com.junyi;

import javafx.util.Pair;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Copy;
import org.junit.platform.commons.util.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.*;


public class LeetCodeSolution {


    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1,-1};
        if (nums.length == 0) return res;
        //计算第一个位置
        int low = 0;
        int high = nums.length-1;
        while (low < high) {
            int mid = low + ((high-low) >> 1);
            if (nums[mid] >= target) {
                res[0] = mid;
                high = mid-1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            }
        }
        //计算最后一个位置
        low = 0;
        high = nums.length-1;
        while ( low < high ) {
            int mid = low + ((high -low) >> 1) + 1;
            if (nums[mid] <= target) {
                res[1] = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return res;
    }

    public static void main(String[] argv) {
        LeetCodeSolution lcs = new LeetCodeSolution();
        char[][]  array = {{'1','0','1','0'},
                {'1','0','1','1'},
                {'1','0','1','1'},
                {'1','1','1','1'}};

//        int result = lcs.maximalSquare(array);
//        System.out.println(result);
    }
}




