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


    public int minSetSize(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        ArrayList<Integer> list = new ArrayList<>(map.values());
        Collections.sort(list, (a, b) ->  (b - a));     // 降序排序

        int result = 0;
        int cnt = 0;
        int halfLen = arr.length >> 1;
        for (int i = 0; i < list.size(); i++) {
            cnt += list.get(i);
            result++;
            if (cnt > halfLen) {
                return result;
            }
        }
        return halfLen;
    }

    public static void main(String[] argv) {
        LeetCodeSolution lcs = new LeetCodeSolution();
        int[] array = new int[]{1,2,3,4,5,6,1};

        int result = lcs.maxScore(array, 3);
        System.out.println(result);
    }
}




