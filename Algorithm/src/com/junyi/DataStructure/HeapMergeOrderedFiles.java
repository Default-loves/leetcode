package com.junyi.DataStructure;

import java.util.*;

/**
 * User: JY
 * Date: 2019/1/21 0021
 * Description: 合并有序小文件，使用堆
 */

public class HeapMergeOrderedFiles {
    public static final ArrayList<Integer> file1 = new ArrayList<>(Arrays.asList(22, 98));
    public static final ArrayList<Integer> file2 = new ArrayList<>(Arrays.asList(2, 6, 80, 89));
    public static final ArrayList<Integer> file3 = new ArrayList<>(Arrays.asList(1, 10, 15));
    public static final Integer numFile = 3;
    public static HashMap<String, Boolean> hasData = new HashMap<>();
    public static class Node{
        Integer value;
        String source;
        public Node(int value, String source){
            this.value = value;
            this.source = source;
        }
    }
    public static class Heap{
        public ArrayList<Node> heap;
        public int n;
        public int count = 0;
        public Heap(Integer capacity){
            n = capacity;
            heap = new ArrayList<>(capacity+1);
            heap.add(new Node(-1,""));
        }

        public void insert(Node node){
            heap.add(node);
            count++;
            up(heap, count);
        }
        public void up(ArrayList<Node> heap, int k){
            while ( k/2 > 0 && heap.get(k/2).value > heap.get(k).value){
                swap(heap, k/2, k);
                k = k/2;
            }
        }
        public void swap(ArrayList<Node> heap, int p, int q){
            Node node = heap.get(p);
            heap.set(p, heap.get(q));
            heap.set(q, node);
        }
        public void print(){
            for (int i = 1; i<=count; i++)
                System.out.println(String.valueOf(heap.get(i).value) + ' ' + heap.get(i).source);
        }
        public ArrayList<Integer> mergeFile(){
            ArrayList<Integer> result = new ArrayList<>();
            while (count > 0){
                Node min = heap.get(1);
                Node node = select(min.source);
                if (node != null)
                    heap.set(1, node);
                else
                    swap(heap, 1, count--);
                result.add(min.value);
                down(heap, count, 1);
            }
            return result;
        }
        private void down(ArrayList<Node> heap, int n, int i) {
            while (true){
                int minPos = i;
                if (i*2 <= n && heap.get(i*2).value < heap.get(i).value) minPos = 2*i;
                if (i*2+1 <= n && heap.get(i*2+1).value < heap.get(minPos).value) minPos = 2*i+1;
                if (minPos == i) break;
                swap(heap, minPos, i);
                i = minPos;
            }
        }

        //根据source选择文件中的数据，如果文件没有数据了，选择其他文件，如果都没有数据了，返回null
        private Node select(String source) {
            boolean isEmpty = false;    //全部的小文件是否都空了
            if (!hasData.get(source)){
                Iterator iter = hasData.entrySet().iterator();
                while (iter.hasNext()){
                    Map.Entry<String, Boolean> entry = (Map.Entry<String, Boolean>) iter.next();
                    if (entry.getValue()){
                        source = entry.getKey();
                        break;
                    }
                }
                if (!iter.hasNext()) isEmpty = true;
            }
            if (isEmpty) return null;
            switch (source){
                case "file1":
                    if (file1.size() == 1)
                        hasData.put("file1", false);
                    return new Node(file1.remove(0), "file1");
                case "file2":
                    if (file2.size() == 1)
                        hasData.put("file2", false);
                    return new Node(file2.remove(0), "file2");
                case "file3":
                    if (file3.size() == 1)
                        hasData.put("file3", false);
                    return new Node(file3.remove(0), "file3");
                default: return null;
            }
        }
    }
    public static void main(String[] args) {
        Heap heap = new Heap(numFile);
        heap.insert(new Node(file1.remove(0), "file1"));
        heap.insert(new Node(file2.remove(0), "file2"));
        heap.insert(new Node(file3.remove(0), "file3"));
        hasData.put("file1", true);
        hasData.put("file2", true);
        hasData.put("file3", true);
//        heap.print();
        ArrayList<Integer> result = heap.mergeFile();
        System.out.println(result);
    }
}
