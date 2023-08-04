package com.xz.nacos.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xz
 * @date 2023/8/4 16:28
 */
public class DP {
    /**
     * 计算从左上角到右下角的最短耗时 和 路径
     * @param args
     */
    public static void main(String[] args) {
        int[][] data = new int[6][6];
//        data[0] = new int[]{2,4,6,8,7,9};
//        data[1] = new int[]{3,8,9,1,4,5};
//        data[2] = new int[]{6,9,12,2,15,11};
//        data[3] = new int[]{8,7,18,7,13,2};
//        data[4] = new int[]{7,2,10,4,3,7};
//        data[5] = new int[]{9,5,6,17,5,1};
        for (int i = 0 ; i < 6; i++) {
            int[] d = new int[6];
            for (int j = 0; j < 6; j++) {
                d[j] = (int)(Math.random() * 100);
            }
            System.out.println(Arrays.toString(d));
            data[i] = d;
        }


        int[][] dp = new int[6][6];
        List<Integer> path = new ArrayList<>(11);
        int pre = 0;
        // 首行计算累加和
        for (int i = 0; i < 6; i++) {
            dp[0][i] = pre + data[0][i];
            pre = dp[0][i];
        }
        for (int i = 1; i < 6; i++) {
            dp[i][0] = dp[i - 1][0] + data[i][0];
            for (int j = 1; j < 6; j++) {
                int cur = data[i][j];
                // 最短耗时
                dp[i][j] = Math.min(dp[i - 1][j] + cur, dp[i][j - 1] + cur);
            }
        }
        int l = 5, r = 5;
        int res = dp[5][5];
        // 根据结果解析出路径
        while (l >= 0 && r >= 0) {
            res -= data[l][r];
            path.add(data[l][r]);
            if (l > 0 && dp[l - 1][r] == res) {
                l--;
            } else {
                r--;
            }
        }
        System.out.println(path);
        System.out.println(dp[5][5]);
    }
}
