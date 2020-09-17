package com.junyi.Other;

import java.util.HashMap;
/*
    实现LRU缓存算法
 */
public class LRUCache {
    public class Node{
        private String key;
        private String value;
        private Node pre;
        private Node next;

        public Node(String key, String value){
            this.key = key;
            this.value = value;
        }
    }
    private Node head;
    private Node end;
    private int capacity;
    private HashMap<String, Node> hashMap;
    public LRUCache(int capacity){
        this.capacity = capacity;
        hashMap = new HashMap<>();
    }

    public String get(String key){
        Node node = hashMap.get(key);
        if (node == null)
            return null;
        refreshNode(node);
        return node.value;
    }
    // 由于访问了链表内的数据，所以刷新节点，将节点放到链表尾部
    private void refreshNode(Node node) {
        if (node == end)
            return;
        removeNode(node);
        addNode(node);
    }

    private void addNode(Node node) {
        if (end != null){
            end.next = node;
            node.pre = end;
            node.next = null;
        }
        end = node;
        if (head == null)
            head = node;
    }

    private String removeNode(Node node) {
        if (node == end){
            end = end.pre;
            end.next = null;
        }
        else if (node == head){
            head = head.next;
            head.pre = null;
        }
        else{
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        return node.key;
    }

    public void put(String key, String value){
        Node node = hashMap.get(key);
        if (node == null){
            if (hashMap.size() >= capacity){    //  超过了容量，需要删除头结点
                String oldkey = removeNode(head);
                hashMap.remove(oldkey);
            }
            node = new Node(key, value);
            addNode(node);
            hashMap.put(key, node);
        }
        else{
            node.value = value;
            refreshNode(node);
        }

    }
    public void remove(String key){
        Node node = hashMap.get(key);
        if (node == null)
            return;
        hashMap.remove(key);
        removeNode(node);
    }

    public static void main(String[] argv){
        LRUCache lruCache = new LRUCache(5);
        lruCache.put("1", "message 1");
        lruCache.put("2", "message 2");
        lruCache.put("3", "message 3");
        lruCache.put("4", "message 4");
        lruCache.put("5", "message 5");
        lruCache.put("2", "message 2 update");
        System.out.println(lruCache.get("2"));
        lruCache.put("6", "message 6");
        lruCache.put("7", "message 7");
        System.out.println(lruCache.get("1"));
        System.out.println(lruCache.get("3"));
        System.out.println(lruCache.get("2"));
        System.out.println(lruCache.get("4"));
    }
}
