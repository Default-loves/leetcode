package com.junyi.Graph;

import java.util.LinkedList;

/**
 * User: JY
 * Date: 2019/3/11 0011
 * Description: 拓扑排序
 */
public class TopologicalSort {
    private class Graph {
        private int v;  //顶点的个数
        private LinkedList<Integer> adj[];   //邻接表
        public Graph(int v) {
            this.v = v;
            adj = new LinkedList[v];
            for (int i = 0; i < v; i++) {
                 adj[i] = new LinkedList<>();
            }
        }
    }
    public void method1(LinkedList<Integer>[] adj) {
        // Kahn算法
        System.out.println("Method 1: ");
        int n = adj.length;
        int[] inDegree = new int[n];    //统计每个顶点的入度
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < adj[i].size(); j++) {
                int w = adj[i].get(j);
                inDegree[w]++;
            }
        }
        LinkedList<Integer> queen = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) queen.add(i);
        }
        while (!queen.isEmpty()) {
            int i =queen.remove();
            System.out.print(" -> " + i);
            for (int j = 0; j < adj[i].size(); j++) {
                int k = adj[i].get(j);
                inDegree[k]--;
                if (inDegree[k] == 0) queen.add(k);
            }
        }
        System.out.println();
    }
    public void method2(LinkedList<Integer>[] adj) {
        //深度优先遍历
        System.out.println("Method 2: ");
        int n = adj.length;
        LinkedList<Integer>[] inverseAdj = new LinkedList[n];
        for (int i = 0; i < n; i++) {   // 申请空间
            inverseAdj[i] = new LinkedList<>();
        }
        for (int i = 0; i < n; i++) {   //通过邻接表构建逆邻接表
            for (int j = 0; j < adj[i].size(); j++) {
                int w = adj[i].get(j);
                inverseAdj[w].add(i);
            }
        }
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {   //dfs遍历
            if (visited[i] == false) {
                visited[i] = true;
                dfs(i, inverseAdj, visited);
            }
        }
    }

    private void dfs(int v, LinkedList<Integer>[] inverseAdj, boolean[] visited) {
        for (int i = 0; i < inverseAdj[v].size(); i++) {
            int w = inverseAdj[v].get(i);
            if (visited[w] == true) continue;
            visited[w] = true;
            dfs(w, inverseAdj, visited);
        }
        System.out.print(" -> " + v);
    }

    public LinkedList<Integer>[] init() {
        Graph graph = new Graph(6);
        graph.adj[0].add(2);
        graph.adj[0].add(1);
        graph.adj[1].add(3);
        graph.adj[3].add(5);
        graph.adj[4].add(5);
        return graph.adj;
    }

    public static void main(String[] args) {
        TopologicalSort ts = new TopologicalSort();
        LinkedList<Integer>[] adj = ts.init();
        ts.method1(adj);
        ts.method2(adj);
    }
}
