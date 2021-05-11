package com.junyi;


import java.util.*;

public class LeetCodeSolution {



    public int maxWidthRamp(int[] A) {
        ArrayList<Integer> list = new ArrayList<>();
        // 构建以A[0]为头的，递减序列
        for (int i = 0; i < A.length; i++) {
            if (list.isEmpty() || A[list.get(list.size()-1)] > A[i]) {
                list.add(i);
            }
        }
        int result = 0;     // 最终结果
        for (int i = A.length-1; i > 0; i--) {
            int k = binarySearch(A, i, list);
            result = Math.max(result, i-k);
        }
        return result;
    }
    /** 返回小于A[k]的最小索引 */
    private int binarySearch(int[] A, int k, ArrayList<Integer> list) {
        int left = 0;
        int right = list.size()-1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (A[list.get(mid)] > A[k]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return list.get(left);
    }




    public int numRabbits(int[] answers) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int answer : answers) {
            map.put(answer, map.getOrDefault(answer, 0) + 1);
        }

        int result = 0;
        // 从发
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer number = entry.getKey();
            Integer count = entry.getValue();
            // 下面这两种方式都是可以的
            result += Math.ceil(count.doubleValue() / (number + 1)) * (number + 1);
            result += (number + count) / (number + 1) * (number + 1);
        }
        return result;

    }

    public static void main(String[] argv) {
        LeetCodeSolution lcs = new LeetCodeSolution();
        int[] array = new int[]{0,0,1,1,1};

        lcs.numRabbits(array);
    }
}




