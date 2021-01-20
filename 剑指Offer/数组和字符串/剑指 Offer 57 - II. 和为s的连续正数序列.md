题目：剑指 Offer 57 - II. 和为s的连续正数序列

来源：力扣（LeetCode）

链接：https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof

### Description

输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。

序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。

限制：

1 <= target <= 10^5

```json
示例 1：

输入：target = 9
输出：[[2,3,4],[4,5]]
示例 2：

输入：target = 15
输出：[[1,2,3,4,5],[4,5,6],[7,8]]
```

### Solution
```java
class Solution {
    public int[][] findContinuousSequence(int target) {
        int capacity = 30;
        int[][] res = new int[capacity][];
        int left = 1, right = 2;
        int k = 0;
        while (right <= target/2+1) {
            int sum = (left + right)*(right - left + 1)/2;
            if (sum > target)
                left++;
            else if (sum < target)
                right++;
            else {
                int[] tmp = new int[right-left+1];
                for (int i = 0; i < tmp.length; i++) {
                    tmp[i] = left+i;
                }
                res[k++] = tmp;
                left++;
                if (k == capacity) {
                    capacity += capacity;
                    res = Arrays.copyOf(res, capacity);
                }
            }
        }
        res = Arrays.copyOf(res, k);
        return res;
    }
}
```

