题目：剑指 Offer II 029. 排序的循环链表

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/4ueAj6
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

注意：本题与主站 708 题相同： https://leetcode-cn.com/problems/insert-into-a-sorted-circular-linked-list/

### Description

给定循环单调非递减列表中的一个点，写一个函数向这个列表中插入一个新元素 insertVal ，使这个列表仍然是循环升序的。

给定的可以是这个列表中任意一个顶点的指针，并不一定是这个列表中最小元素的指针。

如果有多个满足条件的插入位置，可以选择任意一个位置插入新的值，插入后整个列表仍然保持有序。

如果列表为空（给定的节点是 null），需要创建一个循环有序列表并返回这个节点。否则。请返回原先给定的节点。

 ```json
示例 1：
输入：head = [3,4,1], insertVal = 2
输出：[3,4,1,2]
解释：在上图中，有一个包含三个元素的循环有序列表，你获得值为 3 的节点的指针，我们需要向表中插入元素 2 。新插入的节点应该在 1 和 3 之间，插入之后，整个列表如上图所示，最后返回节点 3 。

示例 2：
输入：head = [], insertVal = 1
输出：[1]
解释：列表为空（给定的节点是 null），创建一个循环有序列表并返回这个节点。

示例 3：
输入：head = [1], insertVal = 0
输出：[1,0]

提示：
0 <= Number of Nodes <= 5 * 10^4
-10^6 <= Node.val <= 10^6
-10^6 <= insertVal <= 10^6
 ```





### Solution

插入的数据有三种情况：

- 插入的值为最小值；
- 插入的值为最大值；
- 插入的值为中间值；

我们只需要寻找到插入点，插入即可

本来是挺简单的，然而由于数据存在重复，需要我们代码额外考虑一些场景：

- 比如head为null
- 比如链表只有一个数据
- 比如链表数据为[3,3,3], 插入数据为0

这些我觉得直接特殊判断就行了，保证代码的简洁性

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};
*/

class Solution {
    public Node insert(Node head, int insertVal) {
        // 特殊判断：输入为 null
        if (head == null) {
            head = new Node(insertVal);
            head.next = head;
            return head;
        }
        // 特殊判断，所有数据值都相同
        Node tmp = head.next;
        while (tmp != head) {
            if (tmp.val != tmp.next.val) {
                break;
            }
            tmp = tmp.next;
        }
        if (tmp == head) {
            Node cur = new Node(insertVal, head.next);
            head.next = cur;
            return head;
        }

        // 寻找插入点
        Node p = head;      // p 为插入点
        while (true) {
            // 插入值不是最大值也不是最小值
            if (p.next.val >= insertVal && p.val <= insertVal) break;
            // 插入值是最大值
            if (insertVal > p.val && p.next.val < p.val) break;
            // 插入值是最小值
            if (p.val > p.next.val && insertVal < p.next.val) break;
            p = p.next;
        }
        Node cur = new Node(insertVal, p.next);
        p.next = cur;
        return head;
    }
}
```

