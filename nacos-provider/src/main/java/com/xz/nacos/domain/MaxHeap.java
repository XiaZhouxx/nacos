package com.xz.nacos.domain;

import java.util.Arrays;
import java.util.Comparator;

public class MaxHeap<T> {
    private int size;

    private T[] values;

    private Comparator<T> defaultComparator = (t1, t2) -> t1.hashCode() - t2.hashCode();

    public MaxHeap(T[] values) {
        this.values = values;
    }

    public void add(T v) {
        values[size] = v;
        int cur = size;
        while (cur > 0) {
            int parentIdx = (cur - 1) / 2;
            T parent = values[parentIdx];
            if (defaultComparator.compare(v, parent) <= 0) {
                break;
            }
            values[cur] = parent;
            cur = parentIdx;
        }
        values[cur] = v;
        size++;
    }

    public T poll() {
        if (size == 0) {
            return null;
        }
        size --;
        T res = values[0];
        swap(0, size);
        heapify(0);

        return res;
    }

    public void heapify(int idx) {
        int l = idx * 2 + 1;
        while (l < size) {
            int validIdx = l + 1 < size && defaultComparator.compare(values[l], values[l + 1]) < 0 ? l + 1 : l;
            validIdx = defaultComparator.compare(values[validIdx], values[idx]) > 0 ? validIdx : idx;
            if (validIdx == idx) {
                break;
            }
            swap(idx, validIdx);
            idx = validIdx;
            l = idx * 2 + 1;
        }
    }

    private void swap(int i1, int i2) {
        T temp = values[i1];
        values[i1] = values[i2];
        values[i2] = temp;
    }

    public T[] getValues() {
        return this.values;
    }

    public static void main(String[] args) {
        MaxHeap<Integer> integerMaxHeap = new MaxHeap<>(new Integer[10]);
        integerMaxHeap.add(10);
        integerMaxHeap.add(6);
        integerMaxHeap.add(8);
        integerMaxHeap.add(19);
        integerMaxHeap.add(15);
        integerMaxHeap.add(18);
        integerMaxHeap.add(16);



        for (int i = 0; i < 7;i ++) {
            System.out.println(Arrays.toString(integerMaxHeap.values));
            System.out.println(integerMaxHeap.poll());
        }

//        int[] nums = new int[]{10, 16, 6, 19, 15, 8};
//        int r = nums.length;
//        for (int i = nums.length - 1; i >= 0; i--) {
//            heapify(nums, i, r);
//        }
//        while (r > 0) {
//            int temp = nums[r - 1];
//            nums[r - 1] = nums[0];
//            nums[0] = temp;
//            r--;
//            heapify(nums, 0, r);
//        }
    }

    private static void heapify(int[] nums, int i, int r) {
        // 去找它的子节点, 是否需要下沉即可 当前节点的子节点 = i * 2 + 1; 父节点 = (i - 1) / 2;
        int k = (i * 2) + 1,  j = i;
        while (k < r) {
            int k1 = k + 1;
            int curI = nums[j];
            int curMax = nums[k];
            // 存在右树, 且值大于左树则用左树的值作为大堆顶
            if (k1 < r && nums[k1] > curMax) {
                curMax = nums[k1];
                k = k1;
            }
            if (curI >= curMax) {
                break;
            }
            // 交换值继续判断下一个子树
            nums[j] = curMax;
            nums[k] = curI;
            j = k;
            k = k * 2 + 1;
        }
        System.out.println(Arrays.toString(nums));
    }
}
