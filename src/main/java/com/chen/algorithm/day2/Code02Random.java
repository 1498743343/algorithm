package com.chen.algorithm.day2;

/**
 * code02随机
 *
 * @author chenzihan
 * @date 2022/05/06
 */
public class Code02Random {

    /**
     * 等概率随机获取 [1,5] 范围的一个数
     * 此函数当作黑盒，不可改动，只能调用
     *
     * @return int
     */
    public static int getRandomOneToFive() {
        return (int) (Math.random() * 5) + 1;
    }

    /**
     * 使用 getRandom() 函数，等概率获取 0 或 1
     *
     * @return int
     */
    public static int getZeroOrOne() {
        int result;
        do {
            result = getRandomOneToFive();
        } while (result == 3);
        return result < 3 ? 0 : 1;
    }

    /**
     * 固定概率随机得到0或1，样例中的 0.8 可以随意改变为 [0,1) 区间内的一个值
     * 此函数当作黑盒，不可改动，只能调用
     *
     * @return int
     */
    public static int getRandomZeroOrOne() {
        return Math.random() < 0.8 ? 0 : 1;
    }

    /**
     * 使用 getRandomZeroOrOne() 函数，等概率获取 0 或 1。
     * 假设获取到 0 的概率为 p，那么获取到 1 的概率为 1-p，所以我们可以有以下解法：
     * 调用两次 getRandomZeroOrOne() 函数，获取 0，1 和获取 1，0 的概率都是 p*(1-p)，这样我们就可以等概率返回 0 和 1。
     * 当结果是 0，0或者0，1时放弃结果重新执行就可以了
     *
     * @return int
     */
    public static int getZeroOrOne2() {
        int result;
        do {
            result = getRandomZeroOrOne();
        } while (result == getRandomZeroOrOne()); // 满足 result == getRandomZeroOrOne() 说明两次调用 getRandomZeroOrOne() 获取到的值一样
        return result;
    }

    /**
     * 根据位数等概率获取 0~2的bitNum次方的一个值
     *
     * @param bitNum 数占的 bit 数
     * @return int
     */
    public static int getRandomByBit(int bitNum) {
        int result = 0;
        for (int i = 0; i < bitNum; i++) {
            result += getZeroOrOne() << i;
//            result += getZeroOrOne2() << i;
        }
        return result;
    }

    /**
     * 使用 getRandomByBit() 函数，等概率获取任意 int 区间内的一个值，其中 min != max
     * 这个问题可以看作：等概率获取 [0,max-min] 区间内的一个值，将最后结果 + min 即可
     * 因为任何 int 值都可以通过二进制形式表示，所以每一个 bit 上随机是0或1，这样就可以随机等概率获取一个值
     *
     * @return int
     */
    public static int getRangeNum(int min, int max) {
        int rangeMax = max - min;
        int bitNum = (int) (Math.log(rangeMax) / Math.log(2)) + 1;
        int result;
        do {
            result = getRandomByBit(bitNum);
        } while (result > rangeMax);
        return result + min;
    }

    /**
     * 利用Math.random()函数，把得到[0,x)范围上的数的概率从x调整成x^2
     * 原理如下：当我们使用 randomToRandomPower2() 获取到一个 [0,1) 区间内的随机数后，然后指定一个值 x ~ [0,1)，让 randomToRandomPower2()<x，
     * 这时条件成立的概率就是 x的2次方，因为只有 randomToRandomPower2() 中两次调用 Math.random() 产生的值都 < x，才能满足条件，
     * 由此我们可以引申出 x 的3次方：在 Math.max() 中执行三次 random 函数即可
     *
     * @return int
     */
    public static double randomToRandomPower2() {
        return Math.max(Math.random(), Math.random());
    }

    public static void main(String[] args) {
        int tryTimes = 1000000;
        int min = 0;
        int max = 9;
        int length = max - min + 1;
        int[] arr = new int[length];
        for (int i = 0; i < tryTimes; i++) {
            int rangeNum = getRangeNum(min, max);
            arr[rangeNum - min]++;
        }
        for (int i = 0; i < length; i++) {
            System.out.println(i + min + " 出现了 " + arr[i] + " 次");
        }
        System.out.println("=================");
        int count = 0;
        for (int i = 0; i < tryTimes; i++) {
            if (randomToRandomPower2() < 0.8) {
                count++;
            }
        }
        System.out.println((double) count / (double) tryTimes);
    }
}
