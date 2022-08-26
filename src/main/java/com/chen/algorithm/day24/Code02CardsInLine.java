package com.chen.algorithm.day24;

/**
 * code02 两个人拿一组纸牌
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线
 * 玩家A和玩家B依次拿走每张纸牌
 * 规定玩家A先拿，玩家B后拿
 * 但是每个玩家每次只能拿走最左或最右的纸牌
 * 玩家A和玩家B都绝顶聪明
 * 请返回最后获胜者的分数。
 *
 * @author chenzihan
 * @date 2022/08/26
 */
public class Code02CardsInLine {

    public static void main(String[] args) {
        int[] arr = {5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7};
        System.out.println(win1(arr));
        System.out.println(win2(arr));
        System.out.println(win3(arr));
    }

    /**
     * 方法一：暴力递归
     *
     * @param arr 数组
     * @return int
     */
    private static int win1(int[] arr) {
        int first = first1(arr, 0, arr.length - 1);
        int second = second1(arr, 0, arr.length - 1);
        return Math.max(first, second);
    }

    private static int first1(int[] arr, int left, int right) {
        // 当只剩最后一个时，只能直接拿然后返回
        if (left == right) {
            return arr[left];
        }
        // 我是先手，我不会直接拿当前 left 和 right 中较大的那一个
        // 我拿完这一次以后，我就是后手了，我要看我作为后手怎么拿能拿到最大值，然后去决定我这次拿 left 还是 right
        int p1 = arr[left] + second1(arr, left + 1, right);
        int p2 = arr[right] + second1(arr, left, right - 1);
        return Math.max(p1, p2);
    }

    private static int second1(int[] arr, int left, int right) {
        // 只剩最后一个时，我是后手，先手一定会拿走，所以我什么也拿不到
        if (left == right) {
            return 0;
        }
        // 我是后手，我不能决定先手给我留的牌，但是我知道他一定会留给我最小的牌，所以我只能在先手留给我的小的牌中去拿最大值
        int p1 = first1(arr, left + 1, right);
        int p2 = first1(arr, left, right - 1);
        return Math.min(p1, p2);
    }


    /**
     * 方法二：缓存 + 暴力递归
     * 通过方法一我们发现，first 和 second 方法的返回值只和 left 与 right 两个参数有关，所以我们建立两个二维数组来缓存他们的结果
     *
     * @param arr 数组
     * @return int
     */
    private static int win2(int[] arr) {
        int length = arr.length;
        int[][] firstMap = new int[length][length];
        int[][] secondMap = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                firstMap[i][j] = -1;
                secondMap[i][j] = -1;
            }
        }
        int first = first2(arr, 0, arr.length - 1, firstMap, secondMap);
        int second = second2(arr, 0, arr.length - 1, firstMap, secondMap);
        return Math.max(first, second);
    }

    private static int first2(int[] arr, int left, int right, int[][] firstMap, int[][] secondMap) {
        if (firstMap[left][right] != -1) {
            return firstMap[left][right];
        }
        int ans;
        if (left == right) {
            ans = arr[left];
        } else {
            int p1 = arr[left] + second2(arr, left + 1, right, firstMap, secondMap);
            int p2 = arr[right] + second2(arr, left, right - 1, firstMap, secondMap);
            ans = Math.max(p1, p2);
        }
        firstMap[left][right] = ans;
        return ans;
    }

    private static int second2(int[] arr, int left, int right, int[][] firstMap, int[][] secondMap) {
        if (secondMap[left][right] != -1) {
            return secondMap[left][right];
        }
        int ans = 0;
        if (left != right) {
            int p1 = first2(arr, left + 1, right, firstMap, secondMap);
            int p2 = first2(arr, left, right - 1, firstMap, secondMap);
            ans = Math.min(p1, p2);
        }
        secondMap[left][right] = ans;
        return ans;
    }

    /**
     * 方法三：动态规划
     * 由方法二我们可以发现，用两个二维数组就可以表示所有情况，所以我们只需要把两个二维数组填上，就可以直接取值
     * 由方法二可以知道：
     * 1. firstMap 除了 left == right 的情况，其他点依赖于 secondMap
     * 2. secondMap 除了 left ==right 的情况，其他点依赖于 firstMap
     * 综上，我们根据 base case 把两个二维数组填上即可
     *
     * @param arr 数组
     * @return int
     */
    private static int win3(int[] arr) {
        int length = arr.length;
        int[][] firstMap = new int[length][length];
        int[][] secondMap = new int[length][length];
        for (int i = 0; i < length; i++) {
            firstMap[i][i] = arr[i];
        }
        for (int right = 1; right < length; right++) {
            int curRight = right;
            for (int left = 0; (left < length) && (curRight < length); left++, curRight++) {
                firstMap[left][curRight] = Math.max((arr[left] + secondMap[left + 1][curRight]), (arr[curRight] + secondMap[left][curRight - 1]));
                secondMap[left][curRight] = Math.min(firstMap[left + 1][curRight], firstMap[left][curRight - 1]);
            }
        }
        return Math.max(firstMap[0][length - 1], secondMap[0][length - 1]);
    }

}
