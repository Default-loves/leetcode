package com.junyi.DataStructure;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/**
 * 可以获取最小值的栈
 */
public class MinStack {
    private List<Integer> stack = new ArrayList<>();
    private List<Integer> min = new ArrayList<>();  //存放的是索引

    public void push(int num){
        stack.add(num);
        if( min.size() == 0 )
            min.add(0);
        else{
            int minNum = getMin();
            if( minNum > num )
                min.add(stack.size()-1);
        }
    }
    public int pop(){
        if( stack.isEmpty() )
            throw new EmptyStackException();
        int popIndex = stack.size()-1;
        int minIndex = min.get(min.size()-1);
        if( popIndex == minIndex )
            min.remove(min.size()-1);
        return stack.remove(stack.size()-1);
    }
    public int getMin(){
        if( min.size() == 0 )
            throw new EmptyStackException();
        int minIndex = min.get(min.size()-1);
        return stack.get(minIndex);
    }

    public static void main(String[] args){
        MinStack minStack = new MinStack();
        minStack.push(2);
        minStack.push(3);
        minStack.push(1);
        minStack.push(8);
        System.out.println(minStack.getMin());
        System.out.println("Pop: " + minStack.pop());
        System.out.println("Pop: " + minStack.pop());
        System.out.println(minStack.getMin());
    }
}
