package com.junyi;


import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.IntStream;

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
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
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
    private Map<Integer, Integer> map;      // Key: 数字  Value：二进制数1的个数
    private int[] array;

    /** 计算数字转变为二进制数后 1 的个数*/
    private int find(String s, HashSet<String> set, Integer num) {
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

    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return b[1] - a[1];
        });      // 根据起点从小到大排序，如果起点相同则更长的排在前面
        int count = 0;    // 计算需要删除的区间个数

        int[] pre = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (interval[0] >= pre[0] && interval[1] <= pre[1]) {
                count++;
            } else {
                pre = interval;
            }
        }
        return intervals.length - count;
    }

    public int singleNonDuplicate(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if ((mid & 1) == 1) {   // 奇数
                if (nums[mid] == nums[mid + 1]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {    // 偶数
                if (nums[mid] == nums[mid + 1]) {
                    l = mid + 2;
                } else {
                    r = mid;
                }
            }
        }
        return nums[l];
    }

    public boolean isPossible(int[] nums) {
        // 统计数字的个数
        HashMap<Integer, Integer> countMap = new HashMap<>();
        // 结尾为该数字的连续子序列数量
        HashMap<Integer, Integer> tailMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        for (int num : nums) {
            int value = countMap.getOrDefault(num, 0);
            if (value == 0) {
                continue;
            } else if (tailMap.getOrDefault(num - 1, 0) > 0) {     // 当前数字可以拼接在已有的序列中
                countMap.put(num, value-1);
                tailMap.put(num-1, tailMap.get(num - 1) - 1);
                tailMap.put(num, tailMap.getOrDefault(num, 0) + 1);
            } else if (countMap.getOrDefault(num+1, 0) > 0 && countMap.getOrDefault(num+2, 0) > 0) {    // 当前数字可以与后面的数字形成连续序列
                countMap.put(num, value - 1);
                countMap.put(num + 1, countMap.get(num + 1) - 1);
                countMap.put(num + 2, countMap.get(num + 2) - 1);
                tailMap.put(num + 2, tailMap.getOrDefault(num + 2, 0) + 1);
            } else {
                return false;
            }
        }
        // 全部数字遍历完毕
        return true;
    }

    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        // 计算前缀和
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        // firstLen 的连续子数组最大和
        int maxFirstLen = nums[firstLen - 1];
        // 结果
        int res = nums[firstLen + secondLen - 1];
        for (int i = firstLen + secondLen; i < nums.length; i++) {
            maxFirstLen = Math.max(maxFirstLen, nums[i - secondLen] - nums[i - firstLen - secondLen]);
            // 当前的 secondLen 子数组和
            int curSecondLen = nums[i] - nums[i - secondLen];
            res = Math.max(res, maxFirstLen + curSecondLen);
        }
        return res;
    }

    public double largestSumOfAverages(int[] nums, int k) {

        int n = nums.length;
        double[] preSum = new double[n+1];    // 前缀和
        double[][] dp = new double[n+1][k+1];     // dp[i][j] 表示 nums 前 i 个数，切分为j份的平均值总和
        // 计算前缀和
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i-1] + nums[i-1];
        }
        // 计算结果
        for (int i = 1; i < dp.length; i++) {   // 遍历nums
            dp[i][1] = preSum[i] / i;   // 计算 k = 1
            for (int j = 2; j <= k && j <= i; j++) {   // 遍历 k
                for (int l = 1; l < i; l++) {
                    dp[i][j] = Math.max(dp[i][j], dp[l][j-1] + (preSum[i] - preSum[l]) / (i - l));
                }
            }
        }
        return dp[n][k];
    }

    public int numSubarraysWithSum(int[] nums, int goal) {
        int n = nums.length;
        // 前缀和
        int preSum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();    // 保存前缀和结果
        map.put(0, 1);
        // 计算结果
        int res = 0;
        for (int i = 0; i < n; i++) {
            preSum += nums[i];
            int target = preSum - goal;
            res += map.getOrDefault(target, 0);
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }
        return res;
    }


    public int[][] highestPeak(int[][] isWater) {
        int n = isWater.length;
        int m = isWater[0].length;
        int[][] direction = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        boolean[][] visited = new boolean[n][m];    // 记录已经访问的格子
        int[][] res = new int[n][m];
        Deque<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (isWater[i][j] == 1) {       // 找到所有的水域格子
                    queue.addLast(new int[]{i, j});
                    visited[i][j] = true;
                    res[i][j] = 0;
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] poll = queue.pollFirst();
            int curLength = res[poll[0]][poll[1]];
            for (int i = 0; i < 4; i++) {
                int xn = poll[0] + direction[i][0];
                int yn = poll[1] + direction[i][1];
                if (xn >= 0 && xn < n && yn >= 0 && yn < m && !visited[xn][yn]) {
                    res[xn][yn] = curLength+1;
                    visited[xn][yn] = true;
                    queue.addLast(new int[]{xn, yn});
                }
            }
        }
        return res;
    }

    public int[] getStrongest(int[] arr, int k) {
        Arrays.sort(arr);
        int mid = arr[(arr.length - 1) / 2];      // 中位数

        int l = 0, r = arr.length - 1;  // 左右指针
        int[] res = new int[k];     // 结果数组
        int i = 0;  // 结果数组 res 的指针
        while (i < k) {     // 获取 k 个数
            int a = Math.abs(arr[l] - mid);
            int b = Math.abs(arr[r] - mid);
            if (a > b) {
                res[i++] = arr[l++];
            } else {
                res[i++] = arr[r--];
            }
        }
        return res;
    }



    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        int[][] memo = new int[n][n];
        return helper(nums, 0, n - 1, memo) >= 0;
    }

    private int helper(int[] nums, int i, int j, int[][] memo) {
        if (i == j) {
            return nums[i];
        }
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        int a = nums[i] - helper(nums, i + 1, j, memo);
        int b = nums[j] - helper(nums, i, j - 1, memo);
        int max = Math.max(a, b);
        memo[i][j] = max;
        return max;
        
    }

    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        int n = values.length;
        int[][] data = new int[n][2];
        for (int i = 0; i < n; i++) {   // 整合 values 数组和 labels 数组
            data[i][0] = values[i];
            data[i][1] = labels[i];
        }
        Arrays.sort(data, (o1, o2) -> o2[0] - o1[0]);   // 根据 value 从大到小排序
        int res = 0;    // 结果
        HashMap<Integer, Integer> map = new HashMap<>();    // 记录使用的数据的 label，及其数量
        int count = 0;      // 记录已经统计的数字数量
        int i = 0;      // 索引
        while (i < n && count < numWanted) {
            Integer v = map.getOrDefault(data[i][1], 0);
            if (v < useLimit) {
                count++;
                res += data[i][0];
                map.put(data[i][1], v + 1);
            }
            i++;
        }
        return res;
    }

    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        int[][] dp = new int[n][n];
        for (int i = 2; i < n; i++) {
            for (int j = i - 2; j >= 0; j--) {
                for (int k = j + 1; k < i; k++) {
                    int v = values[i] * values[j] + values[k] + dp[j][k] + dp[k][i];
                    if (dp[j][i] == 0) {
                        dp[j][i] = v;
                    } else {
                        dp[j][i] = Math.min(dp[j][i], v);
                    }
                }
            }
        }
        return dp[0][n-1];
    }

    public int maxEqualRowsAfterFlips(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        HashMap<String, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 0; i < n; i++) {
            boolean firstZero = matrix[i][0] == 0 ? true : false;
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < m; j++) {
                if (firstZero) {
                    sb.append(matrix[i][j]);
                } else {
                    sb.append(matrix[i][j] ^ 1);
                }
            }
            String s = sb.toString();
            Integer v = map.getOrDefault(s, 0);
            map.put(s, v + 1);
            res = Math.max(res, v + 1);
        }
        return res;
    }

    public int maxChunksToSorted(int[] arr) {
        int max = 0;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            if (max == i) {
                count++;
            }
        }
        return count;
    }


    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        List<List<String>> res = new ArrayList<>();
        for (int i = 1; i <= searchWord.length(); i++) {
            res.add(find(searchWord.substring(0, i), products));
        }
        return res;
    }

    private List<String> find(String s, String[] products) {
        Integer index = getStartIndex(s, products);
        ArrayList<String> list = new ArrayList<>();
        while (index < products.length && list.size() < 3 && products[index].startsWith(s)) {
            list.add(products[index++]);
        }
        return list;
    }



    /** 获取以字符串 s 开头的最小索引 */
    private Integer getStartIndex(String s, String[] products) {
        int left = 0, right = products.length - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (s.compareTo(products[mid]) > 0) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }


    public int rangeSum(int[] nums, int n, int left, int right) {
        int MOD = 1_000_000_007;
        int[] preSum = new int[n+1];  // 前缀和

        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i-1] + nums[i-1];
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i < preSum.length; i++) {
            for (int j = 0; j < i; j++) {
                list.add(preSum[i] - preSum[j]);
            }
        }
        Collections.sort(list);
        int res = 0;
        for (Integer t : list.subList(left - 1, right)) {
            res = (res + t) % MOD;
        }
        return res;
    }

    public int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
        int evenSum = 0;    // 偶数和
        for (int num : nums) {
            if ((num & 1) == 0) {
                evenSum += num;
            }
        }
        ArrayList<Integer> result = new ArrayList<>();      // 结果
        for (int[] query : queries) {
            int index = query[1];
            int oldValue = nums[index];
            int newValue = query[0] + oldValue;
            if ((oldValue & 1) == 0) {      // 旧值是偶数
                if ((newValue & 1) == 0) {       // 新值是偶数
                    evenSum += query[0];
                } else {    // 新值是奇数
                    evenSum -= oldValue;
                }
            } else if ((newValue & 1) == 0) {   // 旧值是奇数且新值是偶数
                evenSum += newValue;
            }
            nums[index] = newValue;
            result.add(evenSum);
        }
        return result.stream().mapToInt(t -> t).toArray();
    }

    class BrowserHistory {
        private String[] array;     // 数据
        private Integer curIndex;   // 当前的位置
        private Integer size;   // 网站数量

        public BrowserHistory(String homepage) {
            array = new String[1000];
            array[0] = homepage;
            curIndex = 0;
            size = 1;
        }

        public void visit(String url) {
            array[++curIndex] = url;
            size = curIndex + 1;
        }

        public String back(int steps) {
            curIndex -= steps;
            if (curIndex < 0) {
                curIndex = 0;
            }
            return array[curIndex];
        }

        public String forward(int steps) {
            curIndex += steps;
            if (curIndex >= size) {
                curIndex = size - 1;
            }
            return array[curIndex];
        }
    }

    public char[][] rotateTheBox(char[][] box) {
        int n = box.length;
        int m = box[0].length;
        // 先生成结果数组
        char[][] result = new char[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[j][n-i-1] = box[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            int index = m - 1;
            for (int j = m - 1; j > 0; j--) {
                if (result[j][i] == '#') {
                    result[j][i] = '.';
                    result[index--][i] = '#';
                } else if (result[j][i] == '*') {
                    index = j-1;
                }
            }
        }
        return result;
    }

    public int numberOfSubarrays(int[] nums, int k) {
        // prefixCount[i] = t 表示的意思是累积奇数数字个数为i，t为第i个奇数前的连续偶数个数+1
        int[] prefixCount = new int[nums.length + 1];
        prefixCount[0] = 1;
        int count = 0;      // 累积的奇数数字个数
        int res = 0;        // 最终的结果
        for (int num : nums) {
            count += num & 1;
            prefixCount[count]++;
            if (count >= k) {
                res += prefixCount[count - k];
            }
        }
        return -1;
    }

    public int arrayNesting(int[] nums) {
        int n = nums.length;
        int maxLength = 0;
        boolean[] memo = new boolean[n];    // 记录数据是否访问过
        for (int i = 0; i < n; i++) {
            if (memo[i] == false) {
                int index = i;
                int count = 0;
                do {
                    memo[index] = true;
                    count++;
                    index = nums[index];
                }
                while (index != i);
                maxLength = Math.max(maxLength, count);
            }
        }
        return maxLength;
    }

    public boolean carPooling(int[][] trips, int capacity) {
        int[] road = new int[1001];

        for (int[] trip : trips) {
            road[trip[1]] += trip[0];
            road[trip[2]] -= trip[0];
        }
        // 第一个的特殊判断
        if (road[0] > capacity) {
            return false;
        }

        for (int i = 1; i < road.length; i++) {
            road[i] += road[i - 1];
            if (road[i] > capacity) {
                return false;
            }
        }
        return true;
    }

    public int minMoves(int[] nums) {
        int minValue = Arrays.stream(nums).min().getAsInt();    // 最小值
        int answer = 0;
        for (int num : nums) {
            answer += (num - minValue);
        }
        return answer;

    }

    public String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int count = k;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '-') {
                continue;
            }
            sb.append(Character.toUpperCase(c));
            count--;
            if (count == 0) {
                count = k;
                sb.append('-');
            }
        }
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '-') {    // 特殊情况处理
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.reverse().toString();
    }

    public boolean detectCapitalUse(String word) {
        if (word.length() > 1 && Character.isLowerCase(word.charAt(0)) && Character.isUpperCase(word.charAt(1))) {
            return false;
        }

        for (int i = 2; i < word.length(); i++) {
            if (Character.isUpperCase(word.charAt(i)) ^ Character.isUpperCase(word.charAt(1))) {
                return false;
            }
        }
        return true;
    }

    public int minimumRefill(int[] plants, int capacityA, int capacityB) {
        int left = 0;       // 左指针
        int right = plants.length - 1;      // 右指针
        int result = 0;     // 结果
        int remainA = capacityA, remainB = capacityB;       // A 和 B的剩余水量
        while (left < right) {
            if (plants[left] > remainA) {       // 左边
                remainA = capacityA;
                result++;
            }
            remainA -= plants[left];

            if (plants[right] > remainB) {      // 右边
                remainB = capacityB;
                result++;
            }
            remainB -= plants[right];
            left++;
            right--;
        }
        if (left == right && plants[left] > remainA && plants[left] > remainB) {    // 特殊情况处理，两人共浇一个植物，且两人剩余水量都不足，那么A需要重新装水
            result++;
        }
        return result;
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < primes.length; i++) {
            queue.add(new int[]{primes[i], i, 0});
        }
        int[] res = new int[n];      // 结果
        res[0] = 1;
        for (int i = 1; i < n; ) {
            int[] poll = queue.poll();
            if (res[i - 1] != poll[0]) {
                res[i++] = poll[0];
            }
            int idx = poll[2];
            queue.add(new int[]{res[idx + 1] * primes[poll[1]], poll[1], idx + 1});
        }
        return res[n - 1];
    }
    class MedianFinder {

        private final PriorityQueue<Integer> A;
        private final PriorityQueue<Integer> B;

        /** initialize your data structure here. */
        public MedianFinder() {
            A = new PriorityQueue<>();      // 小顶堆，保存大数字
            B = new PriorityQueue<>((a, b) -> (b - a));     // 大顶堆，保存小数字
        }

        public void addNum(int num) {
            if (A.size() == B.size()) {
                B.add(num);
                A.add(B.poll());
            } else {
                A.add(num);
                B.add(A.poll());
            }
        }

        public double findMedian() {
            return A.size() == B.size()? (A.peek() + B.peek()) / 2.0: A.peek();
        }
    }

    public boolean verifyPostorder(int[] postorder) {
        return verify(postorder, 0, postorder.length - 1);
    }

    // 后续遍历：【左子树】【右子树】【根】
    private boolean verify(int[] postorder, int a, int b) {
        if (a >= b) {
            return true;
        }
        int i = a;  // 右子树的开始索引
        while (postorder[i] < postorder[b]) {
            i++;
        }
        int j = i;
        while (postorder[j] > postorder[b]) {   // 右子树所有节点都应该大于根节点
            j++;
        }
        return j == b && verify(postorder, a, i - 1) && verify(postorder, i, b);
    }

    int[] nums, tmp;
    public int reversePairs(int[] nums) {
        this.nums = nums;
        tmp = new int[nums.length];
        return mergeSort(0, nums.length);
    }

    private int mergeSort(int left, int right) {
        if (left >= right) {
            return 0;
        }
        int mid = (left + right) >> 1;
        int result = mergeSort(left, mid) + mergeSort(mid + 1, right);

        for (int k = left; k <= right; k++) {    // 复制数组
            this.tmp[k] = this.nums[k];
        }
        //开始合并
        int i = left;   // 左数组的索引
        int j = mid + 1;      // 右数组的索引
        for (int k = left; k <= right; k++) {
            if (i == mid + 1) {     // 左数组全部遍历完
                this.nums[k] = tmp[j++];
            } else if (j == right + 1 || tmp[i] <= tmp[j]){     // 右数组遍历完 || 左数组当前数字小于右数组当前数字，说明无逆序对
                this.nums[k] = tmp[i++];
            } else {    // 当前左数组数字大于当前右数组数字，说明索引从 i ~ mid之间的数字都大于右数组当前数字，都是逆序对
                this.nums[k] = tmp[j++];
                result += mid - i + 1;
            }
        }
        return result;
    }

    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j] + dp[j-1];
            }
        }
        return dp[n-1];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = obstacleGrid[0][0] == 1? 0: 1;       // 初始化
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    if (i > 0 && j > 0) {
                        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                    } else if (j > 0) {
                        dp[i][j] = dp[i][j-1];
                    } else if (i > 0) {
                        dp[i][j] = dp[i-1][j];
                    }
                }
            }
        }
        return dp[m-1][n-1];
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        int[] path = new int[n * m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0 && j > 0) {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
                } else if (i > 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else if (j > 0) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                }
            }
        }
        return dp[m-1][n-1];
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
        dp[0] = triangle.get(0).get(0);

        for (int i = 1; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                Integer value = triangle.get(i).get(j);
                if (j == 0) {
                    dp[j] = dp[j] + value;
                } else if (j == i) {
                    dp[j] = dp[j-1] + value;
                } else {
                    dp[j] = Math.min(dp[j-1], dp[j]) + value;
                }
            }
        }
        return Arrays.stream(dp).min().getAsInt();
    }



    public int minFallingPathSum(int[][] grid) {
        int MAX = Integer.MAX_VALUE;
        int n = grid.length;
        int[][] dp = new int[n][n];

        int m1 = -1;   // 最小值索引
        int m2 = -1;   // 次小值索引

        for (int i = 0; i < n; i++) {
            dp[0][i] = grid[0][i];
            if (grid[0][i] < (m1 == -1? MAX : dp[0][m1])) {      // 维护最小值和次小值
                m2 = m1;
                m1 = i;
            } else if (grid[0][i] < (m2 == -1? MAX: dp[0][m2])) {
                m2 = i;
            }
        }

        for (int i = 1; i < n; i++) {
            int m11 = -1;   // 当前行的最小值索引
            int m22 = -1;   // 当前行的次小值索引
            for (int j = 0; j < n; j++) {
                if (j == m1) {
                    dp[i][j] = dp[i-1][m2] + grid[i][j];
                } else {
                    dp[i][j] = dp[i-1][m1] + grid[i][j];
                }

                if (dp[i][j] < (m11 == -1? MAX : dp[i][m11])) {      // 维护最小值和次小值
                    m22 = m11;
                    m11 = j;
                } else if (dp[i][j] < (m22 == -1? MAX: dp[i][m22])) {
                    m22 = j;
                }
            }
            m1 = m11;
            m2 = m22;
        }
        return Arrays.stream(dp[n - 1]).min().getAsInt();
    }


    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        int n = locations.length;
        int[][] dp = new int[n][fuel + 1];       // dp[i][j] 表示从位置i开始，油量为j，到达目的地的路径数量
        for (int i = 0; i <= fuel; i++) {       // 初始化，当开始位置在终点的时候，总是有一条路径
            dp[finish][i] = 1;
        }

        for (int cur = 1; cur <= fuel; cur++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        int cost = Math.abs(locations[i] - locations[j]);
                        if (cost <= cur) {
                            dp[i][cur] += dp[j][cur - cost];
                            dp[i][cur] %= MOD;
                        }
                    }
                }
            }
        }
        return dp[start][fuel];
    }

    int N;
    int MAX_MOVE;
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        N = n;
        MAX_MOVE = maxMove;
        int[][] dp = new int[m * n][maxMove + 1];       // dp[i][j] 表示在位置i，可移动最大次数为j的时候，可以将球移出边界的路径数量

        for (int i = 0; i < m; i++) {   // 初始化边缘
            for (int j = 0; j < n; j++) {
                if (i == 0) add(dp, i, j);
                if (i == m-1) add(dp, i, j);
                if (j == 0) add(dp, i, j);
                if (j == n-1) add(dp, i, j);
            }
        }

        int[][] direction = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};     // 四个方向
        for (int step = 1; step <= maxMove; step++) {
            for (int k = 0; k < m * n; k++) {
                int[] array = parseIndex(k);
                int x = array[0], y = array[1];
                for (int i = 0; i < direction.length; i++) {
                    int xn = x + direction[i][0];
                    int yn = y + direction[i][1];
                    if (xn >= 0 && xn <m && yn >= 0 && yn < n) {
                        dp[k][step] += dp[getIndex(xn, yn)][step-1];
                        dp[k][step] %= MOD;
                    }
                }
            }
        }
        return dp[getIndex(startRow, startColumn)][maxMove];
    }

    private void add(int[][] dp, int x, int y) {
        int index = getIndex(x, y);
        for (int i = 1; i <= MAX_MOVE; i++) {
            dp[index][i]++;
        }
    }



    int n;
    int MIN = Integer.MIN_VALUE;
    public int[] pathsWithMaxScore(List<String> board) {
        n = board.size();

        char[][] charArray = new char[n][n];        // 将 List<String> 变为 char[][]
        for (int i = 0; i < n; i++) {
            charArray[i] = board.get(i).toCharArray();
        }

        int[] dp = new int[n * n];      // 所在位置的得分
        int[] cnt = new int[n * n];     // 所在位置最大得分的方案数量

        for (int i = n-1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {
                int index = getIndex(i, j);
                if (i == n-1 && j == n-1) {     // 起点
                    cnt[index] = 1;
                    continue;
                }
                if (charArray[i][j] == 'X') {      // 障碍
                    dp[index] = MIN;
                    continue;
                }

                int value = i == 0 && j == 0 ? 0 : charArray[i][j] - '0';
                int curPoint = MIN;   // 当前得分
                int curCnt = 0;     // 当前最大得分的方案数

                if (i + 1 < n) {    // 下边
                    int tmpPoint = dp[getIndex(i + 1, j)] + value;
                    int tmpCnt = cnt[getIndex(i + 1, j)];
                    int[] res = update(curPoint, curCnt, tmpPoint, tmpCnt);
                    curPoint = res[0];
                    curCnt = res[1];
                }
                if (j + 1 < n) {    // 右边
                    int tmpPoint = dp[getIndex(i, j + 1)] + value;
                    int tmpCnt = cnt[getIndex(i, j + 1)];
                    int[] res = update(curPoint, curCnt, tmpPoint, tmpCnt);
                    curPoint = res[0];
                    curCnt = res[1];
                }
                if (i + 1 < n && j + 1 < n) {    // 右下角
                    int tmpPoint = dp[getIndex(i + 1, j + 1)] + value;
                    int tmpCnt = cnt[getIndex(i + 1, j + 1)];
                    int[] res = update(curPoint, curCnt, tmpPoint, tmpCnt);
                    curPoint = res[0];
                    curCnt = res[1];
                }
                dp[index] = curPoint < 0? MIN: curPoint;
                cnt[index] = curCnt;
            }
        }
        int[] result = new int[2];  // 结果构造
        result[0] = dp[0] < MIN? 0: dp[0];
        result[1] = cnt[0];
        return result;
    }

    private int[] update(int curPoint, int curCnt, int tmpPoint, int tmpCnt) {
        int[] res = {curPoint, curCnt};
        if (tmpPoint > curPoint) {
            res[0] = tmpPoint;
            res[1] = tmpCnt;
        } else if (tmpPoint == curPoint) {
            res[1] += tmpCnt;
        }
        res[1] %= MOD;
        return res;
    }

    private int getIndex(int x, int y) {
        return x * n + y;
    }

    private int[] parseIndex(int index) {
        return new int[]{index / N, index % N};
    }

    public int[][] imageSmoother(int[][] img) {
        int n = img.length;
        int m = img[0].length;
        int[][] ans = new int[n][m];
        int[][] direction = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 0}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int sum = 0;
                int count = 0;
                for (int[] item : direction) {
                    int in = i + item[0];
                    int jn = j + item[1];
                    if (in >= 0 && in < n && jn >= 0 && jn < m) {
                        count++;
                        sum += img[in][jn];
                    }
                }
                ans[i][j] = sum / count;
            }
        }
        return ans;
    }

    public boolean hasAlternatingBits(int n) {
        int a = (n >> 1) ^ n;
        return ((a + 1) & a) == 0;
    }

    public List<Integer> selfDividingNumbers(int left, int right) {

        ArrayList<Integer> result = new ArrayList<>();
        step:for (int i = left; i <= right; i++) {
            int cur = i;
            while (cur != 0) {
                int a = cur % 10;
                if (a == 0 || i % a != 0) {
                    continue step;
                }
                cur /= 10;
            }
            result.add(i);
        }
        return result;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head;       // 快指针
        ListNode slow = head;       // 慢指针
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode mid = slow.next;
        slow.next = null;       // 从中间切断

        ListNode left = sortList(head);
        ListNode right = sortList(mid);

        return merge(left, right);
    }



    public void reorderList(ListNode head) {
        ListNode fast = head;   // 快指针
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode mid = slow.next;
        slow.next = null;   // 将链表切成两部分
        ListNode right = revert(mid);// 翻转
        head = merge(head, right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode head = left;
        ListNode cur = new ListNode(-1);
        while (left != null && right != null) {
            cur.next = left;
            left = left.next;
            cur.next.next = right;
            right = right.next;
            cur = cur.next.next;
        }
        return head;
    }

    private ListNode revert(ListNode node) {
        ListNode newHead = new ListNode();
        ListNode tmp = null;
        while (node != null) {
            tmp = node.next;
            node.next = newHead;
            newHead = node;
            node = tmp;
        }
        return newHead;
    }

    public String dayOfTheWeek(int day, int month, int year) {
        String[] week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30};

        int dayCount = 0;   // 累计的天数
        for (int i = 1971; i < year; i++) {     // 计算年份对应的天数
            dayCount += isLeapYear(i)? 366: 365;
        }

        for (int i = 1; i < month; i++) {       // 计算月份对应的天数
            dayCount += monthDays[i-1];
            if (i == 2 && isLeapYear(year)) {   // 二月份且是闰年
                dayCount++;
            }
        }
        dayCount += day;
        return week[(dayCount + 3) % 7];    // 1970最后一天是星期四
    }

    private boolean isLeapYear(int year) {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        int size = 0;   // 链表的长度
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            size++;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        cur = head;
        for (int i = 0; i < size / k; i++) {
            ListNode last = null;
            int count = k;
            while (count > 0) {
                ListNode tmp = cur.next;
                cur.next = last;
                last = cur;
                cur = tmp;
                count--;
            }
            ListNode end = pre.next;
            end.next = cur;
            pre.next = last;
            pre = end;
        }
        return dummy.next;
    }

    // 翻转 k 个节点
    private ListNode reverseK(ListNode head, int k) {
        ListNode newHead = null;
        ListNode cur = head;
        while (k > 0) {
            ListNode tmp = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = tmp;
            k--;
        }
        return newHead;
    }

    public int findTheWinner(int n, int k) {
        int pos = 0;
        for (int i = 1; i <= n; i++) {
            pos = (pos + k) % i;
        }
        return pos + 1;
    }


    HashSet<String> set;
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict);
        int length = s.length();
        boolean[] dp = new boolean[length + 1];
        dp[0] = true;
        for (int i = 1; i <= length; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[length];
    }

    int[][] graph;


    public boolean isAlienSorted(String[] words, String order) {
        int[] orderMap = new int[26];
        for (int i = 0; i < order.length(); i++) {
            orderMap[order.charAt(i) - 'a'] = i;
        }

        for (int i = 1; i < words.length; i++) {
            if (check(orderMap, words[i-1], words[i]) > 0) {
                return false;
            }
        }
        return true;
    }

    private int check(int[] orderMap, String a, String b) {
        int i = 0, j = 0;
        while (i < a.length() && j < b.length()) {
            int c1 = orderMap[a.charAt(i) - 'a'];
            int c2 = orderMap[b.charAt(j) - 'a'];
            if (c1 != c2) {
                return c1 - c2;
            }
            i++; j++;
        }
        if (i < a.length()) {
            return 1;
        } else if (j < b.length()) {
            return -1;
        }
        return 0;
    }

    public int minJumps(int[] arr) {
        // Key-值，Value-值对应的索引
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            List<Integer> list = map.computeIfAbsent(arr[i], t -> new ArrayList<>());
            list.add(i);
        }

        HashSet<Integer> visited = new HashSet<>();     // 已经遍历的索引
        Queue<int[]> list = new LinkedList<>();
        list.offer(new int[]{0, 0});     // 初始队列，{0, 0}表示从索引0开始，初始步数为0
        visited.add(0);
        while (!list.isEmpty()) {
            int[] pop = list.poll();
            int index = pop[0];
            int step = pop[1];
            if (index == length - 1) {
                return step;
            }

            step++;
            int value = arr[index];
            if (map.containsKey(value)) {
                for (Integer t : map.get(value)) {
                    if (visited.add(t)) {
                        list.offer(new int[]{t, step});
                    }
                }
                map.remove(value);
            }
            if (index < length - 1 && visited.add(index + 1)) {
                list.offer(new int[]{index + 1, step});
            }
            if (index > 0 && visited.add(index - 1)) {
                list.offer(new int[]{index - 1, step});
            }
        }
        return -1;
    }

    int edgeLen;
    int[] matchsticks;
    public boolean makesquare(int[] matchsticks) {
        int sum = Arrays.stream(matchsticks).sum();
        if (sum % 4 != 0) {
            return false;
        }
        edgeLen = sum >> 2;  // 边长
        this.matchsticks = matchsticks;
        Arrays.sort(matchsticks);
        return check(matchsticks.length-1, new int[4]);
    }

    private boolean check(int cur, int[] lines) {
        if (cur == -1) {
            if (lines[0] == lines[1] && lines[1] == lines[2] && lines[2] == lines[3]) {
                return true;
            }
            return false;
        }
        for (int i = 0; i < 4; i++) {
            if (cur == this.matchsticks.length && i > 0 || lines[i] + this.matchsticks[cur] > edgeLen || i > 0 && lines[i] == lines[i-1]) {
                continue;
            }

            lines[i] += this.matchsticks[cur];
            if (check(cur - 1, lines)) {
                return true;
            }
            lines[i] -= this.matchsticks[cur];
        }
        return false;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else {    // root 为要删除的节点
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            // 节点包含左子树和右子树
            TreeNode node = root.right;
            while (node.left != null) {
                node = node.left;
            }
            node.left = root.left;
            return root.right;
        }
        return root;
    }

    public int minEatingSpeed(int[] piles, int h) {
        int maxValue = Arrays.stream(piles).max().getAsInt();
        int left = 1, right = maxValue;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (calTime(piles, mid) > h) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    /** 计算在速度 speed 下，耗费的时间 */
    private int calTime(int[] piles, int speed) {
        int sum = 0;
        for (int pile : piles) {
            sum += (pile + speed - 1) / speed;
        }
        return sum;
    }

    public String[] uncommonFromSentences(String s1, String s2) {
        Arrays.asList();
        HashMap<String, Integer> map = new HashMap<>();
        count(s1, map);
        count(s2, map);

        return map.entrySet().stream()
                .filter(t -> t.getValue() == 1)
                .map(t -> t.getKey()).toArray(String[]::new);
    }

    private void count(String s, HashMap<String, Integer> map) {
        String[] array = s.split(" ");
        for (String item : array) {
            map.put(item, map.getOrDefault(item, 0) + 1);
        }
    }

    public int heightChecker(int[] heights) {
        int[] array = new int[101];
        for (int height : heights) {
            array[height]++;
        }

        int result = 0;
        int j = 0;      // heights 的索引
        for (int i = 0; i < array.length; i++) {
            while (array[i] > 0) {
                if (heights[j++] != i) {
                    result++;
                }
                array[i]--;
            }

        }
        return result;
    }

    public int numberOfMatches(int n) {
        int result = 0;
        while (n > 1) {
            if ((n & 1) == 1) {     // 奇数
                int k = (n - 1) / 2;
                result += k;
                n = k + 1;
            } else {    // 偶数
                int k = n / 2;
                result += k;
                n = k;
            }
        }
        return result;
    }

    public void duplicateZeros(int[] arr) {
        // 计算需要移动的最后一个数字的索引——i
        int n = arr.length;
        int i = 0, j = 0;
        while (j < n) {
            if (arr[i] == 0) ++j;
            ++i;
            ++j;
        }
        --i;    // i 回到最后一次合法的位置
        --j;    // j 同理，但 j 仍可能等于 n（例如输入 [0]）
        // 从后往前移动数据
        while (i >= 0) {
            if (j < n) arr[j] = arr[i];
            if (arr[i] == 0) arr[--j] = arr[i];
            --i;
            --j;
        }
    }

    public Node insert(Node head, int insertVal) {
        // 特殊判断：输入为 null
        if (head == null) {
            head = new Node(insertVal);
            head.next = head;
            return head;
        }
        // 特殊判断，所有数据值都相同
        Node tmp = head.next;
        while (tmp != head) {
            if (tmp.val != tmp.next.val) {
                break;
            }
            tmp = tmp.next;
        }
        if (tmp == head) {
            Node cur = new Node(insertVal, head.next);
            head.next = cur;
            return head;
        }

        // 寻找插入点
        Node p = head;      // p 为插入点
        while (true) {
            // 插入值不是最大值也不是最小值
            if (p.next.val >= insertVal && p.val <= insertVal) break;
            // 插入值是最大值
            if (insertVal > p.val && p.next.val < p.val) break;
            // 插入值是最小值
            if (p.val > p.next.val && insertVal < p.next.val) break;
            p = p.next;
        }
        Node cur = new Node(insertVal, p.next);
        p.next = cur;
        return head;
    }

    public int findBottomLeftValue(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        list.add(root);     // 初始化

        while (!list.isEmpty()) {
            List<TreeNode> nextList = new ArrayList<>();    // 下一层的节点
            for (TreeNode t : list) {
                if (t.left != null) nextList.add(t.left);
                if (t.right != null) nextList.add(t.right);
            }
            if (nextList.isEmpty()) {       // 下一层没有节点，说明list为最后一层
                break;
            }
            list = nextList;
        }
        return list.get(0).val;
    }

    public int findBottomLeftValue2(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        queue.push(root);
        TreeNode cur = null;
        while (!queue.isEmpty()) {
            cur = queue.pollLast();
            if (cur.right != null) {        // 先右后左
                queue.push(cur.right);
            }
            if (cur.left != null) {
                queue.push(cur.left);
            }
        }
        return cur.val;
    }

    public List<Integer> largestValues(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        List<Integer> resultList = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            int maxValue = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
                maxValue = Math.max(maxValue, node.val);
            }
            resultList.add(maxValue);
        }
        return resultList;
    }

    int MOD = 1_000_000_007;
    private static List<Integer> list = new ArrayList<>();      // 只放置质数数据
    private static int[] count = new int[101];      // count[i] 表示1~i的质数个数
    static {
        for (int i = 2; i <= 100; i++) {
            boolean flag = true;
            for (int j = 2; j * j < i; j++) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                list.add(i);
            }
            count[i] = list.size();
        }
    }

    // 1 <= n <= 100
    public int numPrimeArrangements(int n) {
        System.out.println(Arrays.toString(count));
        int prime = count[n];
        System.out.println(prime);
        int other = n - prime;

        long result = 1;
        for (int i = prime; i > 0; i--) {       // 质数的全排列
            result += result * i % MOD;
        }
        for (int i = other; i > 0; i--) {       // 非质数的全排列
            result += result * i % MOD;
        }
        return (int) result;
    }

    private final Map<String, List<Integer>> dictionary = new HashMap<>();     // 字典，保存计算的结果
    public List<Integer> diffWaysToCompute(String expression) {
        if (dictionary.containsKey(expression)) {
            return dictionary.get(expression);
        }
        List<Integer> result = new ArrayList<>();
        int index = 0;
        int num = 0;
        while (index < expression.length() && !isOperation(expression.charAt(index))) {
            num = num * 10 + expression.charAt(index) - '0';
            index++;
        }
        if (index == expression.length()) {     // expression为纯数字
            result.add(num);
            dictionary.put(expression, result);
            return result;
        }

        for (int i = 0; i < expression.length(); i++) {
            if (isOperation(expression.charAt(i))) {
                List<Integer> subList1 = diffWaysToCompute(expression.substring(i));
                List<Integer> subList2 = diffWaysToCompute(expression.substring(i + 1));
                for (Integer t1 : subList1) {
                    for (Integer t2 : subList2) {
                        result.add(calculate(t1, t2, expression.charAt(i)));
                    }
                }
            }
        }
        dictionary.put(expression, result);
        return result;
    }

    // 根据运算符计算
    private Integer calculate(Integer a, Integer b, char operation) {
        switch (operation) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
        }
        return -1;
    }

    // 判断当前字符是否是运算符
    private boolean isOperation(char c) {
        return c == '+' || c == '-' || c == '*';
    }

    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int result = -1;        // 结果
        int n = stations.length;    // 油站数量
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);    // 从大到小排序
        int remain = startFuel;   // 剩余油量
        int loc = 0;    // 位置
        int idx = 0;    // stations 的索引

        while (loc < target) {
            if (remain == 0) {
                if (!queue.isEmpty()) {
                    result++;
                    remain += queue.poll();
                } else {
                    return -1;
                }
            }
            loc += remain;
            remain = 0;
            while (idx < n && loc >= stations[idx][0]) {
                queue.add(stations[idx++][1]);
            }
        }
        return result;
    }

    public int countGoodRectangles(int[][] rectangles) {
        int maxLen = Integer.MIN_VALUE;     // 最大的正方形边长
        int count = 0;      // 矩形数量
        for (int[] rectangle : rectangles) {
            int edge = Math.min(rectangle[0], rectangle[1]);
            if (edge > maxLen) {
                maxLen = edge;
                count = 1;
            } else if (edge == maxLen) {
                count++;
            }
        }
        return count;
    }

    private int maxGoldSum = 0;

    int m;
    public int getMaximumGold(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] != 0) {
                    dfs(grid, i, j, 0);
                }
            }
        }
        return maxGoldSum;
    }

    private void dfs(int[][] grid, int row, int column, int gold) {
        int curGold = grid[row][column];
        gold += curGold;
        maxGoldSum = Math.max(maxGoldSum, gold);

        grid[row][column] = 0;
        for (int[] d : direction) {
            int nrow = row + d[0];
            int ncolumn = column + d[1];
            if (nrow > 0 && nrow < n && ncolumn > 0 && ncolumn < m && grid[nrow][ncolumn] != 0) {
                dfs(grid, nrow, ncolumn, gold);
            }
        }
        grid[row][column] = curGold;
    }

    public int minCostToMoveChips(int[] position) {
        // 总个数
        int n = position.length;
        // 偶数个数
        int evenCount = (int) Arrays.stream(position).filter(t -> (t & 1) == 0).count();
        // 奇数个数
        int oddCount = n - evenCount;

        return Math.min(evenCount, oddCount);
    }

    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new LinkedList<>();      // 栈
        for (int t : asteroids) {
            boolean ok = true;
            while (ok && !stack.isEmpty() && stack.peekLast() > 0 && t < 0) {
                int a = stack.peekLast();
                int b = -t;
                if (a <= b) stack.pollLast();
                if (a >= b) ok = false;
            }
            if (ok) stack.addLast(t);
        }
        return stack.stream().mapToInt(t -> t).toArray();
    }

    public List<String> simplifiedFractions(int n) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (gcd(i, j) == 1) {
                    list.add(j + "/" + i);
                }
            }
        }
        return list;
    }

    private int gcd(int a, int b) {
        return b != 0? gcd(b, a % b): a;
    }

    class MyCalendarTwo {

        private Map<Integer, Integer> map;

        public MyCalendarTwo() {
            map = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            map.put(start, map.getOrDefault(start, 0) + 1);
            map.put(end, map.getOrDefault(end, 0) - 1);

            int count = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                count += entry.getValue();
                if (count > 2) {
                    map.put(start, map.get(start) - 1);
                    map.put(end, map.get(end) - 1);
                    return false;
                }
            }
            return true;
        }
    }

    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.left != null || root.right != null) return root;
        return root.val == 0? null: root;
    }

    class CBTInserter {

        private TreeNode root;
        private Queue<TreeNode> queue;      // 保存可添加子节点的节点，即完全二叉树的最后一层或最后两层

        public CBTInserter(TreeNode root) {
            this.root = root;
            queue = new LinkedList<>();
            initQueue(root);
        }

        // 初始化队列
        private void initQueue(TreeNode root) {
            Queue<TreeNode> queueSearch = new LinkedList<>();
            queueSearch.offer(root);
            while (!queueSearch.isEmpty()) {
                TreeNode poll = queueSearch.poll();
                if (poll.left != null) queueSearch.offer(poll.left);
                if (poll.right != null) queueSearch.offer(poll.right);
                if (poll.right == null) {       // 包含了两种情况，一种是左右节点都是空，一种是左节点不为空，右节点是空
                    queue.offer(poll);
                }
            }
        }

        public int insert(int val) {
            TreeNode node = queue.peek();
            TreeNode tmp = new TreeNode(val);
            if (node.left == null) {
                node.left = tmp;
            } else if (node.right == null) {
                node.right = tmp;
                queue.poll();       // node 节点已无空余的位置
            }
            queue.offer(tmp);
            return node.val;
        }

        public TreeNode get_root() {
            return root;
        }
    }

    public String fractionAddition(String expression) {
        int n = expression.length();
        int a = 0;  // 分子
        int b = 1;  // 分母
        int i = 0;
        while (i < n) {
            int c = 0;      // 分子
            int d = 0;      // 分母
            boolean flag = true;    // 符号，默认是 +
            if (expression.charAt(i) == '+' || expression.charAt(i) == '-') {
                flag = expression.charAt(i) == '+'? true: false;
                i++;
            }
            // 获取分子
            while (i < n && Character.isDigit(expression.charAt(i))) {
                c = c * 10 + expression.charAt(i) - '0';
                i++;
            }
            // 忽略'/'
            i++;
            // 获取分母
            while (i < n && Character.isDigit(expression.charAt(i))) {
                d = d * 10 + expression.charAt(i) - '0';
                i++;
            }
            if (!flag) {
                c = -c;
            }
            // 计算
            a = a * d + c * b;
            b = b * d;
        }
        if (a == 0) {
            return "0/1";
        }
        int t = gcd(Math.abs(a), b);  // 最大公约数
        return a / t + "/" + b / t;
    }

    public int[] arrayRankTransform(int[] arr) {
        int[] clone = Arrays.copyOf(arr, arr.length);
        Arrays.sort(clone);

        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 1;
        for (int item : clone) {
            if (!map.containsKey(item)) {
                map.put(item, count++);
            }
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = map.get(arr[i]);
        }
        return arr;
    }

    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        HashSet<Integer> set = new HashSet<>();
        // 计算4个边长+2个对角线的长度
        set.add(calSqure(p1, p2));
        set.add(calSqure(p1, p3));
        set.add(calSqure(p1, p4));
        set.add(calSqure(p2, p3));
        set.add(calSqure(p2, p4));
        set.add(calSqure(p3, p4));

        // 边长不能为0 && 正方形4个边长度相等，2个对角线相等，即set的长度应该为2
        if (!set.contains(0) && set.size() == 2) {
            return true;
        }
        return false;
    }

    // 计算两个点的距离的平方
    private Integer calSqure(int[] a, int[] b) {
        return (b[0] - a[0]) * (b[0] - a[0]) + (b[1] - a[1]) * (b[1] - a[1]);
    }



    public int largestComponentSize(int[] nums) {
        int maxValue = Arrays.stream(nums).max().getAsInt();
        UnionFind2 unionFind = new UnionFind2(maxValue + 1);

        for (int num : nums) {
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    unionFind.union(num, i);
                    unionFind.union(num, num / i);
                }
            }
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int result = 0;
        for (int num : nums) {
            int root = unionFind.find(num);
            map.put(root, map.getOrDefault(root, 0) + 1);
            result = Math.max(result, map.get(root));
        }
        return result;
    }


    public class UnionFind {

        private int[] data;
        public int unionCount;     // 合并的次数

        public UnionFind(int n) {
            data = new int[n];
            for (int i = 0; i < n; i++) {
                data[i] = i;
            }
        }

        public int find(int x) {
            if (x == data[x]) return x;
            data[x] = find(data[x]);     //  路径压缩
            return data[x];
        }

        public void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA != rootB) {
                unionCount++;
                data[rootB] = rootA;
            }
        }

        public boolean isConnect(int a, int b) {
            return find(a) == find(b);
        }
    }


    private int[][] direction = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    public int numIslands(char[][] grid) {
        n = grid.length;
        m = grid[0].length;

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    bfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void bfs(char[][] grid, int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int a = poll[0];
            int b = poll[1];
            for (int i = 0; i < direction.length; i++) {
                int p = a + direction[i][0];
                int q = b + direction[i][1];
                if (p >= 0 && p < n && q >= 0 && q < m && grid[p][q] == '1') {
                    queue.offer(new int[]{p, q});
                    grid[p][q] = '0';
                }
            }
        }
    }

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int m = graph[0].length;

        UnionFind2 unionFind = new UnionFind2(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                if (unionFind.isConnect(i, graph[i][j])) {
                    return false;
                }
                unionFind.union(graph[i][0], graph[i][j]);
            }
        }
        return true;
    }

    public int longestConsecutive(int[] nums) {
        // Key: 数字      Value：该数字所在连续序列的最大值
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, num);
        }
        int res = 0;
        for (int num : nums) {
            if (!map.containsKey(num - 1)) {        // 连续序列的开头才进入
                Integer right = map.get(num);
                while (map.containsKey(right + 1)) {
                    right = map.get(right + 1);
                }
                map.put(num, right);
                res = Math.max(res, right - num + 1);       // 更新
            }
        }
        return res;
    }

    public int minimumEffortPath(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;

        // 保存的元素为长度为3的数组，[0]和[1]为两个方格标识，[2]为两个方格的高度差
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int id = i * m + j;
                if (i > 0) {
                    list.add(new int[]{id, id - m, Math.abs(heights[i][j] - heights[i - 1][j])});
                }
                if (j > 0) {
                    list.add(new int[]{id, id - 1, Math.abs(heights[i][j] - heights[i][j - 1])});
                }
            }
        }
        Collections.sort(list, Comparator.comparingInt(a -> a[2]));     // 根据高度差排序，从小到大
        UnionFind2 unionFind = new UnionFind2(n * m);
        int target = n * m - 1;     // 终点标识
        for (int[] item : list) {
            unionFind.union(item[0], item[1]);
            if (unionFind.isConnect(0, target)) {       // 起点和终点是否连通
                return item[2];
            }
        }
        return 0;
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int n = equations.size();
        HashMap<String, Integer> map = new HashMap<>(2 * n);
        UnionFind1 unionFind = new UnionFind1(2 * n);
        // 将变量编码为数字，映射关系保存在 map 中
        int id = 0;
        for (int i = 0; i < n; i++) {
            String var1 = equations.get(i).get(0);
            String var2 = equations.get(i).get(1);

            if (!map.containsKey(var1)) {
                map.put(var1, id++);
            }
            if (!map.containsKey(var2)) {
                map.put(var2, id++);
            }
            unionFind.union(map.get(var1), map.get(var2), values[i]);
        }

        n = queries.size();
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            Integer val1 = map.get(queries.get(i).get(0));
            Integer val2 = map.get(queries.get(i).get(1));
            if (val1 == null || val2 == null) {     // 不在 map 的变量，无答案
                result[i] = -1.0d;
            } else {
                result[i] = unionFind.cal(val1, val2);
            }
        }
        return result;
    }

    public class UnionFind1 {

        private int[] data;
        private double[] weight;   // 权重


        public UnionFind1(int n) {
            data = new int[n];
            weight = new double[n];
            for (int i = 0; i < n; i++) {
                data[i] = i;
                weight[i] = 1.0d;
            }
        }

        public int find(int x) {
            if (x == data[x]) return x;
            int origin = data[x];
            data[x] = find(data[x]);     //  路径压缩
            weight[x] *= weight[origin];
            return data[x];
        }

        public void union(int a, int b, double value) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA != rootB) {
                data[rootA] = rootB;
                weight[rootA] = value * weight[b] / weight[a];
            }
        }

        public double cal(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA != rootB) {
                return -1.0d;
            }
            return weight[a] / weight[b];
        }
    }

    class MyCircularQueue {

        int[] data;
        int head;   // 头指针
        int tail;   // 尾指针
        int n;      // 队列大小

        public MyCircularQueue(int k) {
            n = k + 1;
            data = new int[k + 1];      // 注意初始化的大小
            head = 0;
            tail = 0;
        }

        public boolean enQueue(int value) {
            if (this.isFull()) {
                return false;
            }
            data[tail] = value;
            tail = (tail + 1) % n;
            return true;
        }

        public boolean deQueue() {
            if (this.isEmpty()) {
                return false;
            }
            int value = data[head];
            head = (head + 1) % n;
            return true;
        }

        public int Front() {
            if (this.isEmpty()) {
                return -1;
            }
            return data[head];
        }

        public int Rear() {
            if (this.isEmpty()) {
                return -1;
            }
            return data[(tail - 1 + n) % n];
        }

        public boolean isEmpty() {
            return head == tail;
        }

        public boolean isFull() {
            return (tail + 1) % n == head;
        }
    }

    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        UnionFind2 unionFind = new UnionFind2(n);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (similar(strs[i], strs[j])) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.region;
    }

    // 判断两个字符串是否“相似”
    private boolean similar(String a, String b) {
        int count = 0;
        for (int i = 0; i < a.length(); i++) {      // a和b长度相同
            if (a.charAt(i) != b.charAt(i)) {
                count++;
            }
        }
        return count == 0 || count == 2;
    }

    public class UnionFind2 {

        private int[] data;
        public int region;     // 组的数量

        public UnionFind2(int n) {
            data = new int[n];
            region = n;     // 初始化每个元素自成一组
            for (int i = 0; i < n; i++) {
                data[i] = i;
            }
        }

        public int find(int x) {
            if (x == data[x]) return x;
            data[x] = find(data[x]);     //  路径压缩
            return data[x];
        }

        public void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA != rootB) {
                data[rootB] = rootA;
                region--;
            }
        }

        public boolean isConnect(int a, int b) {
            return find(a) == find(b);
        }
    }

    public List<Integer> minSubsequence(int[] nums) {
        Arrays.sort(nums);      // 排序
        int total = 0;      // 计算全部数据总和
        for (int num : nums) {
            total += num;
        }
        List<Integer> list = new ArrayList<>();     // 结果
        int index = nums.length-1;      // 索引
        int sum = 0;
        while (sum <= total) {
            total -= nums[index];
            sum += nums[index];
            list.add(nums[index]);
            index--;
        }
        return list;
    }

    // 11011000
    public String makeLargestSpecial(String s) {
        if (s.length() <= 2) {
            return s;
        }
        int left = 0;
        int count = 0;
        ArrayList<String> subList = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                count++;
            } else {
                count--;
                if (count == 0) {
                    subList.add("1" + makeLargestSpecial(s.substring(left + 1, i)) + "0");
                    left = i + 1;
                }
            }
        }
        Collections.sort(subList, Comparator.reverseOrder());
        StringBuilder sb = new StringBuilder();
        for (String str : subList) {
            sb.append(str);
        }
        return sb.toString();
    }

    public int arithmeticTriplets(int[] nums, int diff) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int res = 0;
        for (int num : nums) {
            if (set.contains(num - diff) && set.contains(num + diff)) {
                res++;
            }
        }
        return res;
    }

    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            List<Integer> list = map.computeIfAbsent(edge[0], a -> new ArrayList<>());
            list.add(edge[1]);
            list = map.computeIfAbsent(edge[1], a -> new ArrayList<>());
            list.add(edge[0]);
        }

        HashSet<Integer> set = new HashSet<>();
        for (int item : restricted) {
            set.add(item);
        }

        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        set.add(0);
        while (!queue.isEmpty()) {
            Integer cur = queue.poll();
            count++;
            List<Integer> subList = map.get(cur);
            if (subList.isEmpty()) {
                break;
            }

            for (Integer item : subList) {
                if (set.contains(item)) {
                    continue;
                }
                queue.offer(item);
                set.add(item);
            }
        }
        return count;
    }

    public boolean validPartition(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i < n; i++) {
            if (dp[i - 1] && nums[i-1] == nums[i] || i > 1 && dp[i-2]
                    && (nums[i] == nums[i-1] && nums[i] == nums[i-2] || nums[i] == nums[i-1] + 1 && nums[i] == nums[i-2] + 2)) {
                dp[i + 1] = true;
            }
        }
        return dp[n];
    }

    public int longestIdealString(String s, int k) {
        int[] word = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i) - 'a';
            for (int j = Math.max(0, ch - k); j <= Math.min(25, ch + k); j++) {
                word[ch] = Math.max(word[ch], word[j]);
            }
            word[ch]++;
        }
        return Arrays.stream(word).max().getAsInt();
    }

    public int minStartValue(int[] nums) {
        int n = nums.length;
        int[] sum = new int[n + 1];     // 前缀和
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        int minValue = Arrays.stream(sum).min().getAsInt();
        if (minValue >= 1) {
            return 1;
        }
        return 1 - minValue;
    }

    @Test
    public void test() {
        LeetCodeSolution lcs = new LeetCodeSolution();
        int[] array = {1,-2,-3};
        int[][] array2 = {{0,1},{1,2},{3,1},{4,0},{0,5},{5,6}};
        List<String> list = Arrays.asList("E23", "2X2", "12S");
        int r = lcs.minStartValue(array);
        System.out.println(r);

    }
}