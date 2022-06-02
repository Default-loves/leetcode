package com.junyi.sort;

import java.util.Arrays;

/**
 * User: JY
 * Date: 2018/12/24 0024
 * Description: 选择排序
 */
public class SelectSort {
    public void selectSort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex] ) minIndex = j;
            }
            if (minIndex != i) {    // 对于大致已经有序的数组，该判断能够减少不必要的交换操作
                swap(arr, i, minIndex);
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] array = new int[] {4,4,6,5,3,2,8,1,5,6,0,10};
        SelectSort ss = new SelectSort();
        ss.selectSort(array);
        System.out.println(Arrays.toString(array));
    }
}
