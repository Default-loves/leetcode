package com.junyi.sort;

import java.util.Arrays;

/**
 * User: JY
 * Date: 2018/12/24 0024
 * Description: 冒泡排序
 */
public class BubbleSort {
    public void bubbleSort(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            boolean bChange = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j+1]){
                    bChange = true;
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            if (!bChange) break;
        }
    }
    public static void main(String[] args) {
        int[] array = new int[] {4,4,6,5,3,2,8,1,5,6,0,10};
        BubbleSort bs = new BubbleSort();
        bs.bubbleSort(array);
        System.out.println(Arrays.toString(array));

    }
}
