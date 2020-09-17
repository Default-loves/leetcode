package com.junyi.DynamicProgramming;

/**
 * User: JY
 * Date: 2019/3/10 0010
 * Description:有一个二维数组，里面的值表示了到达该点所需要的消耗，求从左上角到右下角，最少的消耗是多少，每次移动只能向下或者向右
 * 递归的方法
 */
public class DPLefeUPtoRightDown2 {
    private int[][] data = {{1, 3, 5, 9},
                            {2, 1, 3, 4},
                            {5, 2, 6, 7},
                            {6, 8, 4, 3}};
    private int n = 4;
    private int[][] mem = new int[n][n];    // 备忘录，用于方法二

    public int minDistDPM2() {
        // 方法二
        System.out.println("Method Two: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mem[i][j] = -1;
            }
        }
        int res = minDist(n-1, n-1);
        printTrace(mem,n-1,n-1);
        return res;
    }

    private int minDist(int i, int j) {
        // 递归方法，从右下角开始
        if (i == 0 && j==0) return data[0][0];
        if (mem[i][j] > 0) return mem[i][j];
        int minLeft = Integer.MAX_VALUE;
        if (j-1 >= 0) {
            minLeft = minDist(i, j-1);
        }
        int minUp = Integer.MAX_VALUE;
        if (i-1 >= 0) {
            minUp = minDist(i-1, j);
        }
        int currMinDist = data[i][j] + Math.min(minLeft, minUp);
        mem[i][j] = currMinDist;
        return currMinDist;
    }

    private void printTrace(int[][] state, int i, int j) {
        if (i == 0 && j == 0) return;
        if (i > 0 && state[i-1][j] + data[i][j] == state[i][j]) {
            printTrace(state, i-1, j);
            System.out.printf("(%d,%d) -> (%d,%d)\n", i-1,j,i,j);
        } else {
            printTrace(state, i, j-1);
            System.out.printf("(%d,%d) -> (%d,%d)\n", i,j-1,i,j);
        }
    }

    public static void main(String[] args) {
        DPLefeUPtoRightDown2 dp = new DPLefeUPtoRightDown2();
        System.out.println(dp.minDistDPM2());
    }

}
