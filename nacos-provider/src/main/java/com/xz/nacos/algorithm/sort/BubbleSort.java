package com.xz.nacos.algorithm.sort;

import java.util.Arrays;

/**
 * @author xz
 * @since 2024/4/7 15:59
 */
public class BubbleSort {
    public static void main(String[] args) {
        int nums[] = new int[]{1,33,4,23,534,54,12,3,2,44,22};

        int len = nums.length;
        for (int i = 0; i < len; i++) {
            boolean flag = false;
            for (int j = 0; j < len - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    flag = true;
                }
            }
            // 优化
            if (!flag) {
                break;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    public static void swap(int nums[], int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}
