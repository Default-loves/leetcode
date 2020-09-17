package com.junyi.Interesting;

import java.util.Arrays;

/**
 * User: JY
 * Date: 2019/4/17 0017
 * Description: 实现对数组shuffle的功能
 */
public class Shuffle {
    public void shuffle(int[] array) {
        for (int i = array.length-1; i >= 0; i--) {
            int rIndex = (int) Math.floor(Math.random() * i);
            swap(array, rIndex, i);
        }
    }
    private void swap(int[] array, int p, int q) {
        int tmp = array[p];
        array[p] = array[q];
        array[q] = tmp;
    }

    public static void main(String[] args) {
        Shuffle s = new Shuffle();
        int[] array = {1,2,3,4,5,6,7,8,9,10};
        s.shuffle(array);
        System.out.println(Arrays.toString(array));
        s.shuffle(array);
        System.out.println(Arrays.toString(array));

    }
}
