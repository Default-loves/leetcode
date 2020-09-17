package com.junyi;

/**
 * User: JY
 * Date: 2018/12/27 0027
 * Description: 使用位来操作
 */
public class UseBitToCal {
    public void calOddEven(){   //奇偶数判断
        //使用“&”进行判断
        long startTime, endTime;
        int odd = 0, even = 0;
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
             if ( (i & 1) == 0)
                 even++;
             else
                 odd++;
        }
        endTime = System.currentTimeMillis();
        System.out.println("Use &:");
        System.out.println(endTime - startTime);
        System.out.println(String.valueOf(odd) + '|' + String.valueOf(even));

        //使用“%”进行判断
        odd = 0;
        even = 0;
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            if ( i % 2 == 0)
                even++;
            else
                odd++;
        }
        endTime = System.currentTimeMillis();
        System.out.println("Use %:");
        System.out.println(endTime - startTime);
        System.out.println(String.valueOf(odd) + '|' + String.valueOf(even));
    }

    //交换两个数
    public void exchangeNumber(){
        int a = 100;
        int b = 200;
        a = a^b;
        b = a^b;
        a = a^b;
        System.out.println("A:" + a);
        System.out.println("B:" + b);
    }

    public static void main(String[] args) {
        UseBitToCal uc = new UseBitToCal();
        uc.calOddEven();
//        uc.exchangeNumber();
    }
}
