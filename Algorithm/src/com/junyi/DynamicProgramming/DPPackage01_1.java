package com.junyi.DynamicProgramming;

/**
 * User: JY
 * Date: 2019/3/8 0008
 * Description: 01背包问题，考虑物品的价值
 */
public class DPPackage01_1 {
    public int maxW = Integer.MIN_VALUE; // 结果放到 maxW 中
    private int[] weight = {2, 2, 4, 6, 3};  // 物品重量
    private int[] value = {3, 4, 8, 9, 6};  //物品的价值
    private int n = 5; // 物品个数
    private int w = 9; // 背包承受的最大重量

    public Integer f() {
        int[] states = new int[w+1];
        for (int i = 0; i < states.length; i++) {
            states[i] = -1;
        }
        states[0] = 0;  //初始化数据
        states[weight[0]] = value[0];
        for (int i = 1; i < n; i++) {
            for (int j = w-weight[i]; j >= 0; j--) {
                if (states[j] >= 0){
                    int v = states[j] + value[i];
                    if (v > states[j+weight[i]]) {
                        states[j+weight[i]] = v;
                    }
                }
            }
        }
        for (int i = 0; i <= w; i++) {  //查找结果
            if (maxW < states[i]) {
                maxW = states[i];
            }
        }
        return maxW;
    }

    public static void main(String[] args) {
        DPPackage01_1 p = new DPPackage01_1();
        System.out.println(p.f());
    }
}
