package com.junyi.BinerySearch;

/**
 * User: JY
 * Date: 2018/12/26 0026
 * Description: 存在重复元素，查找第一个值等于给定值的元素
 */
public class BinarySearch1 {
    public int binarySearch(int[] array, int value){
        int left = 0;
        int right = array.length;
        while ( left <= right ){    //小于等于
            int mid = left + ((right - left) >> 1);
            if (array[mid] < value)
                left = mid + 1;
            else if (array[mid] > value)
                right = mid - 1;
            else if (mid == 0 || array[mid-1] != value)     //相等的时候，如果前面没有相同的值，那么就是所要的结果，如果有相等的值，那么right = mid-1;
                return mid;
            else
                right = mid-1;
        }
        return -1;
    }
    public static void main(String[] args) {
        BinarySearch1 bs = new BinarySearch1();
        int[] array = {0, 0, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 9, 9, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29};
        int value = 0;
        int result = bs.binarySearch(array, value);
        System.out.println(result);
    }
}
