package com.junyi.DataStructure;

/**
 * User: JY
 * Date: 2018/12/28 0028
 * Description: 跳跃表
 */

public class SkipListMy {
    private static final Integer MAX_LEVEL = 16;    //跳表包括数据和索引总共有16层，数据在第一层，索引从第二层开始
    private Node head;
    private int count;
    private int curMaxLevel;
    public SkipListMy() {
        this.head = new Node(-1, MAX_LEVEL);
        this.curMaxLevel = 1;
        this.count = 0;
    }
    class Node{
        public Integer value;
        public Node[] next;
        public Node(int val, int level) {
            this.value = val;
            next = new Node[level];
        }
    }

    public void insert(int value) {
        int level = getLevel();
        Node newNode = new Node(value, level);
        Node tmp = this.head;
        for (int i = level-1; i >= 0; i--) {
            while (tmp.next[i] != null && tmp.next[i].value < value) {
                tmp = tmp.next[i];
            }
            newNode.next[i] = tmp.next[i];
            tmp.next[i] = newNode;
        }
        if (curMaxLevel < level) {
            curMaxLevel = level;
        }
        count++;
    }
    public void delete(int value) {
        Node tmp = this.head;
        for (int i = curMaxLevel-1; i >= 0; i--) {
            while (tmp.next[i] != null && tmp.next[i].value < value) {
                tmp = tmp.next[i];
            }
            if (tmp.next[i].value == value) {
                tmp.next[i] = tmp.next[i].next[i];
            }
        }
        System.out.println("Delete value: " + value);
    }

    public void find(int value) {
        Node tmp = this.head;
        for (int i = curMaxLevel-1; i >= 0; i--) {
             while (tmp.next[i] != null && tmp.next[i].value < value) {
                 tmp = tmp.next[i];
             }
        }
        if (tmp.next[0].value == value) {
            System.out.printf("Value %d is inside", value);
        } else {
            System.out.printf("Fail! Value %d is not inside", value);
        }
        System.out.println();
    }

    public int getLevel() {
        //理论来讲，一级索引中元素个数应该占原始数据的 50%，二级索引中元素个数占 25%，三级索引12.5% ，一直到最顶层。
        // 因为这里每一层的晋升概率是 50%。对于每一个新插入的节点，都需要调用 getLevel  生成一个合理的层数。
        // getLevel 方法会随机生成 1~MAX_LEVEL 之间的数，且 ：
        //        50%的概率返回 2
        //        25%的概率返回 3
        //      12.5%的概率返回 4 ...
        int level = 1;
        while (level <= MAX_LEVEL && Math.random() < 0.5f) {
            level++;
        }
        return level;
    }

    public void printAll(){
        Node tmp = this.head.next[0];
        while (tmp != null) {
            System.out.print(tmp.value);
            System.out.print(" , ");
            tmp = tmp.next[0];
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29};
        SkipListMy sl = new SkipListMy();
        for (int item : array) {
            sl.insert(item);
        }
        sl.printAll();
        sl.find(1);
        sl.find(29);
        sl.find(8);
        sl.delete(7);
        sl.find(7);
        sl.insert(7);
        sl.find(7);
        sl.printAll();
    }
}