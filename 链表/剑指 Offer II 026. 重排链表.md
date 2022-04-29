题目：剑指 Offer II 026. 重排链表

来源：力扣（LeetCode）

链接：https://leetcode-cn.com/problems/LGjMqU


### Description

给定一个单链表 L 的头节点 head ，单链表 L 表示为：

 L0 → L1 → … → Ln-1 → Ln 
请将其重新排列后变为：

L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …

不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

```json
示例 1:
输入: head = [1,2,3,4]
输出: [1,4,2,3]

示例 2:
输入: head = [1,2,3,4,5]
输出: [1,5,2,4,3]

提示：
链表的长度范围为 [1, 5 * 104]
1 <= node.val <= 1000
```

### Solution
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public void reorderList(ListNode head) {
        ListNode fast = head;   // 快指针
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode mid = slow.next;
        slow.next = null;   // 将链表切成两部分
        ListNode right = revert(mid);// 翻转
        head = merge(head, right);
    }

    private ListNode revert(ListNode node) {
        ListNode newHead = null;
        ListNode tmp = null;
        while (node != null) {
            tmp = node.next;
            node.next = newHead;
            newHead = node;
            node = tmp;
        }
        return newHead;
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode head = left;
        ListNode cur = new ListNode(-1);
        while (left != null && right != null) {
            cur.next = left;
            left = left.next;
            cur.next.next = right;
            right = right.next;
            cur = cur.next.next;
        }
        if (left != null) {		// 如果整体链表节点数为单数，那么left节点数 = right节点数+1
            cur.next = left;
        }
        return head;
    }

}
```

