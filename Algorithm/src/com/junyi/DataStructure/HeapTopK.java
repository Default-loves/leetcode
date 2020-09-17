package com.junyi.DataStructure;

import java.util.Arrays;

/**
 * User: JY
 * Date: 2019/1/21 0021
 * Description: use Heap to cal Top K，构建小顶堆
 */

public class HeapTopK {
    public int[] heap;  //从下标1开始存储数据
    public Integer n;   //堆的容量
    public Integer count = 0;   //当前堆中的数量
    public HeapTopK(int capacity){
        this.n = capacity;
        heap = new int[capacity+1];
    }

    public void insert(int data){
        if (count < n){
            heap[++count] = data;
            up(heap, count);
        }
        else if (heap[1] < data){
            heap[1] = data;
            down(heap, count, 1);
        }
    }
    public Integer removeMin(){
        if (count == 0) return null;
        int min = heap[1];
        swap(heap, count--, 1);
        down(heap, count, 1);
        return min;
    }
    public void up(int[] data, int k){
        while ( k/2 > 0 && data[k/2] > data[k]){
            swap(data, k/2, k);
            k = k/2;
        }
    }
    public void swap(int[] data, int p, int q){
        int temp = data[p];
        data[p] = data[q];
        data[q] = temp;
    }
    public void down(int[] data, int n, int k){
        while ( true ){
            int maxInd = k;
            if (k*2 <= n && data[k*2] < data[maxInd]) maxInd = k*2;
            if (k*2+1 <= n && data[maxInd] > data[k*2+1]) maxInd = k*2+1;
            if (maxInd == k) break;
            swap(data, maxInd, k);
            k = maxInd;
        }
    }
    public String toString() {
        return Arrays.toString(heap);
    }

    public static void main(String[] args) {
        int[] array = new int[] {11,4,6,5,3,2,8,1,5,6,0,10};
        int k = 5;
        HeapTopK heap = new HeapTopK(k);
        for ( int data : array)
            heap.insert(data);
        System.out.println(heap);
    }
}
