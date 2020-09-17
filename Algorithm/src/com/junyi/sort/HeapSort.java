package com.junyi.sort;

import java.util.Arrays;

/**
 * User: JY
 * Date: 2019/1/21 0021
 * Description: 堆排序
 */
/*
为什么快速排序在实际中比堆排序要好?
1. 对于快速排序，数据是顺序访问的，而堆排序，数据是跳着访问的，堆排序对CPU缓存不友好
2. 堆排序的数据交换次数比快速排序要多
* */
public class HeapSort {
    // n 表示数据的个数，数组 heap 中的数据从下标 1 到 n 的位置。
    public int[] sort(int[] a) {
        int[] arr = new int[a.length+1];    //重新弄一个数组保存数据，数据从下标1开始
        for (int i = 0; i< a.length; i++) {
            arr[i+1] = a[i];
        }
        int n = arr.length-1;
        buildHeap(arr, n);
        int k = n;
        while (k > 1) {
            swap(arr, 1, k);
            --k;
            down(arr, k, 1);
        }
        return arr;
    }
    private void buildHeap(int[] arr, int n) {
        for (int i = n/2; i > 0; --i)
            down(arr, n, i);
    }
    private void down(int[] arr, int n, int i) {
        while (true) {
            int maxPos = i;
            if (i*2 <= n && arr[i] < arr[i*2]) maxPos = i*2;
            if (i*2+1 <= n && arr[maxPos] < arr[i*2+1]) maxPos = i*2+1;
            if (maxPos == i) break;
            swap(arr, i, maxPos);
            i = maxPos;
        }
    }

    public void swap(int[] a, int p, int q){
        int temp = a[p];
        a[p] = a[q];
        a[q] = temp;
    }

    public static void main(String[] args) {
        int[] array = new int[] {11,4,6,5,3,2,8,1,5,6,0,10};
        HeapSort heap = new HeapSort();
        array = heap.sort(array);
        System.out.println(Arrays.toString(array));
    }

}
