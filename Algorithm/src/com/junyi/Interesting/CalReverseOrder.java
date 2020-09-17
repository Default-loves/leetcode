package com.junyi.Interesting;

/**
 * 使用分治算法思想，计算数组逆序对
 * 将数组分为A和B两部分，逆序对等于A的逆序对+B的逆序对+A和B之间的逆序对
 */
public class CalReverseOrder {
    private int num;
    public int count(int[] array) {
        num = 0;
        mergeSortCount(array, 0, array.length-1);
        return num;
    }
    public void mergeSortCount(int[] array, int p, int r) {
        if (p >= r) return;
        int q = (p+r)/2;
        mergeSortCount(array, p, q);
        mergeSortCount(array, q+1, r);
        merge(array, p, q, r);
    }
    public void merge(int[] array, int p, int q, int r) {
        int[] tmp = new int[r-p+1];
        int i = p, j = q+1, k = 0;
        while ( i <= q && j <= r) {
            if (array[i] < array[j]){
                tmp[k++] = array[i++];
            } else {
                num += (q-i+1);
                tmp[k++] = array[j++];
            }
        }
        while (i <= q) {
            tmp[k++] = array[i++];
        }
        while (j <= r) {
            tmp[k++] = array[j++];
        }
        for ( i = 0; i<=r-p; i++) {
            array[p+i] = tmp[i];
        }
    }

    public static void main(String[] args) {
        int[] array1 = new int[] {1,9,4,7,2,8,3};
        int[] array2 = new int[] {1,3,2};
        int[] array3 = new int[] {3,1};
        int[] array = array2;
        CalReverseOrder divisionTreatment = new CalReverseOrder();
        int result = divisionTreatment.count(array);
        System.out.println(result);
    }

}
