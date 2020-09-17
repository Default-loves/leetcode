package com.junyi.sort;

import java.util.LinkedList;

/**
 * 拓扑排序
 * 使用DFS方法
 */

public class topoSortByDFS {
    public static class Graph {
        private int n;
        private LinkedList<Integer> adj[];  //邻接表
        private LinkedList<Integer> inverAdj[]; //逆邻接表
        private boolean[] visited;
        public Graph(int num) {
            this.n = num+1;     //0索引不放数据
            adj = new LinkedList[this.n];
            inverAdj = new LinkedList[this.n];
            for (int i = 0; i< this.n; i++) {
                adj[i] = new LinkedList<>();
                inverAdj[i] = new LinkedList<>();
            }
        }
        public void addEdge(int p, int q) {
            adj[p].add(q);
            inverAdj[q].add(p);
        }
        public void run() {
            visited = new boolean[this.n];
            for (int i = 1; i< this.n; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    dfs(i);
                }
            }
        }
        public void dfs(int vertex) {
            for (int i = 0; i < inverAdj[vertex].size(); i++) {
                int k = inverAdj[vertex].get(i);
                if (visited[k]) continue;
                visited[k] = true;
                dfs(k);
            }
            System.out.print("->" + vertex);
        }
    }
    public static void main(String[] args) {
        Graph graph = new topoSortByDFS.Graph(7);
        graph.addEdge(1,3);
        graph.addEdge(2,3);
        graph.addEdge(3,4);
        graph.addEdge(5,6);
        graph.addEdge(5,7);
        graph.run();
    }
}
