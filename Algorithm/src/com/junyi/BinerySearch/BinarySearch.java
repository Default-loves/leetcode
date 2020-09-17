package com.junyi.BinerySearch;
/**
 *  简单二分查找
 */
public class BinarySearch {
    public int run(int[] array, int value) {
        int left = 0;
        int right = array.length-1;
        int center;
        while (left <= right) {
            center = left + ((right - left) >> 1);
            if (array[center] == value) return center;
            else if (array[center] < value) left = center + 1;
            else right = center-1;
        }
        return -1;
    }
    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        int[] array = {0, 0, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 9, 9, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29};
        int value = 29;
        int index = bs.run(array, value);
        System.out.println(index);
        System.out.println(array[index]);

    }
}
