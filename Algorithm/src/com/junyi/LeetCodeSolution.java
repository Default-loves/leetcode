package com.junyi;


import java.util.HashMap;
import java.util.Map;

public class LeetCodeSolution {






    public int numRabbits(int[] answers) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int answer : answers) {
            map.put(answer, map.getOrDefault(answer, 0) + 1);
        }

        int result = 0;
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




