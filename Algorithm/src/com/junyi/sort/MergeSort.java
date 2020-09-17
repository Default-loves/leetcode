package com.junyi.sort;

import java.util.Arrays;

/*
    Merge sort
    1. 是稳定的排序，不是原地排序
    2. 空间复杂度是O(n)
    3. 时间复杂度是O(nlogn)
        设T(n)为n个元素进行归并排序的时间，那么T(n)=2*T(n/2)+n=2^k*T(n/2^k)+k*n, T(1)=C,
        让T(n/2^k)=T(1), 得到k=log2n，带入上式中得到T(n)=Cn+nlog2n

 */
public class MergeSort {
    public static int[] mergeSort(int[] arr){
        if (arr == null)
            return new int[0];
        if (arr.length == 1)
            return arr;
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        left = mergeSort(left);
        right = mergeSort(right);
        return merge(left, right);

    }
    public static int[] merge(int[] arr1, int[] arr2){
        if (arr1 == null)
            arr1 = new int[0];
        if (arr2 == null)
            arr2 = new int[0];
        int[] result = new int[arr1.length + arr2.length];
        int i = 0, p = 0, q = 0;
        while (p < arr1.length && q < arr2.length){
            if (arr1[p] < arr2[q])
                result[i] = arr1[p++];
            else result[i] = arr2[q++];
            i++;
        }
        while (p < arr1.length){
            result[i++] = arr1[p++];
        }
        while (q < arr2.length){
            result[i++] = arr2[q++];
        }
        return result;
    }

    public static void main(String[] argv){
        int[] array1 = new int[] {95,94,91,98,99,90,99,93,91,92};
        int[] result = mergeSort(array1);
        System.out.println(Arrays.toString(result));
    }
}
