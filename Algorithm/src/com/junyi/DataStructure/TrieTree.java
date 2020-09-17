package com.junyi.DataStructure;

import java.util.*;

/**
 * User: JY
 * Date: 2019/1/17 0017
 * Description: TrieTree(字典树)
 * TreeNode中的HashMap可以替换成其他的数据结构，来存储一个节点的子节点指针，比如有序数组，跳表，散列表，红黑树等
 */

/**
 * 很多限制：
 * 1. 字符串中包含的字符集不能很大，否则存储空间会浪费很多；
 * 2. 字符串的前缀有比较多的重合，否则存储空间会变大很多
 * 3. 通过指针连接的方式，对缓存总是不友好的；
 * TrieTree适合用于搜索关键字的提示功能、自动输入补全等场景
 */
public class TrieTree {
    public class TreeNode{
        public char label;
        public String prefix;
        public HashMap<Character, TreeNode> sons;
        public Boolean isWord;

        public TreeNode(char c, String pre){
        this.label = c;
        this.prefix = pre;
        this.isWord = false;
        sons = new HashMap<>();
    }
    }
    public TreeNode createPrefixTree(ArrayList<String> data){
        TreeNode head = new TreeNode('#', null);
        for (String phrase : data){
            TreeNode cur = head;
            for (int i = 0; i < phrase.length(); i++){
                Character c = phrase.charAt(i);
                if (cur.sons.containsKey(c))
                    cur = cur.sons.get(c);
                else {
                    TreeNode newNode = new TreeNode(c, phrase.substring(0,i));
                    cur.sons.put(c, newNode);
                    cur = newNode;
                }
            }
            cur.isWord = true;
        }
        return head;
    }
    public void showAllWord(TreeNode head){
        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()){
            TreeNode cur = stack.pop();
            if (cur.isWord) System.out.println(cur.prefix + cur.label);
            Iterator<Map.Entry<Character, TreeNode>> iter = cur.sons.entrySet().iterator();
            Stack<TreeNode> tstack = new Stack<>();
            //下面的操作是让输出更合理，从小到大
            while (iter.hasNext())
                tstack.push(iter.next().getValue());
            while (!tstack.isEmpty())
                stack.push(tstack.pop());
        }
    }
    public Boolean isInside(TreeNode head, String word){
        TreeNode node = head;
        for (Character ch : word.toCharArray()){
            if (node.sons.containsKey(ch))
                node = node.sons.get(ch);
            else return false;
        }
        if (node.isWord) return true;
        else return false;
    }

    public static void main(String[] args) {
        ArrayList<String> sList = new ArrayList<>(Arrays.asList("ab", "abb", "abbc", "ad"));
        TrieTree pt = new TrieTree();
        TreeNode head = pt.createPrefixTree(sList);
        pt.showAllWord(head);
        Boolean result = pt.isInside(head, "ab");
        System.out.println(result);
    }
}
