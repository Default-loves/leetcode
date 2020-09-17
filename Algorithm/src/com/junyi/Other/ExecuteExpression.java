package com.junyi.Other;

import java.util.HashMap;
import java.util.Stack;

/**
 * User: JY
 * Date: 2018/12/22 0022
 * Description: 对算术表达式进行计算
 * TODO 有问题的算法
 */
public class ExecuteExpression {
    private static HashMap<String, Integer> hashMap;
    public ExecuteExpression(){
        hashMap = new HashMap<>();
        hashMap.put("+", 1);
        hashMap.put("-", 1);
        hashMap.put("*", 2);
        hashMap.put("/", 2);
    }

    public static int execute(String s){
        Stack numberStack = new Stack<>();
        Stack operatorStack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (48 <= s.charAt(i) && s.charAt(i) <= 57 )
                numberStack.push(s.charAt(i));
            else {
                char c = (char) operatorStack.peek();
                if (hashMap.get(s.charAt(i)) > hashMap.get(c))
                    operatorStack.push(s.charAt(i));
                else {
                    int number1 = (int) numberStack.pop();
                    int number2 = (int) numberStack.pop();
                    char op = (char) operatorStack.pop();
                    int result = cal(number2, number1, op);
                    numberStack.push(result);
                    operatorStack.push(s.charAt(i));
                }
            }
        }
        return 0;
    }

    private static Integer cal(int number1, int number2, char op) {
        if (op == '+') return number1 + number2;
        if (op == '-') return number1 - number2;
        if (op == '*') return number1 * number2;
        if (op == '/') return number1 / number2;
        return null;
    }

    public static void main(String[] args) {
        String string = "3+2*6/3-1";
        int result;
        result = (int) execute(string);
        System.out.println(result);
    }
}
