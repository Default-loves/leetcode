package com.junyi;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.incrementExact;
import static java.lang.Math.max;
import static java.lang.Math.min;



public class LeetCodeSolution {
    private static final int target = 24;
    private static final double E = 1e-6;

    public boolean judgePoint24(int[] nums) {
        ArrayList<Double> res = new ArrayList<>();
        Arrays.stream(nums).forEach(k -> res.add((double) k));
        return f(res);
    }

    private boolean f(ArrayList<Double> list) {
        if (list.size() == 1) {
            return Math.abs(list.get(0) - target) < E;
        }
        for (int i = 0; i < list.size(); i++) {
            for (int j = i+1; j < list.size(); j++) {
                ArrayList<Double> cList = new ArrayList<>();
                for (int k = 0; k < list.size(); k++) {
                     if (k == i || k ==j)
                         continue;
                     cList.add(list.get(k));
                }
                double a = list.get(i);
                double b = list.get(j);
                for (int k = 0; k < 6; k++) {
                    switch (k) {
                        case 0: cList.add(a + b);break;
                        case 1: cList.add(a * b);break;
                        case 2: cList.add(a - b);break;
                        case 3: cList.add(b - a);break;
                        case 4:
                            if (Math.abs(b-0) < E) {
                                continue;
                            }
                            cList.add(a / b);
                            break;
                        case 5:
                            if (Math.abs(a-0) < E) {
                                continue;
                            }
                            cList.add(b / a);
                            break;
                        default:
                    }
                    if (f(cList)) {
                        return true;
                    }
                    cList.remove(cList.size()-1);
                }
            }
        }
        return false;
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




