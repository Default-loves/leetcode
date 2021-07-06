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

    public void recoverTree(TreeNode root) {
        dfs(root);
        // 交换两个错误的节点
        int tmp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = tmp;
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


    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        int result = 0;
        for (int num : nums) {
            if (!set.contains(num - 1)) {
                int curNum = num + 1;
                int count = 1;
                while (set.contains(curNum)) {
                    curNum++;
                    count++;
                }
                result = Math.max(result, count);
            }
        }
        return result;
    }

    public int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int answer = 0;
        int cur = 0;
        for (int i = 0; i < nums.length; i++) {
            int tmp = nums[i] == 0 ? cur-- : cur++;
            if (map.containsKey(cur)) {
                answer = Math.max(answer, i - map.get(cur));
            } else {
                map.put(cur, i);
            }
        }
        return answer;
    }

    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int target = threshold * k;
        int answer = 0;
        int sum = 0;
        // 窗口大小为k
        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }
        if (sum >= target) {
            answer++;
        }
        for (int i = 0; i < arr.length - k; i++) {
            sum += arr[i + k] - arr[i];
            if (sum >= target) {
                answer++;
            }
        }
        return answer;
    }


    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (firstNode == null && preNode.val > root.val) firstNode = preNode;
        if (firstNode != null && preNode.val > root.val) secondNode = root;
        preNode = root;
        dfs(root.right);
    }

    public int totalHammingDistance(int[] nums) {
        int n = nums.length;
        int total = 0;
        for (int i = 0; i < 30; i++) {
            int cnt = 0;
            for (int num : nums) {
                cnt += (num >> i) & 1;
            }
            total += cnt * (n - cnt);
        }
        return total;
    }

    public int knightDialer(int n) {
        // 索引0的数据，表示数字4和数字6可以跳转到数字0
        int[][] path = {{4, 6}, {6, 8}, {7, 9}, {4, 8}, {0, 3, 9}, {}, {0, 1, 7}, {2, 6}, {1, 3}, {2, 4}};
        int MOD = 1_000_000_007;
        int[][] dp = new int[n][10];
        Arrays.fill(dp[0], 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k : path[j]) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][k]) % MOD;
                }
            }
        }
        int result = 0;
        for (int i = 0; i < 10; i++) {
            result = (result + dp[n - 1][i]) % MOD;
        }
        return result;
    }


    public int videoStitching(int[][] clips, int time) {
        int[] dp = new int[time + 1];     // dp[i] 表示对于时间点i，需要的片段最小数量
        Arrays.fill(dp, Integer.MAX_VALUE - 1);   // 需要注意的是，不能赋默认值为最大值
        dp[0] = 0;
        for (int i = 1; i <= time; i++) {
            for (int[] clip : clips) {
                if (clip[0] < i && i <= clip[1]) {
                    dp[i] = Math.min(dp[i], dp[clip[0]] + 1);
                }
            }
        }
        return dp[time] == Integer.MAX_VALUE - 1 ? -1 : dp[time];
    }

    public int minHeightShelves(int[][] books, int shelf_width) {
        int n = books.length;
        int[] dp = new int[n + 1];  // 放置第i本书，书架整体最小高度
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;    // 初始化
        for (int i = 1; i <= n; i++) {
            int width = 0;
            int height = 0;
            int j = i;
            while (j > 0) {
                width += books[j - 1][0];       // 当前层的宽度
                if (width > shelf_width) {      // 如果当前层的宽度大于书架的宽度，则结束
                    break;
                }
                height = Math.max(height, books[j - 1][1]);     // 当前层的最大高度
                dp[i] = Math.min(dp[i], height + dp[j - 1]);
                j--;
            }
        }
        return dp[n];
    }


    private int answer;

    public int countPairs(TreeNode root, int distance) {
        dfs(root, distance);
        return answer;
    }

    private List<Integer> dfs(TreeNode root, int distance) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        if (root.left == null && root.right == null) {
            list.add(0);
            return list;
        }
        List<Integer> left = dfs(root.left, distance);
        left = left.stream().map(t -> {
            int newValue = t + 1;
            if (newValue < distance) {
                list.add(newValue);
            }
            return newValue;
        }).collect(Collectors.toList());
        List<Integer> right = dfs(root.right, distance);
        right = right.stream().map(t -> {
            int newValue = t + 1;
            if (newValue < distance) {
                list.add(newValue);
            }
            return newValue;
        }).collect(Collectors.toList());
        if (!left.isEmpty() && !right.isEmpty()) {
            for (Integer l : left) {
                for (Integer r : right) {
                    answer += (l + r) <= distance ? 1 : 0;
                }
            }
        }
        return list;
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
        int result = lcs.knightDialer(3131);
        System.out.println(result);
    }
}




