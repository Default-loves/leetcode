package com.junyi.Other;

/**
 * User: JY
 * Date: 2019/3/26 0026
 * Description: 布隆过滤器，这儿使用了位图
 * 布隆过滤器适合用于不需要准确率达到100%,允许存在小概率的错误的大规模数据判重场景
 * TODO：当数据个数和位图大小的比例超过某个阈值的时候，重新申请一个新的位图，后面的数据会放置到新的位图中，跟散列表的扩容类似
 */
public class BloomFilterMy extends BigMapMy{
    public BloomFilterMy(int nbits) {
        super(nbits);
    }
    private int hash1(int data) {
        return data % getNbits();
    }
    private int hash2(int data) {
        return (data + 3) % (getNbits()/2);
    }
    @Override
    public void set(int data) {
        super.set(hash1(data));
        super.set(hash2(data));
    }
    @Override
    public boolean get(int data) {
        return super.get(hash1(data)) & super.get(hash2(data));
    }

    public static void main(String[] args) {
        int[] array = new int[]{11,4,6,5,3,2,8,1,5,6,0,10};
        BloomFilterMy bf = new BloomFilterMy(100);
        for (int item : array) {
            bf.set(item);
        }
        System.out.println("99:" + bf.get(99));
        System.out.println("11:" + bf.get(11));
    }
}
