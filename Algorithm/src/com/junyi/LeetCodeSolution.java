package com.junyi;


import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.min;

public class LeetCodeSolution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Node {
        public String val;
        public List<Node> children;
        public boolean alive;

        public Node() {
        }

        public Node(String _val) {
            val = _val;
        }

        public Node(String _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    private TreeNode preNode = new TreeNode(Integer.MIN_VALUE);     // 前一个节点
    private TreeNode firstNode;     // 第一个错误的节点
    private TreeNode secondNode;    // 第二个错误的节点


    public int[] findRightInterval(int[][] intervals) {

    }


    public int minimumEffortPath(int[][] heights) {
        int answer = Integer.MAX_VALUE;

        return answer;
    }

    public int hIndex(int[] citations) {
        int left = 0, right = citations.length;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (check(citations, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    public int orderOfLargestPlusSign(int n, int[][] mines) {
    }

    private int min4(int a, int b, int c, int d) {
        return Math.min(Math.min(a, b), Math.min(c, d));
    }


    public int flipgame(int[] fronts, int[] backs) {
        int n = fronts.length;
        HashSet<Integer> set = new HashSet<>();     // 正面和背面相同的数字
        for (int i = 0; i < n; i++) {
            if (fronts[i] == backs[i]) {
                set.add(fronts[i]);
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int front : fronts) {
            if (!set.contains(front)) {
                answer = Math.min(answer, front);
            }
        }
        for (int back : backs) {
            if (!set.contains(back)) {
                answer = Math.min(answer, back);
            }
        }
        return answer == Integer.MAX_VALUE? 0: answer;
    }

    private boolean check(int[] data, int mid) {
        long count = Arrays.stream(data).filter(t -> t >= mid).count();
        return count >= mid;
    }

    public int countPairs(int[] deliciousness) {
        int maxValue = Arrays.stream(deliciousness).max().getAsInt();
        int maxSum = maxValue * 2;  // 两个餐品美味程度的最大值
        int answer = 0;     // 最终的结果
        int MOD = 1_000_000_007;
        // Key: 美味程度， Value：数量
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int item : deliciousness) {
            for (int i = 1; i < maxSum; i <<= 1) {
                int count = map.getOrDefault(i - item, 0);
                answer = (answer + count) % MOD;
            }
            map.put(item, map.getOrDefault(item, 0) + 1);
        }
        return answer;
    }



    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        ArrayList<Integer> answerList = new ArrayList<>();
        int n = favoriteCompanies.size();
        Set<String>[] set = new Set[n];
        for (int i = 0; i < n; i++) {
            set[i] = new HashSet<>(favoriteCompanies.get(i));
        }

        for (int i = 0; i < n; i++) {
            int j = 0;
            while (j < n) {
                 if (i == j) {
                     j++;
                     continue;
                 }
                 if (set[j].containsAll(favoriteCompanies.get(i))) {
                     break;
                 }
                 j++;
            }
            if (j == n) {
                answerList.add(i);
            }
        }
        return answerList;
    }



    public static void main(String[] argv) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list = (ArrayList<Integer>) list.stream().map(t -> t + 1).collect(Collectors.toList());


        System.out.println(list.toString());

    }

    @Test
    public void test() {
        LeetCodeSolution lcs = new LeetCodeSolution();
        TreeNode root = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        two.left = new TreeNode(4);
        two.right = new TreeNode(5);
        TreeNode three = new TreeNode(3);
        three.left = new TreeNode(6);
        three.right = new TreeNode(7);
        root.left = two;
        root.right = three;
        int[][] array = {{1, 1}, {2, 3}, {2, 3}, {1, 1}, {1, 1}, {1, 1}, {1, 2}};
//        int result = lcs.knightDialer(3131);
//        System.out.println(result);
    }
}




