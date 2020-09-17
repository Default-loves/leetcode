package com.junyi.BackTracking;
/*
    总金额10元，纸币的面额有1,2,5,10元
    问总共有多少种组合？
 */
import java.util.ArrayList;

public class AllKindinTotalMoney {
    public static long[] rewards = {1, 2, 5, 10};
    private static int totalNum = 0;

    public static void getResult(long totalMoney, ArrayList<Long> result){
        if (totalMoney == 0){
            System.out.println(result);
            totalNum++;
        }
        else if (totalMoney < 0)
            return;
        else{
            for(long reward: rewards){
                ArrayList<Long> newResult = (ArrayList<Long>) result.clone();
                newResult.add(reward);
                getResult(totalMoney-reward, newResult);
            }
        }

    }
    public static void main(String[] argv){
        long totalMoney = 10;
        getResult(totalMoney, new ArrayList<>());
        System.out.println("Total number: " + totalNum);
    }
}
