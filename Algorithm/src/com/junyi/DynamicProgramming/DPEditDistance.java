package com.junyi.DynamicProgramming;

/**
 * User: JY
 * Date: 2019/1/16 0016
 * Description: 编辑距离
 * 应用场景：适合用于搜索引擎中的自动纠错
 * 针对纠错效果不好的问题，我们可以有的优化思路：
 * 1、根据编辑距离计算Top10个，根据搜索热门程度选择最佳的；
 * 2、根据2,3个计算编辑距离的方法，取交集；
 * 3、分析用户的搜索日志，得到用户容易拼错的单词列表；
 * 4、分析用户的搜索日志，得到用户的常用的搜索关键字
 *
 */

public class DPEditDistance {
    public Integer calEditDistance1(String a, String b){
        /**
         *  莱文斯坦距离(Levenshtein Distance)
         *  其距离的大小表示了两个字符串差异的大小
         *  考虑增加、删除、替换字符
         */
        if (a == null || b == null) return -1;
        int[][] d = new int[a.length()][b.length()];
        for (int i = 0; i < b.length(); i++) {  //初始化第一行
            if (a.charAt(0) == b.charAt(i)) d[0][i] = i;
            else if (i != 0) d[0][i] = d[0][i-1] +1;
            else d[0][i] = 1;
        }
        for (int i = 0; i < a.length(); i++) {  //初始化第一列
            if (b.charAt(0) == a.charAt(i)) d[i][0] = i;
            else if (i != 0) d[i][0] = d[i][0] +1;
            else d[i][0] = 1;
        }

        for (int i = 1; i < a.length(); i++) {
            for (int j = 1; j < b.length(); j++) {
                int r = a.charAt(i) == b.charAt(j) ? 0 : 1;
                d[i][j] = tripleMin(d[i - 1][j - 1] + r, d[i - 1][j] + 1, d[i][j - 1] + 1);
            }
        }
        return d[a.length()-1][b.length()-1];
    }

    public Integer calEditDistance2(String a, String b){
        /**
         * 最长公共子串(Longest Common substring length)
         * 表示两个字符串相似程度的大小
         * 只考虑了增加和删除字符
         */
        if (a == null || b == null) return -1;
        int[][] d = new int[a.length()][b.length()];
        for (int i = 0; i < b.length(); i++) {
            if (a.charAt(0) == b.charAt(i)) d[0][i] = 1;
            else if (i != 0) d[0][i] = d[0][i-1];
            else d[0][i] = 0;
        }
        for (int i = 0; i < a.length(); i++) {
            if (b.charAt(0) == a.charAt(i)) d[i][0] = 1;
            else if (i != 0) d[i][0] = d[i][0];
            else d[i][0] = 0;
        }

        for (int i = 1; i < a.length(); i++) {
            for (int j = 1; j < b.length(); j++) {
                int r = a.charAt(i) == b.charAt(j) ? 1 : 0;
                d[i][j] = tripleMax(d[i - 1][j - 1] + r, d[i - 1][j], d[i][j - 1]);
            }
        }
        return d[a.length()-1][b.length()-1];
    }

    private Integer tripleMin(Integer a, Integer b, Integer c){
        int min = Math.min(a, b);
        return Math.min(min, c);
    }
    private int tripleMax(int a, int b, int c) {
        int tmp = a > b ? a : b;
        return tmp > c ? tmp : c;
    }

    public static void main(String[] args) {
        DPEditDistance ed = new DPEditDistance();
        String s1 = "mouse";
        String s2 = "llmoousee";
        System.out.println(ed.calEditDistance1(s1, s2));
        System.out.println(ed.calEditDistance2(s1, s2));
    }
}
