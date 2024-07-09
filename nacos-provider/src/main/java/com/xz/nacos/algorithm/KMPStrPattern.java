package com.xz.nacos.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xz
 * @since 2024/2/19 10:04
 */
public class KMPStrPattern {
    public static void main(String[] args) {
        String pattern = "abab";
        /**
         * abab
         * 前缀： a ab aba
         * 后缀： b ab bab
         * abab = [0,0,1,2]
         * 当匹配 aba的时候失配, 回滚到 b继续匹配
         *
         * 当失配的时候回滚的辅助数组
         */
        int[] help = new int[pattern.length()];
        // 最大匹配长度
        int maxLen = 0;
        for (int i = 1; i < pattern.length(); i++) {
            // 当不匹配时，回退到最近匹配的位置
            while (maxLen > 0 && pattern.charAt(maxLen) != pattern.charAt(i)) {
                maxLen = help[maxLen - 1];
            }
            // 计算匹配长度
            if (pattern.charAt(maxLen) == pattern.charAt(i)) {
                maxLen ++;
            }
            help[i] = maxLen;
        }
        System.out.println(Arrays.toString(help));

        System.out.println(matchCount("abcababcbabcabanbabab", help, pattern));
    }

    private static List<Integer> matchCount(String s, int[] help, String pattern) {
        ArrayList<Integer> ans = new ArrayList<>();
        int match = 0;
        // 匹配时同理，利用匹配数组, 在失配时找到回退的位置
        for (int i = 0; i < s.length(); i++) {
            while (match > 0 && s.charAt(i) != pattern.charAt(match)) {
                match = help[match - 1];
            }
            // 匹配模式串的长度
            if (s.charAt(i) == pattern.charAt(match)) {
                match ++;
            }
            if (match == pattern.length()) {
                ans.add(i - match + 1);
                match = help[match - 1];
            }
        }

        return ans;
    }
}
