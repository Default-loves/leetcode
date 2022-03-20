题目：剑指 Offer 51. 数组中的逆序对

来源：力扣（LeetCode）

链接：https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof


### Description

在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。

 ```json
 示例 1:
 输入: [7,5,6,4]
 输出: 5
 
 限制：
 0 <= 数组长度 <= 50000
 ```



### Solution
```java
class Solution {
    int[] nums, tmp;
    public int reversePairs(int[] nums) {
        this.nums = nums;
        tmp = new int[nums.length];
        return mergeSort(0, nums.length-1);
    }

    private int mergeSort(int left, int right) {
        if (left >= right) {
            return 0;
        }
        int mid = (left + right) >> 1;
        int result = mergeSort(left, mid) + mergeSort(mid + 1, right);

        for (int k = left; k <= right; k++) {    // 复制数组
            this.tmp[k] = this.nums[k];
        }
        //开始合并
        int i = left;   // 左数组的索引
        int j = mid + 1;      // 右数组的索引
        for (int k = left; k <= right; k++) {
            if (i == mid + 1) {     // 左数组全部遍历完
                this.nums[k] = tmp[j++];
            } else if (j == right + 1 || tmp[i] <= tmp[j]){     // 右数组遍历完 || 左数组当前数字小于右数组当前数字，说明无逆序对
                this.nums[k] = tmp[i++];
            } else {    // 当前左数组数字大于当前右数组数字，说明索引从 i ~ mid之间的数字都大于右数组当前数字，都是逆序对
                this.nums[k] = tmp[j++];
                result += mid - i + 1;
            }
        }
        return result;
    }
}
```

