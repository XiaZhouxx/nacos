package com.xz.nacos.algorithm;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 快排
 */
public class QuickSort {

    public static int getDigit(int num, int p) {
        while (--p > 0) {
            num = num / 10;
        }
        return num % 10;
    }

    public static void quickSort(int[] arr) {
        doQuickSort(arr, 0, arr.length - 1);
    }

    public static void doQuickSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        // 随机快排. 避免 o(n logN)
        swap(arr, l + (int)(Math.random() * (r - l) + 1), r);
        int[] dataRange = doSort(arr, l, r);
        System.out.println(Arrays.toString(dataRange));
        doQuickSort(arr, l, dataRange[0] - 1);
        doQuickSort(arr, dataRange[1] + 1, r);
    }

    public static int[] doSort(int[] arr, int l, int r) {
        int li = l, val = arr[r];
        while (li <= r) {
            int cur = arr[li];
            if (cur < val) {
                swap(arr, l, li);
                l++;
            }
            if (cur > val) {
                swap(arr, r, li);
                r--;
                li--;
            }
            li++;
        }
        return new int[]{l, r};
    }

    public static void swap(int[] arr, int i, int i1) {
        int tmp = arr[i];
        arr[i] = arr[i1];
        arr[i1] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2,3,1,4,8,9,6};
        System.out.println(Math.random());
        System.out.println(        Arrays.toString(arr));
        quickSort(arr);
        System.out.println(        Arrays.toString(arr));
    }
}
