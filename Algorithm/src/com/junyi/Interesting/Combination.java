package com.junyi.Interesting;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * User: JY
 * Date: 2019/1/9 0009
 * Description: 计算一组数组的组合个数
 */
public class Combination {
    public void combineALL(ArrayList<Integer> array){
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for (Integer item : array){
            ArrayList<ArrayList<Integer>> temp = (ArrayList<ArrayList<Integer>>) result.clone();
            for (ArrayList<Integer> list: result){
                ArrayList<Integer> newList = (ArrayList<Integer>) list.clone();
                newList.add(item);
                temp.add(newList);
            }
            result = temp;
        }
        System.out.println(result);
        System.out.println(result.size());
    }
    public static void main(String[] args) {
        Combination cn = new Combination();
        ArrayList<Integer> data = new ArrayList<>(Arrays.asList(1,2,3,4));
        cn.combineALL(data);
    }
}
