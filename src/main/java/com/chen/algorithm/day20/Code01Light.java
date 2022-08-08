package com.chen.algorithm.day20;

import java.util.HashSet;

/**
 * 给定一个字符串str，只由‘X’和‘.’两种字符构成。
 * ‘X’表示墙，不能放灯，也不需要点亮
 * ‘.’表示居民点，可以放灯，需要点亮
 * 如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
 * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 *
 * @author chenzihan
 * @date 2022/08/08
 */
public class Code01Light {

    public static int minLight1(String road) {
        if (road == null || road.length() == 0) {
            return 0;
        }
        return process(road.toCharArray(), 0, new HashSet<>());
    }

    /**
     * 递归解决的主要思路：只要是 . 的位置，都可以点灯或者不点灯，假设当前的 . 是 i 位置，如果 i-1,i,i+1都没有点灯，那这就是一个错误的结果，
     * 所以我们只需要将所有的错误结果排除，然后递归找到所有正确结果中点灯数量最少的答案，返回即可
     *
     * @param str    str
     * @param index  指数
     * @param lights 灯
     * @return int
     */
    public static int process(char[] str, int index, HashSet<Integer> lights) {
        // 没走到最后时，递归所有可能
        if (index != str.length) {
            int no = process(str, index + 1, lights);
            int yes = Integer.MAX_VALUE;
            if (str[index] == '.') {
                lights.add(index);
                yes = process(str, index + 1, lights);
                lights.remove(index);
            }
            return Math.min(yes, no);
        } else {
            // 走到一个方案的最后时，判断这个方案是否满足要求，分情况返回
            for (int i = 0; i < str.length; i++) {
                // 如果当前位置是'.' 并且附近三个节点都没有点灯，那就是一个错误的方案
                if (str[i] == '.' && !lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
                    return Integer.MAX_VALUE;
                }
            }
            // 如果所有位置都满足要求，返回 light 的数量
            return lights.size();
        }
    }

    public static int minLight2(String road) {
        char[] str = road.toCharArray();
        int lights = 0;
        int i = 0;
        while (i < str.length) {
            if (str[i] == 'X') {
                i++;
            } else {
                lights++;
                if (i + 1 == str.length) {
                    break;
                } else if (str[i + 1] == 'X') {
                    // 如果下一个是 X，就跳过，即 i 跳到 i+2 的位置
                    i += 2;
                } else {
                    i += 3;
                }
            }
        }
        return lights;
    }


    public static int minLight3(String road) {
        char[] str = road.toCharArray();
        // 总灯数
        int lights = 0;
        // 两个 X 之间连续的 . 的数量
        int num = 0;
        for (char c : str) {
            if (c == 'X') {
                // 对连续 . 的数量取整
                lights += (num + 2) / 3;
                num = 0;
            } else {
                num++;
            }
        }
        // 如果 road 不是以 X 结尾，num 就会已经累加过，但是没有计入到总数中
        lights += (num + 2) / 3;
        return lights;
    }

    public static String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        int len = 20;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            String test = randomString(len);
            int ans1 = minLight1(test);
            int ans2 = minLight2(test);
            int ans3 = minLight3(test);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("str = " + test);
                System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
                System.out.println("ans3 = " + ans3);
                System.out.println("oops!");
                break;
            }
        }
        System.out.println("finish!");
    }
}
