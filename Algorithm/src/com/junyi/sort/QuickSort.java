package com.junyi.sort;

import java.util.Arrays;
import java.util.Random;

/*
    快速排序
    1. 不稳定的排序，原地排序
    2. 关于轴的选择，可以用随机法，也可以左中右三个数选择居中的数字
 */
public class QuickSort {
    public static void quickSort(int[] arr, int left, int right){
        int center;
        if (left < right){
            center = random_partition(arr, left, right);
            quickSort(arr, left, center-1);
            quickSort(arr, center+1, right);
        }
    }

    public static int random_partition(int[] arr, int left, int right){
        //随机在范围内取一个数
        Random random = new Random();
        int i = random.nextInt(right-left) + left;
        swap(arr, i, left);
        return partition(arr,left,right);
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[left];
        int i = left;
        int j = right;
        while (true){
            while (arr[j] > pivot && i < j) j--;    // j start first,can't i
            while (arr[i] <= pivot && i < j) i++;
            if (i >= j)
                break;
            swap(arr, i, j);
        }
        arr[left] = arr[j];
        arr[j] = pivot;
        return j;

    }

    public static void swap(int[] arr, int p, int q) {
        int tmp = arr[p];
        arr[p] = arr[q];
        arr[q] = tmp;
    }

    // 将left, center, right下标三个数中，大小居中者放到left下标处
    private void median3(int[]arr, int l, int r) {
        int c = l + (r - l) / 2;
        if (arr[l] > arr[c]) swap(arr, l, c); // 左中，大者居中
        if (arr[c] > arr[r]) swap(arr, c, r); // 中右，大者居右，此时最大者居右
        if (arr[c] > arr[l]) swap(arr, l, c); // 左中，大者居左，此时中者居左
    }

    public static void main(String[] argv){
        int[] array1 = new int[] {95,94,91,98,99,90,99,93,91,92};
        int[] array2 = new int[] {3,5};
        int[] array3 = new int[] {4,4,6,5,3,2,8,1,5,6,0,10};
        int[] array4 = new int[] {3,2,1};
        int[] array5 = new int[] {95,94,91,98,89,90,99,93,81,92};
        int[] array = array1;
        quickSort(array,0,array.length-1);
        System.out.println(Arrays.toString(array));
    }
}
