package com.junyi.StringMatch;

import java.util.ArrayList;

/**
 * User: JY
 * Date: 2019/3/7 0007
 * Description: 字符串匹配算法BF，暴力法
 * 时间复杂度：O(n*m) n是主串的长度，m是模式串的长度
 */
public class StringMatchingBF {
    private static ArrayList<Integer> search(String main, String sub) {
        ArrayList<Integer> result = new ArrayList<>();
        int length = main.length()-sub.length()+1;
        int subLength = sub.length();
        for(int i = 0; i < length; i++) {
            int j = 0;
            for (; j < subLength; j++) {
                if (main.charAt(i+j) != sub.charAt(j)) {
                    break;
                }
            }
            if (j == subLength) {
                result.add(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String stringMain = "aabaaaaabbaabaac";
        String stringSub = "ba";
        ArrayList<Integer> result = StringMatchingBF.search(stringMain, stringSub);
        System.out.println(result.toString());

    }
}
