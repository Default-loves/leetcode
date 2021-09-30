package com.junyi;


import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.*;

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
        public int x;
        public int y;
        public int depth;

        public Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.depth = d;
        }
    }


    public int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int l = 0, r = nums.length-1;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] < nums[mid+1]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }


    public int largestMagicSquare(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int maxSize = 1;      // 最大幻方的尺寸，也即是结果

        int[][] rowSum = new int[n][m+1];     // 行的前缀和
        int[][] columnSum = new int[n+1][m];     // 列的前缀和
        // 计算前缀和
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                rowSum[i][j+1] = rowSum[i][j] + grid[i][j];
                columnSum[i+1][j] = columnSum[i][j] + grid[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = maxSize + 1; i + k <= n && j + k <= m; k++) {    // 对于每个格子，不必每次都从尺寸1开始
                    if (isMagic(grid, rowSum, columnSum, i, j, k)) {
                        maxSize = k;
                    }
                }
            }
        }
        return maxSize;
    }

    private boolean isMagic(int[][] grid, int[][] rowSum, int[][] columnSum, int a, int b, int size) {
        int sum = 0;        // 左斜对角和
        int otherSum = 0;   // 右斜对角和
        for (int i = 0; i < size; i++) {
            sum += grid[a + i][b + i];
            otherSum += grid[a + i][b + size - 1 - i];
        }
        if (sum != otherSum) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (sum != rowSum[a + i][b + size] - rowSum[a + i][b]) {     // 判断每一行
                return false;
            }
            if (sum != columnSum[a + size][b + i] - columnSum[a][b + i]) {      // 判断每一列
                return false;
            }
        }
        return true;
    }



