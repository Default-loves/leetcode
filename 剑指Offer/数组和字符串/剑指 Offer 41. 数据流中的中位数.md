题目：剑指 Offer 41. 数据流中的中位数

来源：力扣（LeetCode）

链接：https://leetcode-cn.com/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof


### Description

如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。

例如，

[2,3,4] 的中位数是 3

[2,3] 的中位数是 (2 + 3) / 2 = 2.5

设计一个支持以下两种操作的数据结构：

void addNum(int num) - 从数据流中添加一个整数到数据结构中。
double findMedian() - 返回目前所有元素的中位数。

```json
示例 1：
输入：
["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
[[],[1],[2],[],[3],[]]
输出：[null,null,null,1.50000,null,2.00000]

示例 2：
输入：
["MedianFinder","addNum","findMedian","addNum","findMedian"]
[[],[2],[],[3],[]]
输出：[null,null,2.00000,null,2.50000]

限制：
最多会对 addNum、findMedian 进行 50000 次调用。
```

注意：本题与主站 295 题相同：https://leetcode-cn.com/problems/find-median-from-data-stream/



### Solution
```java
class MedianFinder {

    private final PriorityQueue<Integer> A;
    private final PriorityQueue<Integer> B;

    /** initialize your data structure here. */
    public MedianFinder() {
        A = new PriorityQueue<>();      // 小顶堆，保存大数字
        B = new PriorityQueue<>((a, b) -> (b - a));     // 大顶堆，保存小数字
    }

    public void addNum(int num) {
        if (A.size() == B.size()) {
            B.add(num);
            A.add(B.poll());
        } else {
            A.add(num);
            B.add(A.poll());
        }
    }

    public double findMedian() {
        return A.size() == B.size()? (A.peek() + B.peek()) / 2.0: A.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
```

