package com.junyi.sort;

import java.util.Arrays;

/**
 * User: JY
 * Date: 2018/12/24 0024
 * Description: 冒泡排序
 */
public class BubbleSort {

    /** 如果某一轮次没有交换操作，则说明数组已经有序，可提前退出，无需进行后续的轮次 */
    public void bubbleSort(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            boolean change = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j+1]){
                    change = true;
                    swap(arr, j, j+1);
                }
            }
            if (!change) break;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    /** 在上面基础之上，记录一个轮次最后交换的位置，下一轮次只需要遍历到最后交换的位置 */
    public void bubbleSort2(int[] arr){
        int lastIndex = arr.length - 1;     // 最后交换的位置索引
        boolean change = true;      // 是否有交换操作
        while (change) {
            change = false;
            int swapIndex = -1;
            for (int i = 0; i < lastIndex; i++) {
                if (arr[i] > arr[i+1]) {
                    swap(arr, i, i+1);
                    change = true;
                    swapIndex = i;
                }
            }
            lastIndex = swapIndex;
        }
    }
    public static void main(String[] args) {
        int[] array = new int[] {4,4,6,5,3,2,8,1,5,6,0,10};
        BubbleSort bs = new BubbleSort();
        bs.bubbleSort2(array);
        System.out.println(Arrays.toString(array));

    }
}
