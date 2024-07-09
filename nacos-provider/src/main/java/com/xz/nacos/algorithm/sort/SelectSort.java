package com.xz.nacos.algorithm.sort;

import java.util.Arrays;

/**
 * 选择排序
 * @author xz
 * @since 2024/4/7 16:27
 */
public class SelectSort {
    public static void main(String[] args) {
        int nums[] = new int[]{1,33,4,23,534,54,12,3,2,44,22};

        for (int i = 0; i < nums.length - 1; i++) {
            int tmp = i;
            // 每次选择一个最小的数放到i下标, 最终得到的就是排序的数组
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[tmp]) {
                    tmp = j;
                }
            }
            swap(nums, i, tmp);
        }
        System.out.println(Arrays.toString(nums));
    }

    public static void swap(int nums[], int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}
