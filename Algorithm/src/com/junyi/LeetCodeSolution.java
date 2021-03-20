package com.junyi;

import javafx.util.Pair;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Copy;
import org.junit.platform.commons.util.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.*;


public class LeetCodeSolution {


    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for (int num : nums) {
            int length = result.size();
            for (int i = 0; i < length; i++) {
                ArrayList<Integer> item = new ArrayList<>(result.get(i));
                item.add(num);
                if (!result.contains(item)) {
                    result.add(item);
                }
            }
        }
        return result;
    }





    public static void main(String[] argv) {
        LeetCodeSolution lcs = new LeetCodeSolution();
        int[] array = new int[]{1,2,3,4,5,6,1};

//        int result = lcs.maxScore(array, 3);
//        System.out.println(result);
    }
}




