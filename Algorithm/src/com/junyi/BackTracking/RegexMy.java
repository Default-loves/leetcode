package com.junyi.BackTracking;

/**
 * User: JY
 * Date: 2019/4/14 0014
 * Description: 正则表达式，回溯算法
 */
public class RegexMy {
    public static class Pattern {
        private char[] pattern;
        private int plen;
        private boolean flag;

        public Pattern(char[] pattern) {
            this.pattern = pattern;
            this.plen = pattern.length;
        }
        public boolean match(char[] text) {
            flag = false;
            rmatch(0, 0, text, text.length);
            return flag;
        }
        private void rmatch(int pi, int tj, char[] text, int tlen) {
            if (flag == true) {
                return;
            }
            if (pi == plen) {
                if (tj == tlen) {
                    flag = true;
                }
                return;
            }
            if (pattern[pi] == '*') {   //匹配0次或多次
                for (int k = 0; k < tlen-tj; k++) {
                    rmatch(pi+1, tj+k, text, tlen);
                }
            } else if (pattern[pi] == '?') {   //匹配0次或1次
                rmatch(pi+1, tj, text, tlen);
                rmatch(pi+1, tj+1, text, tlen);
            } else if (tj < tlen && text[tj] == pattern[pi]) {
                rmatch(pi+1, tj+1, text, tlen);
            }
        }
    }

    public static void main(String[] args) {
        Pattern p = new Pattern("abc*heap".toCharArray());
        System.out.println(p.match("abcda".toCharArray()));
        System.out.println(p.match("abc".toCharArray()));
        System.out.println(p.match("abcabca".toCharArray()));
        System.out.println(p.match("abcd".toCharArray()));
        System.out.println(p.match("abc_a".toCharArray()));
    }
}
