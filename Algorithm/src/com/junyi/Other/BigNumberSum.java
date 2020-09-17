package com.junyi.Other;
/*
    两个大整数相加
 */

public class BigNumberSum {
    private static int subCapacity = 9; //每个数组元素存储的数据位数

    private static String bigNumberSum(String bigNumberA, String bigNumberB){
        int maxLength = bigNumberA.length() > bigNumberB.length() ? bigNumberA.length() : bigNumberB.length();
        maxLength = maxLength/subCapacity + 1;
        int[] intsA = splitString(bigNumberA);
        int[] intsB = splitString(bigNumberB);
        int[] result = new int[maxLength];
        //计算结果
        for (int i = 0; i<maxLength; i++){
            result[i] += intsA[i] + intsB[i];
            if (result[i] >= Math.pow(10,subCapacity)){ //存在进位
                result[i] -= Math.pow(10,subCapacity);
                result[i+1] = 1;
            }
        }
        //将结果数组转换为字符串
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for (int i = maxLength-1; i>=0; i--){
            if (!flag){
                if (result[i] == 0)
                    continue;
                flag = true;
            }
            sb.append(result[i]);
        }
        return sb.toString();
    }
    private static int[] splitString(String string){
        // 将字符串从末尾开始按照subCapacity长度切分成数组
        int end = string.length();
        int start = string.length() > subCapacity ? end-subCapacity : 0;
        int resultSize = string.length()/subCapacity + 1;
        int[] result = new int[resultSize];
        for (int i = 0; i<resultSize; i++){
            result[i] = Integer.valueOf(string.substring(start,end));
            end = start;
            start = start - subCapacity > 0 ? start-subCapacity : 0;
        }
        return result;
    }
    public static void main(String[] argv){
        String string = bigNumberSum("426709752318","95481253129");
        System.out.println(string);

    }
}
