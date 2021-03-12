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


    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());// 初始化空数组
        Arrays.sort(nums);
        int start = 1; //保存新解的开始位置
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> ans_tmp = new ArrayList<>();
            // 遍历之前的所有结果
            for (int j = 0; j < ans.size(); j++) {
                List<Integer> list = ans.get(j);
                //如果出现重复数字，就跳过所有旧解
                if (i > 0 && nums[i] == nums[i - 1] && j < start) {
                    continue;
                }
                List<Integer> tmp = new ArrayList<>(list);
                tmp.add(nums[i]); // 加入新增数字
                ans_tmp.add(tmp);
            }

            start = ans.size(); //更新新解的开始位置
            ans.addAll(ans_tmp);
        }
        return ans;
    }



    public static void main(String[] argv) {
        LeetCodeSolution lcs = new LeetCodeSolution();
        int[] array = new int[]{1,2,3,4,5,6,1};

        int result = lcs.maxScore(array, 3);
        System.out.println(result);
    }
}




