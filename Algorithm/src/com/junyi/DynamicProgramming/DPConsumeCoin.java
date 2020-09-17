package com.junyi.DynamicProgramming;

/**
 * User: JY
 * Date: 2019/3/11 0011
 * Description:我们有1元，3元，5元的硬币，需要支付9元，问最少需要多少个硬币？
 */
public class DPConsumeCoin {

    public int minCoin(int money) {
        int state[] = new int[money+1];
        for (int i = 0; i < state.length; i++) {
            state[i] = -1;
        }
        state[1] = 1;
        state[2] = 2;
        state[3] = 1;
        state[4] = 2;
        state[5] = 1;
        for (int i = 6; i < state.length; i++) {
            state[i] = 1 + min3(state[i-1], state[i-3], state[i-5]);
        }
        return state[money];
    }
    private int min3(int a, int b, int c) {
        int tmp = a < b ? a : b;
        return tmp < c ? tmp : c;
    }

    public static void main(String[] args) {
        DPConsumeCoin dp = new DPConsumeCoin();
        System.out.println(dp.minCoin(9));
        System.out.println(dp.minCoin(15));
        System.out.println(dp.minCoin(14));
    }
}