//
    public int triangleNumber(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int answer = 0;
        for (int i = n-1; i >= 2; i--) {
            int left = 0, right = i-1;
            while (left < right) {
                if (nums[left] + nums[right] > nums[i]) {
                    answer += right - left;
                    right--;
                } else {
                    left++;
                }
            }
        }
        return answer;
    }


    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        return noGreater(nums, right) - noGreater(nums, left - 1);
    }

    /** 计算连续子数组中最大值不大于k的子数组数量 */
    private int noGreater(int[] nums, int k) {
        int pre = 0;
        int answer = 0;
        for (int num : nums) {
            if (num <= k) {
                pre++;
            } else {
                pre = 0;
            }
            answer += pre;
        }
        return answer;
    }

    public int subarraysWithKDistinct(int[] nums, int k) {
        return solve(nums, k) - solve(nums, k - 1);
    }

    /**
     * 连续子数组不同整数个数不超过k个的子数组个数
     * @param fruits
     * @param k: 连续子数组不同整数的个数
     * @return
     */
    public int solve(int[] fruits, int k) {
        int preIndex = 0;   // 索引
        int answer = 0;     // 结果
        // Key: 树类型     Value：数量
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < fruits.length; i++) {
            int v = map.getOrDefault(fruits[i], 0);
            if (v == 0) {
                k--;
            }
            map.put(fruits[i], v + 1);
            while (k < 0) {
                map.put(fruits[preIndex], map.get(fruits[preIndex]) - 1);
                if (map.get(fruits[preIndex]) == 0) {
                    k++;
                }
                preIndex++;
            }
            answer += i - preIndex + 1;
        }
        return answer;
    }



    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });

        int n = pairs.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = i-1; j >= 0; j--) {
                if (pairs[j][1] < pairs[i][0]) {
                    dp[i] = dp[j] + 1;
                    break;
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });

        int count = 1;
        int cur = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= cur) {
                cur = intervals[i][1];
                count++;
            }
        }
        return intervals.length - count;
    }


    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder);
        int n = folder.length;
        ArrayList<String> answer = new ArrayList<>();
        answer.add(folder[0]);
        for (int i = 1; i < n; i++) {
            String last = answer.get(answer.size() - 1);
            if (!folder[i].startsWith(last)) {
                answer.add(folder[i]);
            }
        }
        return answer;
    }

    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) {
            return false;
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int item : hand) {
            map.put(item, map.getOrDefault(item, 0) + 1);
        }
        while (!map.isEmpty()) {
            Integer start = map.firstKey();
            for (int i = 0; i < groupSize; i++) {
                int index = start + i;
                Integer tmp = map.getOrDefault(index, 0);
                if (tmp <= 0) {
                    return false;
                }
                map.put(index, tmp - 1);
                if (tmp == 1) {
                    map.remove(index);
                }
            }
        }
        return true;
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;

        // 上边和左边
        Queue<int[]> queue1 = new LinkedList<>();
        IntStream.range(0, n).boxed().forEach(t -> queue1.add(new int[]{t, 0}));
        IntStream.range(0, m).boxed().forEach(t -> queue1.add(new int[]{0, t}));
        boolean[][] visited1 = expand(heights, queue1);

        // 下边和右边
        Queue<int[]> queue2 = new LinkedList<>();
        IntStream.range(0, n).boxed().forEach(t -> queue2.add(new int[]{t, m-1}));
        IntStream.range(0, m).boxed().forEach(t -> queue2.add(new int[]{n-1, t}));
        boolean[][] visited2 = expand(heights, queue2);

        // 取交集
        List<List<Integer>> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited1[i][j] && visited2[i][j]) {
                    answer.add(Arrays.asList(i, j));
                }
            }
        }
        return answer;
    }

    private boolean[][] expand(int[][] heights, Queue<int[]> queue) {
        int[][] direction = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        int n = heights.length;
        int m = heights[0].length;

        boolean[][] visited = new boolean[n][m];
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            visited[x][y] = true;
            for (int i = 0; i < 4; i++) {
                int xn = x + direction[i][0];
                int yn = y + direction[i][1];
                if (xn >= 0 && xn < n && yn >= 0 && yn < m && !visited[xn][yn] && heights[xn][yn] >= heights[x][y]) {
                    queue.add(new int[]{xn, yn});
                    visited[xn][yn] = true;
                }
            }
        }
        return visited;
    }

    public double maxAverageRatio(int[][] classes, int extraStudents) {
        // 优先级队列，堆顶为通过率最低的班级
        PriorityQueue<double[]> queue = new PriorityQueue<>((a, b) -> {
            double p = (a[0] + 1) / (a[1] + 1) - a[0] / a[1];
            double q = (b[0] + 1) / (b[1] + 1) - b[0] / b[1];
            if (p > q) return -1;
            if (p < q) return 1;
            return 0;
        });

        for (int[] item : classes) {
            queue.add(new double[]{item[0], item[1]});
        }
        // 将聪明学生插入到班级
        while (extraStudents > 0) {
            double[] poll = queue.poll();
            poll[0] += 1;
            poll[1] += 1;
            queue.add(poll);
            extraStudents--;
        }
        // 统计通过率
        double sum = 0;
        while (!queue.isEmpty()) {
            double[] poll = queue.poll();
            sum += poll[0] / poll[1];
        }
        return sum / classes.length;
    }

    class NumArray {

        int[] tree;
        int n;

        public NumArray(int[] nums) {
            this.n = nums.length;
            this.tree = new int[2 * n];
            for (int i = n; i < 2 * n; i++) {
                this.tree[i] = nums[i - n];
            }
            for (int i = n-1; i > 0; i--) {
                this.tree[i] = this.tree[i * 2] + this.tree[i * 2 + 1];
            }
        }

        public void update(int index, int val) {
            int pos = index + n;        // 叶子节点的索引
            this.tree[pos] = val;
            while (pos > 1) {
                int parent = pos / 2;
                int left = parent * 2;      // 左节点
                int right = left + 1;       // 右节点
                this.tree[parent] = this.tree[left] + this.tree[right];
                pos = parent;
            }
        }

        public int sumRange(int left, int right) {
            left += n;
            right += n;
            int sum = 0;
            while (left <= right) {
                if ((left & 1) == 1) {
                    sum += this.tree[left];
                    left++;
                }
                if ((right & 1) == 0) {
                    sum += this.tree[right];
                    right--;
                }
                left /= 2;
                right /= 2;
            }
            return sum;
        }
    }

    public boolean equationsPossible(String[] equations) {
        int[] parent = new int[26];
        IntStream.range(0, 26).boxed().forEach(t -> parent[t] = t);

        for (String s : equations) {
            if (s.charAt(1) == '=') {
                int a = s.charAt(0) - 'a';
                int b = s.charAt(3) - 'a';
                union(parent, a, b);
            }
        }
        for (String s : equations) {
            if (s.charAt(1) == '!') {
                int a = s.charAt(0) - 'a';
                int b = s.charAt(3) - 'a';
                if (find(parent, a) == find(parent, b)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void union(int[] parent, int a, int b) {
        parent[find(parent, a)] = find(parent, b);
    }

    private int find(int[] parent, int index) {
        while (parent[index] != index) {
            parent[index] = parent[parent[index]];
            index = parent[index];
        }
        return index;
    }

    public String findLongestWord(String s, List<String> dictionary) {
        String answer = "";
        for (String item : dictionary) {
            for (int i = 0, j = 0; i < s.length() && j < item.length(); i++) {
                 if (s.charAt(i) == item.charAt(j)) {
                     j++;
                 }
                 if (j == item.length()) {      // 字典的字符串是 s 的序列串
                     // 长度更长，或者长度相等但字典序更小
                     if (answer.length() < item.length() || answer.length() == item.length() && item.compareTo(answer) < 0) {
                         answer = item;
                     }
                 }
            }
        }
        return answer;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> answer = new ArrayList<>();
        int n = matrix.length;
        int m = matrix[0].length;
        int top = 0, bottom = n-1, left = 0, right = m-1;
        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++) {   // 上
                answer.add(matrix[top][i]);
            }
            top++;
            for (int i = top; i <= bottom; i++) {   // 右
                answer.add(matrix[i][right]);
            }
            right--;
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {   // 下
                    answer.add(matrix[bottom][i]);
                }
                bottom--;
            }
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {   // 左
                    answer.add(matrix[i][left]);
                }
                left++;
            }
        }
        return answer;
    }



    public int getWinner(int[] arr, int k) {
        int win = 0;    // 连胜纪录
        int i = 0;
        while (win < k && i < arr.length-1) {
            if (arr[i] > arr[i+1]) {
                win++;
                arr[i+1] = arr[i];
            } else {
                win = 1;
            }
            i++;
        }
        return arr[i];
    }



    public int[][] updateMatrix(int[][] mat) {
        int[][] direction = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        int n = mat.length;
        int m = mat[0].length;
        Deque<int[]> queue = new LinkedList<>();
        int[][] answer = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {       // 将0边上的1添加到队列中
                    for (int k = 0; k < 4; k++) {
                        int x = i + direction[k][0];
                        int y = j + direction[k][1];
                        if (x >= 0 && x < n && y >= 0 && y < m && mat[x][y] == 1 && answer[x][y] == 0) {
                            answer[x][y] = 1;
                            queue.add(new int[]{x, y});
                        }
                    }
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int i = 0; i < 4; i++) {
                int xn = poll[0] + direction[i][0];
                int yn = poll[1] + direction[i][1];
                if (xn >= 0 && xn < n && yn >= 0 && yn < m && mat[xn][yn] == 1 && answer[xn][yn] == 0) {
                    answer[xn][yn] = answer[poll[0]][poll[1]] + 1;
                    queue.add(new int[]{xn, yn});
                }
            }
        }
        return answer;
    }

    public int[] findDiagonalOrder(int[][] mat) {
        int n = mat.length - 1;
        int m = mat[0].length - 1;
        ArrayList<Integer> list = new ArrayList<>();
        boolean flag = true;   // 控制方向
        for (int i = 0; i <= n + m; i++) {
            int y = Math.min(i, m);
            int x = i - y;
            ArrayList<Integer> tmp = new ArrayList<>();
            while (x <= n && y >= 0) {
                tmp.add(mat[x][y]);
                x++;
                y--;
            }
            if (flag) {
                Collections.reverse(tmp);
            }
            list.addAll(tmp);
            flag = !flag;
        }
        return list.stream().mapToInt(t -> t).toArray();
    }


    private int[][] direction = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    private boolean[][] visited;       // 记录是否访问

    public boolean exist(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(check(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean check(char[][] board, int x, int y, String word, int index) {
        if (index == word.length() - 1) {
            return board[x][y] == word.charAt(index);
        }
        if (board[x][y] == word.charAt(index)) {
            visited[x][y] = true;
            for (int k = 0; k < 4; k++) {
                int xn = x + direction[k][0];
                int yn = y + direction[k][1];
                if (xn >= 0 && xn < board.length && yn >= 0 && yn < board[0].length
                        && board[xn][yn] == word.charAt(index+1) && !visited[xn][yn]) {
                    if (check(board, xn, yn, word, index+1)) {
                        return true;
                    }
                }
            }
            visited[x][y] = false;
        }
        return false;
    }

    public String shiftingLetters(String s, int[] shifts) {
        long sum = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = shifts.length - 1; i >= 0; i--) {
            sum += shifts[i];
            int k = s.charAt(i) + (int) (sum % 26);
            char c = (char) (k > 'z' ? k - 26 : k);
            sb.append(c);
        }
        return sb.reverse().toString();
    }

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int[][] direction = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        Deque<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});
        int originColor = grid[row][col];
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        visited[row][col] = true;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            for (int i = 0; i < 4; i++) {
                int xn = x + direction[i][0];
                int yn = y + direction[i][1];
                if (xn >= 0 && xn < n && yn >= 0 && yn < m) {
                    if (visited[xn][yn]) {
                        continue;
                    }
                    if (grid[xn][yn] == originColor) {
                        visited[xn][yn] = true;
                        queue.offer(new int[]{xn, yn});
                    } else {
                        grid[x][y] = color;
                    }
                } else {    // (x, y) 为矩阵的边界
                    grid[x][y] = color;
                }
            }
        }
        return grid;
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);      // 排序
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int[] path = new int[n];
        for (int i = 1; i < n; i++) {
            for (int j = i-1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0 && dp[i] <= dp[j]) {      // 搜索[0, i)中最长的序列
                    dp[i] = dp[j] + 1;
                    path[i] = j;
                }
            }
        }
        int index = 0;      // 最后一个数字的索引
        int maxValue = 0;       // 最大长度
        for (int i = 0; i < n; i++) {
            if (maxValue < dp[i]) {
                maxValue = dp[i];
                index = i;
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        while (list.size() < maxValue) {
            list.add(nums[index]);
            index = path[index];
        }
        return list;
    }


    public int minAreaRect(int[][] points) {
        int OFFSET = 40001;
        HashSet<Integer> set = new HashSet<>();
        for (int[] point : points) {
            set.add(point[0] * OFFSET + point[1]);
        }
        int n = points.length;
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (points[i][0] != points[j][0] && points[i][1] != points[j][1]) {     // 矩形对角线的两个点
                    if (set.contains(points[i][0] * OFFSET + points[j][1])
                            && set.contains(points[j][0] * OFFSET + points[i][1])) {     // 判断另一条对角线的两个点是否存在
                        minValue = Math.min(minValue, Math.abs(points[i][0] - points[j][0]) * Math.abs(points[i][1] - points[j][1]));
                    }
                }

            }
        }
        return minValue == Integer.MAX_VALUE? 0: minValue;
    }


    public int partitionDisjoint(int[] nums) {
        int maxLeft = nums[0];
        int maxGlobal = nums[0];
        int pos = 0;
        for (int i = 0; i < nums.length; i++) {
            maxGlobal = Math.max(maxGlobal, nums[i]);
            if (nums[i] >= maxLeft) {
                continue;
            }
            maxLeft = maxGlobal;
            pos = i;
        }
        return pos + 1;
    }






    private void dfs(char[][] board, int row, int col) {
        int n = board.length;
        int m = board[0].length;
        Deque<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});
        board[row][col] = '#';
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int i = 0; i < 4; i++) {
                int xn = poll[0] + direction[i][0];
                int yn = poll[1] + direction[i][1];
                if (xn >= 0 && xn < n && yn >= 0 && yn < m && board[xn][yn] == 'O') {
                    board[xn][yn] = '#';
                    queue.add(new int[]{xn, yn});
                }
            }
        }
    }

    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        int[] visited = new int[n];   // 记录历史访问情况
        for (int i = 0; i < n; i++) {
            if (visited[i] != 0) continue;
            int cur = i;
            boolean valueType = nums[i] > 0;   // 正数还是负数

            while (true) {
                int next = ((cur + nums[cur]) % n + n) % n;
                if (next == cur) break;
                if (visited[next] != 0) {
                    if (visited[next] != i+1) break;
                    else return true;
                }
                if (valueType && nums[next] < 0) break;
                if (!valueType && nums[next] > 0) break;
                visited[next] = i + 1;
                cur = next;
            }
        }
        return false;
    }

    class TopVotedCandidate {
        private int[] persons;
        private int[] times;
        private int n;
        private int[] winnerByTime;     // winnerByTime[i] = k, 表示累计到时间times[i]的时候，获胜者是k


        public TopVotedCandidate(int[] persons, int[] times) {
            this.persons = persons;
            this.times = times;
            this.n = persons.length;
            this.winnerByTime = new int[n];
            int curWinner = -1;
            int count = 0;
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int c = map.getOrDefault(persons[i], 0) + 1;
                map.put(persons[i], c);
                if (c >= count) {
                    curWinner = persons[i];
                    count = c;
                }
                winnerByTime[i] = curWinner;
            }
        }

        public int q(int t) {
            int left = 0, right = n-1;
            while (left < right) {
                int mid = left + ((right - left) >> 1) + 1;
                if (times[mid] > t) {
                    right = mid - 1;
                } else if (times[mid] <= t) {
                    left = mid;
                }
            }
            return this.winnerByTime[left];
        }
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {

        int n = intervals.length;
        int[][] res = new int[n+1][2];  // n可能为0
        int i = 0;  // 遍历数组 intervals 的指针
        int j = 0;  // 结果数组 res 的指针
        while (i < n && intervals[i][1] < newInterval[0]) {     // 前段不重叠区间
            res[j++] = intervals[i++];
        }
        int[] tmp = newInterval;
        while (i < n && intervals[i][0] <= newInterval[1]) {     // 重叠区间
            tmp[0] = Math.min(intervals[i][0], tmp[0]);
            tmp[1] = Math.max(intervals[i][1], tmp[1]);
            i++;
        }
        res[j++] = tmp;
        while (i < n) {     // 后段不重叠区间
            res[j++] = intervals[i++];
        }
        return Arrays.copyOf(res, j);
    }

    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int left = 0, right = -1;  // 需要排序的左右索引，初始值是处理无需排序的情况
        int minValue = nums[n-1];
        int maxValue = nums[0];
        for (int i = 0; i < n; i++) {
            if (nums[i] >= maxValue) {   // 从左向右遍历，维护最大值，计算右边界
                maxValue = nums[i];
            } else {
                right = i;
            }

            if (nums[n-1-i] <= minValue) {   // 从右向左遍历，维护最小值，计算左边界
                minValue = nums[n-1-i];
            } else {
                left = n-1-i;
            }
        }
        return right-left+1;
    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0;
        int l = 0;   // 左指针，指向子数组乘积小于k的左边界
        int answer = 0;     // 答案
        int prod = 1;       // 累乘
        for (int i = 0; i < nums.length; i++) {
            prod *= nums[i];
            while (prod >= k) {     // 乘积大于0，左指针右移
                prod /= nums[l++];
            }
            answer += i - l + 1;
        }
        return answer;
    }

    public int jump(int[] nums) {
        int n = nums.length;
        int maxPos = 0;
        int step = 0;
        int r = 0;
        for (int i = 0; i < n-1; i++) {
            maxPos = Math.max(maxPos, nums[i] + i);
            if (r == i) {
                r = maxPos;
                step++;
            }
        }
        return step;
    }


    public List<String> wordSubsets(String[] words1, String[] words2) {
        // words2 处理
        int[] words2Array = new int[26];
        for (String s : words2) {
            int[] c = count(s);
            for (int i = 0; i < 26; i++) {
                words2Array[i] = Math.max(words2Array[i], c[i]);
            }
        }
        // 遍历 words1
        ArrayList<String> list = new ArrayList<>();
        for (String s : words1) {
            int[] c = count(s);
            int i = 0;
            while (i < 26 && c[i] >= words2Array[i]) {
                i++;
            }
            if (i == 26) {
                list.add(s);
            }
        }
        return list;
    }

    private int[] count(String s) {
        int[] array = new int[26];
        for (char c : s.toCharArray()) {
            array[c - 'a']++;
        }
        return array;
    }


    private int doRob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }
        return dp[n - 1];
    }

    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }
        return dp[n - 1];
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if(sum % k != 0) return false;
        int target = sum / k;   // 每个分组的总和
        boolean[] visited = new boolean[nums.length];
        return dfs(nums, k, target, 0, 0, visited);
    }

    /**
     * 回溯法
     * @param nums
     * @param k：分组个数
     * @param target：每个分组的总和
     * @param curSum：当前分组的累和
     * @param index：nums的索引
     * @param visited：记录是否已访问
     */
    private boolean dfs(int[] nums, int k, int target, int curSum, int index, boolean[] visited) {
        if (k == 0) return true;    // 全部分组都已经处理完毕
        if (curSum == target) {
            return dfs(nums, k-1, target, 0, 0, visited);   // 处理下一个分组
        }
        for (int i = index; i < nums.length; i++) {     // 当前分组添加数字
            if (!visited[i] && curSum + nums[i] <= target) {
                visited[i] = true;
                if (dfs(nums, k, target, curSum + nums[i], i+1, visited)) {
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }

    class RLEIterator {
        private int[] number;
        private int[] count;
        private int index;
        private int n;  // 数字的个数

        public RLEIterator(int[] encoding) {
            this.n = encoding.length / 2;
            number = new int[n];
            count = new int[n];
            this.index = 0;
            int i = 0;
            int j = 0;
            while (i < encoding.length) {
                this.count[j] = encoding[i++];
                this.number[j] = encoding[i++];
                j++;
            }
        }

        public int next(int n) {
            while (n > 0 && this.index < this.n) {
                int c = this.count[index];
                if (c < n) {
                    n -= c;
                    index++;
                } else {
                    this.count[index] -= n;
                    return this.number[index];
                }
            }
            return -1;
        }
    }

    public int hIndex(int[] citations) {
        int n = citations.length;
        int left = 0, right = n - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (citations[mid] < n - mid) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return citations[right] >= n - right? n-right: 0;
    }

    public List<Integer> majorityElement(int[] nums) {
        int cand1 = nums[0], cand2 = nums[0];   // 最多有两个候选人
        int count1 = 0, count2 = 0;     // 两个候选人的票数
        for (int num : nums) {
            if (num == cand1) {
                count1++;
                continue;
            }
            if (num == cand2) {
                count2++;
                continue;
            }
            count1--;
            count2--;

        }
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num == cand1) count1++;
            else if (num == cand2) count2++;
        }
        ArrayList<Integer> list = new ArrayList<>();
        if (count1 > nums.length / 3) list.add(cand1);
        if (count2 > nums.length / 3) list.add(cand2);
        return list;
    }

    public int smallestRangeII(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int res = nums[n -1] - nums[0];
        for (int i = 0; i < n-1; i++) {
            int minValue = Math.min(nums[0] + k, nums[i+1] - k);
            int maxValue = Math.max(nums[i] + k, nums[n-1] - k);
            res = Math.min(res, maxValue - minValue);
        }
        return res;
    }

    public int expressiveWords(String s, String[] words) {
        String feature = getFeature(s);
        int res = 0;
        for (String word : words) {
            if (find(word, feature)) {
                res++;
            }
        }
        return res;
    }

    private boolean find(String word, String feature) {
        int i = 0;      // word 的索引
        int j = 0;      // feature 的索引
        while (j < feature.length()) {
            if (feature.charAt(j) >= '0' && feature.charAt(j) <= '9') {
                int count = 0;
                while (feature.charAt(j) >= '0' && feature.charAt(j) <= '9') {
                    count = count * 10 + feature.charAt(j) - '0';
                    j++;
                }
                int countWord = 0;
                while (i < word.length() && word.charAt(i) == feature.charAt(j)) {
                    countWord++;
                    i++;
                }
                if (countWord > count || countWord == 0) {
                    return false;
                }
                j++;
            } else {
                if (i < word.length() && word.charAt(i) != feature.charAt(j)) {
                    return false;
                }
                i++;
                j++;
            }
        }
        return i == word.length();
    }

    /** 计算字符串s的特征，比如 heeellooo，结果为：h3e2l3o */
    private String getFeature(String s) {
        s += ' ';
        StringBuilder sb = new StringBuilder();
        char pre = s.charAt(0);
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == pre) {
                count++;
            } else {
                if (count >= 3) {
                    sb.append(count);
                    sb.append(pre);
                } else {
                    while (count > 0) {
                        sb.append(pre);
                        count--;
                    }
                }
                pre = s.charAt(i);
                count = 1;
            }
        }
        return sb.toString();
    }

    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int[] t : dp) {
            Arrays.fill(t, 2);  //  初始化值为2
        }
        // 记录数字对应的索引
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }

        int res = 0;    //
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                int diff = arr[j] - arr[i];
                Integer index = map.getOrDefault(diff, -1);
                if (index != -1 && index < i) {
                    dp[i][j] = Math.max(dp[i][j], dp[index][i] + 1);
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res > 2? res: 0;
    }

    public int minDominoRotations(int[] tops, int[] bottoms) {
        int count = check(tops[0], tops, bottoms);      // 假设所有卡牌相同的值在上层
        if (count == -1) {
            count = check(bottoms[0], tops, bottoms);   // 假设所有卡牌相同的值在下层
        }
        return count;
    }

    private int check(int target, int[] tops, int[] bottoms) {
        int n = tops.length;
        int countTop = 0;   // 上层的翻转次数
        int countBottom = 0;    // 下层的翻转次数
        for (int i = 0; i < n; i++) {
            if (target != tops[i] && target != bottoms[i]) return -1;
            if (target != tops[i]) countTop++;
            if (target != bottoms[i]) countBottom++;
        }
        return Math.min(countTop, countBottom);
    }


    class TimeMap {

        private Map<String, List<Pair>> map;


        /** Initialize your data structure here. */
        public TimeMap() {
            this.map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            List<Pair> list = map.computeIfAbsent(key, t -> new ArrayList<>());
            list.add(new Pair(timestamp, value));
        }

        public String get(String key, int timestamp) {
            List<Pair> list = map.getOrDefault(key, new ArrayList<>());
            return binarySearch(list, timestamp);
        }

        private String binarySearch(List<Pair> list, int timestamp) {
            if (list.isEmpty()) {
                return "";
            }
            if (list.get(0).timestamp > timestamp) {
                return "";
            }
            int l = 0, r = list.size()-1;
            while (l < r) {
                int mid = l + ((r - l) >> 1) + 1;
                if (list.get(mid).timestamp > timestamp) {
                    r = mid-1;
                } else {
                    l = mid;
                }
            }
            return list.get(l).value;
        }



        class Pair{
            private int timestamp;
            private String value;

            public Pair(int timestamp, String value) {
                this.timestamp = timestamp;
                this.value = value;
            }
        }
    }

    public int subarraySum(int[] nums, int k) {
        int res = 0;    // 结果
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int preSum = 0;     // 前缀和
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            Integer count = map.getOrDefault(preSum - k, 0);
            res += count;
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }
        return res;
    }

    public int[] prevPermOpt1(int[] arr) {
        int i = arr.length - 2;
        while (i >= 0 && arr[i] <= arr[i+1]) {      // 从后往前，寻找第一个符合 arr[i] > arr[i+1] 的数字
            i--;
        }
        if (i == -1) {      // 已经是最小排列
            return arr;
        }
        // 寻找比 arr[i] 小的最大数字 arr[k]
        int k = i + 1;
        for (int j = k; j < arr.length; j++) {
            if (arr[j] < arr[i] && arr[k] < arr[j]) {
                k = j;
            }
        }
        swap(arr, i, k);
        return arr;
    }

    private void swap(int[] arr, int p, int q) {
        int tmp = arr[p];
        arr[p] = arr[q];
        arr[q] = tmp;
    }

    public int numFactoredBinaryTrees(int[] arr) {
        Arrays.sort(arr);
        int MOD = 1_000_000_007;
        int n = arr.length;
        long[] dp = new long[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            int l = 0, r = i - 1;
            while (l <= r) {
                long multi = ((long) arr[l]) * arr[r];
                if (multi > arr[i]) r--;
                else if (multi < arr[i]) l++;
                else {
                    int k = l == r? 1: 2;
                    dp[i] += dp[l] * dp[r] * k;
                    l++;
                    r--;
                }
            }
            dp[i] %= MOD;
        }
        // 计算结果
        int res = 0;
        for (long item : dp) {
            res += item;
            res %= MOD;
        }
        return res;
    }



    public int subarraysDivByK(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int res = 0;    // 结果
        int sumPre = 0;     // 前缀和
        for (int i = 0; i < nums.length; i++) {
            sumPre = (sumPre + nums[i]) % k;
            if (sumPre < 0) {   // 负数处理
                sumPre += k;
            }
            Integer v = map.getOrDefault(sumPre, 0);
            res += v;
            map.put(sumPre, v+1);
        }
        return res;
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Disjoint disjoint = new Disjoint(accounts.size());
        // Key: 邮箱  Value：账号id，即account数组的下标
        HashMap<String, Integer> emailMap = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            List<String> single = accounts.get(i);
            for (int j = 1; j < single.size(); j++) {
                String email = single.get(j);
                if (!emailMap.containsKey(email)) {
                    emailMap.put(email, i);
                } else {
                    disjoint.merge(i, emailMap.get(email));
                }
            }
        }
        // Key: 账号id    Value：账号包含的邮箱
        HashMap<Integer, List<String>> accountMap = new HashMap<>();
        for (Map.Entry<String, Integer> entry : emailMap.entrySet()) {
            Integer id = disjoint.find(entry.getValue());
            List<String> list = accountMap.computeIfAbsent(id, t -> new ArrayList<>());
            list.add(entry.getKey());
        }

        List<List<String>> res = new ArrayList<>();      // 结果
        for (Map.Entry<Integer, List<String>> entry : accountMap.entrySet()) {
            String name = accounts.get(entry.getKey()).get(0);      // 名字
            List<String> emailList = entry.getValue();
            Collections.sort(emailList);
            emailList.add(0, name);     // 将名字添加到最前面
            res.add(emailList);
        }
        return res;
    }

    class Disjoint {
        int[] parent;

        public Disjoint(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
            }
        }

        public Integer find(int k) {
            if (parent[k] != k) {
                parent[k] = find(parent[k]);
            }
            return parent[k];
        }

        public void merge(int a, int b) {
            parent[find(b)] = find(a);
        }
    }

    public int largest1BorderedSquare(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][][] dp = new int[n + 1][m + 1][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    dp[i+1][j+1][0] = dp[i+1][j][0] + 1;
                    dp[i+1][j+1][1] = dp[i][j+1][1] + 1;
                }
            }
        }
        int res = 0;    // 结果正方形的边长
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int edge = Math.min(dp[i][j][0], dp[i][j][1]);
                while (edge > 0) {
                    if (dp[i][j-edge+1][1] >= edge && dp[i-edge+1][j][0] >= edge) {
                        res = Math.max(res, edge);
                        break;      // 更小的 edge 没有必要计算了
                    }
                    edge--;
                }
            }
        }

        return res * res;
    }

    public int[] numMovesStonesII(int[] stones) {
        Arrays.sort(stones);
        int n = stones.length;
        // 计算最大值
        int countEmpty = stones[n-1] - stones[0] + 1 - n;   // 最开始的时候，空位数量
        int maxValue = countEmpty - Math.min(stones[n-1] - stones[n-2] - 1, stones[1] - stones[0] - 1);

        // 计算最小值
        int minValue = maxValue;
        int j = 0;      // 大小为n的滑动窗口开始索引
        for (int i = 0; i < n; i++) {   // stone 数组的索引
            while (j + 1 < n && stones[j+1] - stones[i] + 1 <= n) {
                j++;
            }
            int countStone = j - i + 1;  // 滑动窗口内的石子数量
            int countMove = n - countStone;     // 移动次数
            if (j - i + 1 == n-1 && stones[j] - stones[i] + 1 == n-1) {   // 特殊情况处理
                countMove = 2;
            }
            minValue = Math.min(minValue, countMove);
        }
        return new int[]{minValue, maxValue};
    }

    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i >= num) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }


    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int sum = 0;
        int minSum = Integer.MAX_VALUE;
        int minIndex = 0;
        for (int i = 0; i < n; i++) {
            sum += gas[i] - cost[i];
            if (sum < minSum) {
                minSum = sum;
                minIndex = i;
            }
        }
        return sum < 0? -1: (minIndex + 1) % n;
    }

    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        LinkedList<Integer> list1 = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) list1.add(hash(i, j));
                if (img2[i][j] == 1) list2.add(hash(i, j));
            }
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Integer a : list1) {
            for (Integer b : list2) {
                int code = a - b;
                map.put(code, map.getOrDefault(code, 0) + 1);
            }
        }
        return map.isEmpty()? 0: map.values().stream().mapToInt(t -> t).max().getAsInt();
    }


    private Integer hash(int a, int b) {
        return (a << 6) + b;
    }

    public int findBestValue(int[] arr, int target) {
        Arrays.sort(arr);
        int n = arr.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int a = (target - sum) / (n - i);
            if (a < arr[i]) {
                double b = ((double) (target - sum)) / (n - i);
                if (b - a > 0.5) {
                    return a + 1;
                } else {
                    return a;
                }
            }
            sum += arr[i];
        }
        return arr[n - 1];
    }

    public int maxSideLength(int[][] mat, int threshold) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] dp = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = mat[i-1][j-1] + dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1];
            }
        }
        int res = 0;
        for (int k = 1; k <= Math.min(n, m); k++) {      // 边长
            for (int i = 1; i <= n; i++) {       // 第 i 行
                for (int j = 1; j <= m; j++) {   // 第 j 列
                    if (i - k < 0 || j - k < 0) {
                        continue;
                    }
                    int area = dp[i][j] - dp[i - k][j] - dp[i][j - k] + dp[i-k][j-k];
                    if (area <= threshold) {
                        res = Math.max(res, k);
                    }
                }
            }
        }
        return res;
    }


    private int res = 0;
    private int n;
    private Map<Integer, Integer> map;      // Key: 数字  Value：二进制数1的个数
    private int[] array;

    /** 计算数字转变为二进制数后 1 的个数*/
    private int get(Integer num) {
        if (map.containsKey(num)) {
            return map.get(num);
        }
        int count = 0;
        int tmp = num;
        while (tmp > 0) {
            count++;
            tmp -= lowbit(tmp);
        }
        map.put(num, count);
        return count;
    }

    /** 计算二进制数末尾1的值 */
    private int lowbit(Integer k) {
        return k & (-k);
    }
    public int maxLength(List<String> arr) {
        map = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();
        for (String s : arr) {
            int cur = 0;
            for (char ch :s.toCharArray()){
                int k = ch - 'a';
                if (((cur >> k) & 1) != 0) {
                    cur = -1;
                    break;
                }
                cur |= (1 << k);
            }
            if (cur != -1) {
                set.add(cur);
            }
        }

        this.n = set.size();
        int total = 0;
        int index = 0;
        this.array = new int[this.n];
        for (Integer t : set) {
            total |= t;
            this.array[index++] = t;
        }
        dfs(0, 0, total);
        return res;
    }

    /**
     * @param index: array数组的索引
     * @param cur：当前的结果
     * @param total：剩余的可选字符串
     */
    private void dfs(Integer index, Integer cur, Integer total) {
        if (get(cur | total) <= this.res) {      // 剪枝，当前的结果再加上剩余的小于 res，则不必往后递归了
            return;
        }
        if (index == n) {   // 遍历完全部字符串
            this.res = Math.max(this.res, get(cur));
            return;
        }
        int other = this.array[index];
        if ((cur & other) == 0) {   // 字母都不相同，可以添加
            dfs(index + 1, cur | other, total - (total & other));
        }
        dfs(index + 1, cur, total);
    }

    public int maxAbsValExpr(int[] arr1, int[] arr2) {
        int n = arr1.length;
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        ArrayList<Integer> list3 = new ArrayList<>();
        ArrayList<Integer> list4 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = arr1[i];
            int y = arr2[i];
            list1.add(x + y + i);
            list2.add(x + y - i);
            list3.add(x - y + i);
            list4.add(x - y - i);
        }
        int a = max(list1) - min(list1);
        int b = max(list2) - min(list2);
        int c = max(list3) - min(list3);
        int d = max(list4) - min(list4);
        return Math.max(Math.max(a, b), Math.max(c, d));
    }

    private int max(ArrayList<Integer> list) {
        return list.stream().mapToInt(t -> t).max().getAsInt();
    }
    private int min(ArrayList<Integer> list) {
        return list.stream().mapToInt(t -> t).min().getAsInt();
    }

    public boolean isPossibleDivide(int[] nums, int k) {
        int n = nums.length;
        if (n % k != 0) {
            return false;
        }
        // 有序字典，Key：数字  Value：数量
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        while (!map.isEmpty()) {
            Integer start = map.firstKey();
            for (int i = 0; i < k; i++) {
                int num = start + i;
                Integer count = map.getOrDefault(num, 0);
                if (count == 0) {
                    return false;
                }
                map.put(num, count - 1);
                if (count == 1) {
                    map.remove(num);
                }
            }
        }
        return true;
    }

    public int longestStrChain(String[] words) {
        Arrays.sort(words, Comparator.comparing(String::length));   // 根据长度排序
        int[] lenArray = new int[17];
        for (int i = 0; i < words.length; i++) {
            lenArray[words[i].length()] = i;
        }

        int res = 0;
        int[] dp = new int[words.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < words.length; i++) {
            int target = words[i].length() - 1;
            int j = lenArray[target];
            while (j >= 0 && words[j].length() == target) {
                if (check(words[j], words[i])) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                j--;
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /** 判断 a 是否是 b 的前身 */
    private boolean check(String a, String b) {
        int an = a.length();
        int bn = b.length();
        if (an + 1 != bn) {
            return false;
        }
        int ai = 0, bi = 0;
        while (ai < an && bi < bn) {
            if (a.charAt(ai) == b.charAt(bi)) {
                ai++;
            }
            bi++;
        }
        return ai == an;
    }


    String START = "start";
    String END = "end";
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] array = new int[n];
        Stack<Integer> stack = new Stack<>();       // 栈，保存的是标识符
        int preTime = 0;        // 上一次事件的时间
        for (int i = 0; i < logs.size(); i++) {
            String[] cur = logs.get(i).split(":");
            if (END.equals(cur[1])) {
                array[stack.peek()] += Integer.parseInt(cur[2]) - preTime + 1;
                preTime = Integer.parseInt(cur[2]) + 1;
                stack.pop();
            } else if (START.equals(cur[1])) {
                if (!stack.isEmpty()) {
                    array[stack.peek()] += Integer.parseInt(cur[2]) - preTime;
                }
                preTime = Integer.parseInt(cur[2]);
                stack.push(Integer.parseInt(cur[0]));
            }
        }
        return array;
    }


    @Test
    public void test() {
        LeetCodeSolution lcs = new LeetCodeSolution();
        String[] array = {"0:start:0","0:start:2","0:end:5","1:start:7","1:end:7","0:end:8"};
        List<String> list = Arrays.stream(array).collect(Collectors.toList());
        int[] r = lcs.exclusiveTime(2, list);
        System.out.println(Arrays.toString(r));
    }
}