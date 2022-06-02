package com.junyi.sort;

import java.util.*;

/*
    快速排序非递归方法，即使用栈
    将需要排序的左右下标压栈
 */
public class QuickSortStack {
    public static void quickSort(int[] arr){
        Deque<Integer> stack = new ArrayDeque<>();  // 右在下，左在上
        stack.push(arr.length - 1);
        stack.push(0);
        while (!stack.isEmpty()) {
            Integer left = stack.pop();
            Integer right = stack.pop();
            if (left >= right) {
                continue;
            }
            median3(arr, left, right);
            int pivot = partition(arr, left, right);
            if (pivot > left) {
                stack.push(pivot - 1);
                stack.push(left);
            }
            if (pivot < right) {
                stack.push(right);
                stack.push(pivot + 1);
            }
        }
    }
    // 将left, center, right下标三个数中，大小居中者放到left下标处
    private static void median3(int[] arr, int l, int r) {
        int c = l + (r - l) / 2;
        if (arr[l] > arr[c]) swap(arr, l, c); // 左中，大者居中
        if (arr[c] > arr[r]) swap(arr, c, r); // 中右，大者居右，此时最大者居右
        if (arr[c] > arr[l]) swap(arr, l, c); // 左中，大者居左，此时中者居左
    }

    public static void random(int[] arr, int left, int right){
        //随机在范围内取一个数
        Random random = new Random();
        int i = random.nextInt(right-left) + left;
        swap(arr, i, left);
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
    public static void main(String[] argv){
        int[] array1 = new int[] {95,94,91,98,99,90,99,93,91,92};
        int[] array2 = new int[] {3,5};
        int[] array3 = new int[] {4,4,6,5,3,2,8,1,5,6,0,10};
        int[] array4 = new int[] {3,2,1};
        int[] array5 = new int[] {95,94,91,98,89,90,99,93,81,92};
        int[] array = array1;
        quickSort(array);
        System.out.println(Arrays.toString(array));
    }
}
