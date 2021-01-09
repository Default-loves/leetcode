package com.junyi;

import javafx.util.Pair;
import org.junit.platform.commons.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.incrementExact;
import static java.lang.Math.max;
import static java.lang.Math.min;



public class LeetCodeSolution {

    public List<Integer> splitIntoFibonacci(String S) {
        ArrayList<Integer> res = new ArrayList<>();
        doSplit(res, S, 0, S.length(), 0, 0);
        return res;
    }

    /**
     *
     * @param res：最后的结果
     * @param source：源字符串, 不会改变
     * @param index：当前的字符串索引
     * @param length：源字符串的长度
     * @param sum：切分结果最后两个元素的和
     * @param prev：切分结果最后一个元素的值
     */
    private boolean doSplit(ArrayList<Integer> res, String source, int index, int length, int sum, int prev) {
        if (index == length) {
            return res.size() >= 3;
        }
        long cnt = 0;
        for (int i = index; i < length; i++) {
             if (index < i && source.charAt(index) == '0') {   // 对于开头是“0”的子串，不再处理
                 break;
             }
             cnt = cnt * 10 + (source.charAt(i) - '0');
             if (cnt > Integer.MAX_VALUE) {     // 每个子串的大小不大于2^31 - 1
                 break;
             }
             int cur = (int) cnt;
             if (res.size() >= 2) {
                 if (cur < sum) {
                     continue;
                 } else if (cur > sum) {    // 累加的值已经大于切分结果最后两个元素的和，后续不再处理
                     break;
                 }
             }
             res.add(cur);      // 添加进 res 的数符合 cur == sum
             if (doSplit(res, source, i+1, length, prev + cur, cur)) {
                 return true;
             } else {
                 res.remove(res.size()-1);
             }
        }
        return false;
    }


    public static void main(String[] argv) {
        LeetCodeSolution lcs = new LeetCodeSolution();
        String[] arr = new String[]{"What","must","be","acknowledgment","shall","be"};
        List<Integer> res = lcs.splitIntoFibonacci("123456579");
        System.out.println(res);
    }
}




