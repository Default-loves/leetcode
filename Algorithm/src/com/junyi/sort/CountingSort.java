package com.junyi.sort;

import java.util.Arrays;

/**
 * 计数排序
 */
public class CountingSort {
    public static void countSort(int [] array){
        int max = array[0];
        int min = array[0];
        for( int i = 0; i < array.length; i++){
            if( max < array[i] )
                max = array[i];
            if( min > array[i] )
                min = array[i];
        }
        int d = max - min;
        int[] countArray = new int[d+1];
        //放置数据到相应的位置
        for( int i = 0; i < array.length; i++)
            countArray[array[i]-min]++;
        //累加
        for( int i = 1; i < countArray.length; i++){
            countArray[i] = countArray[i] + countArray[i-1];
        }
        int[] sortedCount = new int[array.length];
        // 从后面开始，才能保证计数排序是稳定排序
        for( int i = array.length -1 ; i >= 0; i--){
            sortedCount[countArray[array[i]-min]-1] = array[i];
            countArray[array[i]-min]--;
        }
        for (int i = 0; i < sortedCount.length; i++) {
            array[i] = sortedCount[i];
        }
    }
    public static void main(String[] args){
        int[] array = new int[] {4,4,6,5,3,2,8,1,5,6,0,10};
        int[] array1 = new int[] {95,94,91,98,99,90,99,93,91,92};
        countSort(array);
        System.out.println(Arrays.toString(array));
    }
}


