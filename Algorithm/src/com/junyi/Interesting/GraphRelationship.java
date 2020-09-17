package com.junyi.Interesting;

import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * User: JY
 * Date: 2019/1/23 0023
 * Description: 图关系
 */
public class GraphRelationship {
    private static final int NUM_USER = 6;
    public static final String PATH = System.getProperty("user.dir") + "/resource/relationship.txt";
    public Node[] graph = new Node[NUM_USER+1]; // Did't use 0
    public class Node{
        public int userID;
        public HashSet<Integer> friend;
        public int degree = 0;
        public Node(int id){
            this.userID = id;
            friend = new HashSet<>();
        }
    }
    public void  init(){
        for (int i = 1; i <= NUM_USER; i++)
            graph[i] = new Node(i);
        readTextFile(PATH);
    }
    public void readTextFile(String path){
        File file = new File(path);
        if (file.isFile() && file.exists()){
            try {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file));
                BufferedReader br = new BufferedReader(read);
                String line = "";
                while ((line = br.readLine()) != null){
                    String[] rel = line.split(" ");
                    int fromID = Integer.parseInt(rel[0]);
                    int toID = Integer.parseInt(rel[1]);
                    if (fromID <= NUM_USER) {
                        graph[fromID].friend.add(toID);
                        graph[toID].friend.add(fromID);
                    }
                }
                read.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void searchFriend(int userID) {
        /**
         * use bfs
         */
        if (userID > NUM_USER) return;
        HashSet<Integer> visited = new HashSet<>();
        visited.add(userID);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(userID);
        while (! queue.isEmpty()){
            int currentID = queue.poll();
            for (int id : graph[currentID].friend){
                if (visited.contains(id)) continue;
                visited.add(id);
                queue.offer(id);
                graph[id].degree = graph[currentID].degree + 1;
                System.out.println(String.format("%s to %s is %d", userID, id, graph[id].degree));
            }
        }
    }

    public static void main(String[] args) {
        GraphRelationship gr = new GraphRelationship();
        gr.init();
        gr.searchFriend(1);
    }
}
