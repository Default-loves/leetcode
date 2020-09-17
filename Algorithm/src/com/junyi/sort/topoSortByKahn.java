package com.junyi.sort;


import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * 拓补排序
 * 使用Kahn方法
 */
public class topoSortByKahn {
    public static class Graph {
        private int point;
        private LinkedList<Integer> adj[];
        public Graph(int point) {
            this.point = point+1;   //第0位不存储数据
            adj = new LinkedList[this.point];
            for (int i = 0; i < this.point; i++) {
                adj[i] = new LinkedList<>();
            }
        }
        public void addEdge(int p, int q) {
            adj[p].add(q);
        }
        public void sort() {
            int[] inDegree = new int[this.point];   //  存储每个节点的入度
            for (int i = 0; i< this.point; i++) {
                for (int j = 0; j < adj[i].size(); j++) {
                    int k = adj[i].get(j);
                    inDegree[k]++;
                }
            }
            LinkedList<Integer> queue = new LinkedList<>(); //存储入度为0的节点
            for (int i = 1; i< this.point; i++) {
                if (inDegree[i] == 0) queue.add(i);
            }
            while (!queue.isEmpty()) {
                int k = queue.remove();
                System.out.print("->" + k);
                for (int i = 0; i < adj[k].size(); i++) {   //遍历节点的后续节点
                    int a = adj[k].get(i);
                    inDegree[a]--;
                    if (inDegree[a] == 0) queue.add(a);
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new topoSortByKahn.Graph(7);
        graph.addEdge(1,3);
        graph.addEdge(2,3);
        graph.addEdge(3,4);
        graph.addEdge(5,6);
        graph.addEdge(5,7);
        graph.sort();
    }
}
