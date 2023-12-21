package com.xz.nacos.algorithm;

import java.util.*;

/**
 * @author xz
 * @since 2023/12/19 14:55
 */
public class NumberConverter {
    /**
     * TODO 尝试使用后缀树
     * @param number
     * @param strList
     * @return
     */
    public static String[] validaString(int number, String[] strList) {
        List<String> ans = new ArrayList<>();
        Map<Character, List<TrieTree>> map = new HashMap<>();
        // 1. 将number 每个位数转换成对应的字母再转化为前缀树
        TrieTree root = new TrieTree();
        List<TrieTree> next = new ArrayList<>();
        next.add(root);
        String strNum = String.valueOf(number);
        int length = strNum.length();
        int level = 0;
        for (int i = 0; i < length; i++) {
            String cur = parse(strNum.charAt(i) - '0');
            int len = cur.length();
            List<TrieTree> curNext = new ArrayList<>();
            for (TrieTree t : next) {
                for (int j = 0; j < len; j++) {
                    TrieTree node = new TrieTree();
                    node.length = length - level;
                    t.children.put(cur.charAt(j), node);
                    List<TrieTree> trees = map.getOrDefault(cur.charAt(j), new ArrayList<>());
                    trees.add(node);
                    map.put(cur.charAt(j), trees);
                    curNext.add(node);
                }
            }
            level ++;
            next = curNext;
            number = number / 10;
        }
        System.out.println(root);
        // 2. 遍历前缀树, 找到所有的字符串
        for (String str : strList) {
            char[] chars = str.toCharArray();
            int len = chars.length;
            if (len > length) {
                continue;
            }
            // 2.1 找到第一个字符对应的前缀树
            List<TrieTree> trees = map.get(chars[0]);
            if (trees == null) {
                continue;
            }
            // 2.2 遍历前缀树, 看字符串是否存在
            for (TrieTree t : trees) {
                if (t.length < len) {
                    continue;
                }
                int i = 1;
                TrieTree cur = t;
                while (i < len) {
                    if (cur.children.containsKey(chars[i])) {
                        cur = cur.children.get(chars[i]);
                        i++;
                    } else {
                        break;
                    }
                }
                if (i == len) {
                    ans.add(str);
                    break;
                }
            }
        }
        return ans.toArray(new String[0]);
    }

    public static String parse(int number) {
        String[] arr = {" ", " ", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        return arr[number];
    }



    static class TrieTree {
        int length;

        Map<Character, TrieTree> children = new HashMap<>();

        @Override
        public String toString() {
            return  length +
                    ":" + children;
        }
    }

    public static void main(String[] args) {
        String[] strList = {"aav", "qwe", "wew", "adg"};
        System.out.println(Arrays.toString(validaString(1234, strList)));


        // 构建后缀树

    }

}
