package com.junyi.DataStructure;

public class Queue {

    private class ArrayQueue {  //顺序队列
        private String[] queue;
        private int n;
        private int head = 0;
        private int tail = 0;
        public ArrayQueue(int capacity){
            queue = new String[capacity];
            n = capacity;
        }
        public boolean enqueue(String item){
            if (tail == n) {    //队列尾指针已经指到了最后
                if (head == 0) return false;    //队列满了
                for (int i = head; i < tail; i++){  //将数据往前挪，空出后面的位置
                    queue[i-head] = queue[i];
                }
                head = 0;
                tail -= head;
            }
            queue[tail] = item;
            tail++;
            return true;
        }
        public String dequeue(){
            if (head == tail) return null;
            String tmp = queue[head];
            head++;
            return tmp;
        }
    }
    private class CircularQueue{    //循环队列
        private String[] queue;
        private int n;
        private int head = 0;
        private int tail = 0;
        public CircularQueue(int capacity){
            queue = new String[capacity];
            n = capacity;
        }
        public boolean enqueue(String item){    //入队列
            if ((tail+1)%n == head) return false;
            queue[tail] = item;
            tail = (tail+1)%n;
            return true;
        }
        public String dequeue(){
            if (head == tail) return null;
            String tmp = queue[head];
            head = (head+1)%n;
            return tmp;
        }
    }
    private class BlockingQueue{ // 阻塞队列
        /*
        生产者-消费者模型
        在队列为空的时候，从队头取数据会被阻塞。因为此时还没有数据可取，直到队列中有了数据才能返回；如果队列已经满了，那么插入数据的操作就会被阻塞，直到队列中有空闲位置后再插入数据，然后再返回。
         */
    }
    private class ConcurrentQueue { //并发队列
        /*
        线程安全的队列，最简单的方法是在enqueue和dequeue方法上加锁
        基于数组的循环队列，利用CAS原子操作，可以实现非常高效地并发队列
         */
    }

    public static void main(String[] args) {

    }
}
