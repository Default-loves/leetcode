package com.junyi;


public class LeetCodeSolution {



    public void fib(int N) {
        System.out.println("通常的方法：" + normal(N));
        System.out.println("快速幂方法：" + fast(N-1));
    }

    public int normal(int N) {
        int a = 0, b = 1;
        for (int i = 0; i < N; i++) {
            int tmp = b;
            b = a + b;
            a = tmp;
        }
        return a;
    }


    public int fast(int N) {
        int[][] constantMatrix = new int[][]{{1, 1}, {1, 0}};   // 常量矩阵
        int[][] result = new int[][]{{1, 0}, {0, 1}};       // 初始化为单元矩阵
        while (N > 0) {
            if ((N & 1) == 1) {
                result = cal(result, constantMatrix);
            }
            constantMatrix = cal(constantMatrix, constantMatrix);
            N = N >> 1;
        }

        int[][] A0 = new int[][]{{1}, {0}};     // 起始条件
        return cal(result, A0)[0][0];
    }

    /** 计算两个矩阵的乘积 */
    public int[][] cal(int[][] A, int[][] B) {
        int n = A.length;
        int m = B[0].length;
        int[][] result = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < B.length; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }

    public static void main(String[] argv) {
        LeetCodeSolution lcs = new LeetCodeSolution();
        int[] array = new int[]{1,2,3,4,5,6,1};
        int[][] A = new int[][]{
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };
        int[][] B = new int[][]{
                {1, 1},
                {0, 2},
                {1, 0}
        };

        lcs.fib(10);
    }
}




