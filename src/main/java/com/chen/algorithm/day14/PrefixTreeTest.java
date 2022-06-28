package com.chen.algorithm.day14;

import java.util.HashMap;

/**
 * 前缀树测试
 *
 * @author chenzihan
 * @date 2022/06/28
 */
public class PrefixTreeTest {

    public static class Right {

        private HashMap<String, Integer> box;

        public Right() {
            box = new HashMap<>();
        }

        public void insert(String word) {
            if (!box.containsKey(word)) {
                box.put(word, 1);
            } else {
                box.put(word, box.get(word) + 1);
            }
        }

        public void delete(String word) {
            if (box.containsKey(word)) {
                if (box.get(word) == 1) {
                    box.remove(word);
                } else {
                    box.put(word, box.get(word) - 1);
                }
            }
        }

        public int search(String word) {
            return box.getOrDefault(word, 0);
        }

        public int prefixNumber(String pre) {
            int count = 0;
            for (String cur : box.keySet()) {
                if (cur.startsWith(pre)) {
                    count += box.get(cur);
                }
            }
            return count;
        }
    }
    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 6);
            ans[i] = (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }
    public static void main(String[] args) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            Code01PrefixTree trie1 = new Code01PrefixTree();
            Code02PrefixTree trie2 = new Code02PrefixTree();
            Right right = new Right();
            for (String s : arr) {
                double decide = Math.random();
                if (decide < 0.25) {
                    trie1.insert(s);
                    trie2.insert(s);
                    right.insert(s);
                } else if (decide < 0.5) {
                    trie1.delete(s);
                    trie2.delete(s);
                    right.delete(s);
                } else if (decide < 0.75) {
                    int ans1 = trie1.search(s);
                    int ans2 = trie2.search(s);
                    int ans3 = right.search(s);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                } else {
                    int ans1 = trie1.prefixNumber(s);
                    int ans2 = trie2.prefixNumber(s);
                    int ans3 = right.prefixNumber(s);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        System.out.println("finish!");
    }
}
