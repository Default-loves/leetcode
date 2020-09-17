package com.junyi.BackTracking;

/**
 * User: JY
 * Date: 2019/3/8 0008
 * Description: 01背包问题、回溯法
 */
public class BTPackage0_1 {

    private int maxW = Integer.MIN_VALUE; // 结果放到 maxW 中
    private int[] weight = {2, 2, 4, 6, 3};  // 物品重量
    private int n = 5; // 物品个数
    private int w = 9; // 背包承受的最大重量
    private boolean[][] mem = new boolean[5][10]; // 备忘录，默认值 false，使用的情况下f函数调用29次，如果不是用备忘录，那么f函数会调用45次

    public void f(int i, int cw) {
        if (cw == w || i == n) { // cw==w 表示装满了，i==n 表示物品都考察完了
            if (cw > maxW)
                maxW = cw;
                return;
        }
        if (mem[i][cw]) return; // 重复状态
        mem[i][cw] = true; // 记录 (i, cw) 这个状态
        f(i+1, cw); // 选择不装第 i 个物品
        if (cw + weight[i] <= w) {
            f(i+1,cw + weight[i]); // 选择装第 i 个物品
        }
    }


public static void main(String[] args) {
        BTPackage0_1 p = new BTPackage0_1();
        p.f(0, 0);
        System.out.println(p.maxW);
    }
}
