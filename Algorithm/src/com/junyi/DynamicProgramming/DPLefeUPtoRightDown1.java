package com.junyi.DynamicProgramming;

/**
 * User: JY
 * Date: 2019/3/10 0010
 * Description: 有一个二维数组，里面的值表示了到达该点所需要的消耗，求从左上角到右下角，最少的消耗是多少，每次移动只能向下或者向右
 * 动态规划
 */
public class DPLefeUPtoRightDown1 {
    private int[][] data = {{1, 3, 5, 9},
                            {2, 1, 3, 4},
                            {5, 2, 6, 7},
                            {6, 8, 4, 3}};
    private int n = 4;

    public int minDistDPM1() {
        //方法一
        System.out.println("Method One: ");
        int[][] state = new int[n][n];
        int tmp = 0;
        for (int i = 0; i < n; i++) {   //初始化第一行
            tmp += data[0][i];
            state[0][i] = tmp;
        }
        tmp = 0;
        for (int i = 0; i < n; i++) {   //初始化第一列
            tmp += data[i][0];
            state[i][0] = tmp;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                state[i][j] = data[i][j] + Math.min(state[i-1][j], state[i][j-1]);
            }
        }
        printTrace(state,n-1,n-1);
        return state[n-1][n-1];
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
        DPLefeUPtoRightDown1 dp = new DPLefeUPtoRightDown1();
        System.out.println(dp.minDistDPM1());
    }

}
