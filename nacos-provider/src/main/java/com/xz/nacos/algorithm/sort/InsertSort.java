package com.xz.nacos.algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序
 * @author xz
 * @since 2023/8/31 16:16
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] randomArr = new int[]{11, 22, 3, 41, 5, 62, 7, 81, 9, 10};
        insertSort(randomArr);
        System.out.println(Arrays.toString(randomArr));
    }

    public static void insertSort(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;
            // 从后往前找，找到最靠前比temp小的数下标
            while (j >= 0 && arr[j] > temp) {
                // 将比当前值大的左移
                arr[j + 1] = arr[j];
                j--;
            }
            // 需要插入的位置
            arr[j + 1] = temp;
        }
    }
}
