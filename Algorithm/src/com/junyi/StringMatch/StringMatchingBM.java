package com.junyi.StringMatch;

import java.util.ArrayList;

/**
 * User: JY
 * Date: 2019/3/7 0007
 * Description: 字符串匹配算法BM(Boyer-Moore)
 * 时间复杂度：
 */
public class StringMatchingBM {
    //TODO Have Bug
    private static int SIZE = 256;
    private static ArrayList<Integer> search(String main, String sub) {
        int n = main.length();
        int m = sub.length();
        int[] bc = new int[SIZE];
        generateBC(sub, m, bc);
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < n-m+1;) {
            int j;
            for (j = m-1; j >=0; j--) {
                if (main.charAt(i+j) != sub.charAt(j)) break;
            }
            if (j < 0) {
                result.add(i);
                i += 1;
            } else {
                i += (j-bc[(int)main.charAt(i+j)]);
            }
        }
        return result;
    }
    private static void generateBC(String a, int m, int[] bc) {
        for (int i = 0; i < SIZE; i++) {
            bc[i] = -1;
        }
        for (int i = 0; i < m; i++) {
            int ascii = (int) a.charAt(i);
            bc[ascii] = i;
        }
    }


    public static void main(String[] args) {
        String stringMain = "abaaaaabbaabaac";
        String stringSub = "ba";
        ArrayList<Integer> result = StringMatchingBM.search(stringMain, stringSub);
        System.out.println(result.toString());
    }

}
