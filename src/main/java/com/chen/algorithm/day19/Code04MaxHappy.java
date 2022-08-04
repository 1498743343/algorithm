package com.chen.algorithm.day19;

import java.util.ArrayList;
import java.util.List;

/**
 * code04最大快乐值
 * 每个人都有快乐值，我来那我的直接下级就都不能来，求解最大快乐值
 *
 * @author chenzihan
 * @date 2022/08/04
 */
public class Code04MaxHappy {

    public static class Employee {
        public int happy;
        public List<Employee> nexts;

        public Employee(int h) {
            happy = h;
            nexts = new ArrayList<>();
        }

    }

    // for test
    public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
        if (Math.random() < 0.02) {
            return null;
        }
        Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
        genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
        return boss;
    }

    // for test
    public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
        if (level > maxLevel) {
            return;
        }
        int nextsSize = (int) (Math.random() * (maxNexts + 1));
        for (int i = 0; i < nextsSize; i++) {
            Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
            e.nexts.add(next);
            genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
        }
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxNexts = 7;
        int maxHappy = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
            if (maxHappy(boss) != test(boss)) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("finish!");
    }

    private static int maxHappy(Employee boss) {
        Info ans = process(boss);
        return Math.max(ans.yes, ans.no);
    }

    private static Info process(Employee boss) {
        if (boss == null) {
            return new Info(0, 0);
        }
        int yes = boss.happy;
        int no = 0;
        for (Employee person : boss.nexts) {
            Info personInfo = process(person);
            // boss 来，我一定不来
            yes += personInfo.no;
            // boss 不来，我不一定来，要看 yes 和 no 谁更大
            no += Math.max(personInfo.yes, personInfo.no);
        }
        return new Info(yes, no);
    }

    private static int test(Employee boss) {
        if (boss == null) {
            return 0;
        }
        return getHappy(boss, false);
    }

    private static int getHappy(Employee boss, boolean up) {
        // 领导来，那我一定不来
        if (up) {
            int ans = 0;
            for (Employee person : boss.nexts) {
                ans += getHappy(person, false);
            }
            return ans;
        } else {
            // 我来
            int yes = boss.happy;
            // 我不来
            int no = 0;
            for (Employee person : boss.nexts) {
                yes += getHappy(person, true);
                no += getHappy(person, false);
            }
            return Math.max(yes, no);
        }
    }

    static class Info {
        /**
         * 我来的最大值
         */
        public int yes;
        /**
         * 我不来的最大值
         */
        public int no;

        public Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }

}
