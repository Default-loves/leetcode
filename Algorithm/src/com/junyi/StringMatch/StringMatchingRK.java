package com.junyi.StringMatch;

import java.util.ArrayList;

/**
 * User: JY
 * Date: 2019/3/7 0007
 * Description: RK(Rabin-Karp)算法
 * 时间复杂度：O(n)，n是主串的长度
 * 包括了两部分，第一部分是计算每个子串的hash值，为O(n)，第二部分是将每个子串和模式串进行比较，比较n-m+1次，每次的时间复杂度是O(1),所以这部分的时间复杂度为O(n-m+1)
 */
public class StringMatchingRK {
    private static ArrayList<Integer> search(String main, String sub) {
        int m = main.length()-sub.length()+1;
        int n = sub.length();
        int[] preData = new int[n];     //将26的0~m-1次幂提前计算
        preData[0] = 1;
        for (int i = 1; i < n; i++) {
            preData[i] = preData[i-1]*26;
        }
        int[] h = new int[m];
        int tmp = 0;    //计算第一个hash值
        for (int i = n-1, j = 0; i >= 0; i--, j++) {
            tmp += preData[i] * (main.charAt(j) - 'a');
        }
        h[0] = tmp;
        for (int i = 1; i < m; i++) {  //计算后面的hash值
            h[i] = (h[i-1] - preData[n-1] * (main.charAt(i-1) - 'a')) * 26 + (main.charAt(i+n-1) - 'a');
        }
        int subHash = 0;    //计算模式串的hash值
        for (int i = n-1; i >= 0; i--) {
            subHash += preData[i] * (sub.charAt(n-i-1) - 'a');
        }
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            if (h[i] == subHash) {
                result.add(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String stringMain = "aabaaaaabbaabaac";
        String stringSub = "ba";
        ArrayList<Integer> result = StringMatchingRK.search(stringMain, stringSub);
        System.out.println(result.toString());
    }


}
