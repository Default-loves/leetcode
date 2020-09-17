package com.junyi.sort;

/**
 * 桶排序
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class BucketSort {
    public static double[] bucketSort(double[] array){
        // 获取最值
        double min = array[0];
        double max = array[0];
        for (double element: array) {
            if (min > element)
                min = element;
            if (max < element)
                max = element;
        }
        double d = max - min;
        // 创建桶并初始化桶
        int bucketLength = array.length;
        ArrayList<LinkedList<Double>> bucket = new ArrayList<LinkedList<Double>>(bucketLength);
        for (int i = 0; i < bucketLength; i++)
            bucket.add(new LinkedList<>());
        // 遍历源数据，插入相应的桶
        for (double item : array){
            int num = (int) ((item-min) / d * ( bucketLength -1));
            bucket.get(num).add(item);
        }
        // 桶内数据排序
        for (LinkedList<Double> element : bucket ){
            Collections.sort(element);
        }
        // 构建结果
        double[] sortedData = new double[array.length];
        int i = 0;
        for (LinkedList<Double> list : bucket)
            for (double element: list)
                sortedData[i++] = element;
        return sortedData;
    }

    public static void main(String[] argv){
        double[] data = new double[] {4.12,6.421,0.0023,3.0,2.123,8.122,4.12, 10.09};
        double[] result = bucketSort(data);
        System.out.println(Arrays.toString(result));
    }
}
