package com.junyi.DynamicProgramming;

/**
 * User: JY
 * Date: 2019/3/8 0008
 * Description: 01背包问题、只考虑了重量，没有考虑物品的价值
 */
public class DPPackage01_0 {
    private int maxW = Integer.MIN_VALUE; // 结果放到 maxW 中
    private int[] weight = {2, 2, 4, 6, 3};  // 物品重量
    private int n = 5; // 物品个数
    private int w = 9; // 背包承受的最大重量

    public Integer f() {
        boolean[] states = new boolean[w+1];
        states[0] = true;
        states[weight[0]] = true;
        for (int i = 1; i < n; i++) {
            for (int j = w-weight[i]; j >= 0; j--) {    //需要从后面往前循环，否则会重复计算
                if (states[j] == true) {
                    states[j+weight[i]] = true;
                }
            }
        }
        for (int i = w; i >= 0; i--) {
            if (states[i] == true) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        DPPackage01_0 p = new DPPackage01_0();
        System.out.println(p.f());

    }
}
