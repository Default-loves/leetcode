题目：剑指 Offer 33. 二叉搜索树的后序遍历序列

来源：力扣（LeetCode）

链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof


### Description

输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。

参考以下这颗二叉搜索树：

         5
        / \
       2   6
      / \
     1   3


```json
示例 1：
输入: [1,6,3,2,5]
输出: false

示例 2：
输入: [1,3,2,6,5]
输出: true

提示：
数组长度 <= 1000
```

### Solution
```java
class Solution {
    public boolean verifyPostorder(int[] postorder) {
        return verify(postorder, 0, postorder.length - 1);
    }

    // 后续遍历：【左子树】【右子树】【根】
    private boolean verify(int[] postorder, int a, int b) {
        if (a >= b) {
            return true;
        }
        int i = a;  // 右子树的开始索引
        while (postorder[i] < postorder[b]) {
            i++;
        }
        int j = i;
        while (postorder[j] > postorder[b]) {   // 右子树所有节点都应该大于根节点
            j++;
        }
        return j == b && verify(postorder, a, i - 1) && verify(postorder, i, b - 1);
    }
}
```

