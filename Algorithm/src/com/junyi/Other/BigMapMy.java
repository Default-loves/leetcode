package com.junyi.Other;

/**
 * User: JY
 * Date: 2019/3/26 0026
 * Description: 位图(BigMap)，数据的范围不能够很大，如果很大的话，用布隆过滤器
 */
public class BigMapMy {
    private char[] bytes;   //一个char类型是2 byte
    private int nbits;
    public char[] getBytes() {
        return bytes;
    }
    public void setBytes(char[] bytes) {
        this.bytes = bytes;
    }
    public int getNbits() {
        return nbits;
    }
    public void setNbits(int nbits) {
        this.nbits = nbits;
    }
    public BigMapMy(int nbits) {
        this.nbits = nbits;
        bytes = new char[nbits];
    }

    public void set(int a) {
        if (a > nbits) return;
        int bytesIndex = a /16; //16的原因是因为1个char是16个bit
        int bitIndex = a % 16;
        bytes[bytesIndex] |= (1 << bitIndex);
    }
    public boolean get(int a) {
        if (a > nbits) return false;
        int bytesIndex = a /16;
        int bitIndex = a % 16;
        return (bytes[bytesIndex] & (1 << bitIndex)) != 0;
    }

    public static void main(String[] args) {
        int[] array = new int[]{11,4,6,5,3,2,8,1,5,6,0,10};
        BigMapMy bm = new BigMapMy(100);
        for (int item : array) {
            bm.set(item);
        }
        System.out.println(bm.get(20));
        System.out.println(bm.get(1));
    }
}
