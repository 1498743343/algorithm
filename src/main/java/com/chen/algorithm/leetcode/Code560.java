
package com.chen.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * code560 leetcode 第560题
 * <a href="https://leetcode.cn/problems/subarray-sum-equals-k/description/?envType=study-plan-v2&envId=top-100-liked">测试链接</a>
 *
 * @author chenzihan
 */
public class Code560 {
    /**
     * 和为 K 的子数组 <br/>
     * 解题思路：遍历数组，到下标位 i 的位置时，就往前看，看前面有多少组数可以满足 sumPre + arr[i] = k；因为我们一直用 sum 计算前缀和，并且用 map 记录了
     * 所有的前缀和，所以我们只需要查看 map 中有多少满足 sum - k 的前缀和即可，这里用到的就是前缀和的差来做计算<br/>
     * 上述描述可能有点抽象，我们举个例子：当前位置位 i, sum 代表的是 0~i 的和，如果 0~j(j<i) 的和是 sum - k，那么我们就找到了一个区间 j~i 满足他们的和是 k
     * @param nums 数组
     * @param k 和
     * @return 个数
     */
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int ans = 0;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k)) {
                ans += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }
}