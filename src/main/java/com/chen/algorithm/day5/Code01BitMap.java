package com.chen.algorithm.day5;

import java.util.HashSet;

/**
 * code01 位图
 *
 * @author chenzihan
 * @date 2022/05/12
 */
public class Code01BitMap {
    /**
     * 位图：可以理解为是 HashSet，但是只存储 int 类型的数字
     * 对这个类中用到的二进制操作做一下说明：二进制操作远快于 + - * / 等基础运算
     * value >> 6：一个数字 >> 6，达到的效果和 / 64 是一样的
     * value & 63：一个数字 & 63，达到的效果和 % 64 是一样的
     * (1L << (value & 63))：为的是将 value 取得 / 64 的余数在 64 个 bit 上的具体位置标为 1，注意此处必须是 1L，
     * 因为 1 是 int 类型，只占 32 位，<< 超过 32 时有问题
     *
     * @author chenzihan
     * @date 2022/05/12
     */
    public static class BitMap {
        private long[] bits;

        /**
         * @param max 位图能存储的最大值
         */
        public BitMap(int max) {
            // 因为[0,63]范围内的数字也需要占 8 个字节，所以 >> 6 后要 + 1
            bits = new long[(max >> 6) + 1];
        }

        public void add(int value) {
            bits[value >> 6] |= (1L << (value & 63));
        }

        public void delete(int value) {
            bits[value >> 6] &= ~(1L << (value & 63));
        }

        public boolean contains(int value) {
            // 这里必须使用 != 0 ，因为 num & num == num，而不是 1
            return (bits[value >> 6] & (1L << (value & 63))) != 0;
        }
    }

    public static void main(String[] args) {
        int max = 100000;
        int tryTime = 1000000;
        HashSet<Integer> hashSet = new HashSet<>();
        BitMap bitMap = new BitMap(max);
        System.out.println("测试开始");
        for (int i = 0; i < tryTime; i++) {
            int num = (int) (Math.random() * (max + 1));
            double decide = Math.random();
            if (decide < 0.33) {
                hashSet.add(num);
                bitMap.add(num);
            } else if (decide < 0.66) {
                hashSet.remove(num);
                bitMap.delete(num);
            } else {
                if (bitMap.contains(num) != hashSet.contains(num)) {
                    System.out.println("Oops!");
                    break;
                }
            }
        }
        for (int num = 0; num <= max; num++) {
            if (bitMap.contains(num) != hashSet.contains(num)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束！");
    }
}
