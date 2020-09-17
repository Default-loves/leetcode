package com.junyi.BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * User: JY
 * Date: 2019/1/9 0009
 * Description: 田忌赛马
 */
public class TianQiCompete {
    public static HashMap<String, Double> qiHorseTime = new HashMap<String, Double>(){
        {
            put("qi1", 1.0);
            put("qi2", 2.0);
            put("qi3", 3.0);
        }
    };
    public static HashMap<String, Double> tianHorseTime = new HashMap<String, Double>(){
        {
            put("tian1", 1.5);
            put("tian2", 2.5);
            put("tian3", 3.5);
        }
    };
    public static ArrayList<String> qiHorse = new ArrayList<>(Arrays.asList("qi1", "qi2", "qi3"));

    public void permutation(ArrayList<String> horse, ArrayList<String> result){
        if (horse.size() == 0){
            System.out.println(result);
            compare(result, qiHorse);
            return;
        }
        for (int i = 0; i < horse.size(); i++){
            ArrayList<String> newResult = (ArrayList<String>) result.clone();
            newResult.add(horse.get(i));
            ArrayList<String> newHorse = (ArrayList<String>) horse.clone();
            newHorse.remove(i);
            permutation(newHorse, newResult);
        }
    }
    private void compare(ArrayList<String> a, ArrayList<String> b) {
        int count = 0;
        for (int i = 0; i < a.size(); i++){
            if (tianHorseTime.get(a.get(i)) < qiHorseTime.get(b.get(i)))
                count += 1;
        }
        if (count > a.size()/2) System.out.println("Tian win~");
        else System.out.println("Qi win~");
    }

    public static void main(String[] args) {
        ArrayList<String> horse = new ArrayList<>(Arrays.asList("tian1", "tian2", "tian3"));
        TianQiCompete compete = new TianQiCompete();
        compete.permutation(horse, new ArrayList<>());
    }
}
