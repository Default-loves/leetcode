package com.junyi.sort;

import java.util.Arrays;

/**
 * User: JY
 * Date: 2018/12/24 0024
 * Description:插入排序
 */
public class InsertSort {
    public void insertSort(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int value = arr[i];
            int j = i;
            for (; j > 0; j--) {
                if (arr[j-1] > value)
                    arr[j] = arr[j-1];
                else break;
            }
            arr[j] = value;
        }
    }
    public static void main(String[] args) {
        int[] array = new int[] {4,4,6,5,3,2,8,1,5,6,0,10};
        InsertSort is = new InsertSort();
        is.insertSort(array);
        System.out.println(Arrays.toString(array));
    }
}
