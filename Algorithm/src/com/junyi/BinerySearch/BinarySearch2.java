package com.junyi.BinerySearch;

/**
 * User: JY
 * Date: 2018/12/26 0026
 * Description: 查找第一个值大于给定值的数组位置
 */
public class BinarySearch2 {
    public int binarySearch(int[] array, int value){
        int left = 0;
        int right = array.length;
        while ( left <= right){
            int mid = left + ((right - left) >> 1);
            if (array[mid] <= value)
                left = mid + 1;
            else if (array[mid] > value && array[mid-1] <= value)
                return mid;
            else right = mid - 1;
        }
        return -1;
    }
    public static void main(String[] args) {
        BinarySearch2 bs = new BinarySearch2();
        int[] array = {0, 0, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 9, 9, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29};
        int value = 8;
        int index = bs.binarySearch(array, value);
        System.out.println(index);
        System.out.println(array[index]);
    }
}
