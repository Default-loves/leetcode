package com.junyi.BinerySearch;

/**
 * User: JY
 * Date: 2018/12/26 0026
 * Description: 查找第一个值小于等于给定值的数组位置
        */
public class BinarySearch3 {
    public int binarySearch(int[] array, int value){
        int left = 0;
        int right = array.length-1;
        while ( left <= right){
            int mid = left + ((right - left) >> 1);
            if (array[mid] > value)
                right = mid - 1;
            else if (mid == array.length-1 || array[mid+1] >= value)
                return mid;
            else left = mid + 1;
        }
        return -1;
    }
    public static void main(String[] args) {
        BinarySearch3 bs = new BinarySearch3();
        int[] array = {0, 0, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 9, 9, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29};
        int value = 31;
        int index = bs.binarySearch(array, value);
        System.out.println(index);
        System.out.println(array[index]);
    }
}
