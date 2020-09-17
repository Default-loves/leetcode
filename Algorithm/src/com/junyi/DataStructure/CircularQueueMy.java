package com.junyi.DataStructure;

/**
 * User: JY
 * Date: 2018/12/24 0024
 * Description: 循环队列
 */
public class CircularQueueMy {
    // 空：head == tail
    // 满：(tail + 1) % n == head
    private int[] queue;
    private int n;
    private int size = 0;
    private int head = 0;
    private int tail = 0;
    public CircularQueueMy(int capacity){
        queue = new int[capacity];
        n = capacity;
    }

    public boolean push(int item){
        if ((tail+1)%n == head) return false;
        queue[tail] = item;
        size++;
        tail = (tail + 1) % n;
        return true;
    }

    public int pop(){
        if (head == tail) return Integer.parseInt(null);
        int item = queue[head];
        size--;
        head = (head + 1) % n;
        return item;
    }
    public int getNumber(){
        return size;
    }

    public static void main(String[] args) {
        CircularQueueMy queue = new CircularQueueMy(3);
        queue.push(1);
        queue.push(2);
        queue.push(3);
        System.out.println(queue.getNumber());
        System.out.println(queue.pop());
        queue.push(4);
        queue.push(5);
    }
}
