package com.xz.nacos.algorithm;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[]{2,3,1,4,8,9,6};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }


    public static void mergeSort(int[] nums, int s, int e) {
         if (s >= e) {
             return ;
         }
        int mid = s + (e - s) / 2;
        mergeSort(nums, s, mid);
        mergeSort(nums, mid + 1, e);

        int[] tmp = new int[e - s + 1];
        int l = s, r = mid + 1, idx = 0;
        while (l <= mid && r <= e) {
            tmp[idx++] = nums[l] > nums[r] ? nums[r++] : nums[l++];
        }
        while (l <= mid) {
            tmp[idx++] = nums[l++];
        }
        while (r <= e) {
            tmp[idx++] = nums[r++];
        }
        idx = 0;
        while (s <= e) {
            nums[s++] = tmp[idx++];
        }
    }
}
