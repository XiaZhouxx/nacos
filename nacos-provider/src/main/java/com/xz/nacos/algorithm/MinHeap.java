package com.xz.nacos.algorithm;

import java.util.Arrays;
import java.util.Comparator;

public class MinHeap<T> {
    private int size;

    private T[] values;

    private Comparator<T> defaultComparator = (t1, t2) -> t2.hashCode() - t1.hashCode();

    public MinHeap(T[] values) {
        this.values = values;
    }

    public void add(T v) {
        values[size] = v;
        int cur = size;
        int parentIdx = (size - 1) / 2;
        while (defaultComparator.compare(v, values[parentIdx]) > 0) {
            T temp = values[parentIdx];
            values[parentIdx] = values[cur];
            values[cur] = temp;
            cur = parentIdx;
            parentIdx = (parentIdx - 1) / 2;
        }
        size++;
    }

    public T poll() {
        if (size == 0) {
            return null;
        }
        size --;
        T res = values[0];
        int idx = 0, subL = idx * 2 + 1, subR = idx * 2 + 2;
        swap(idx, size);

        while (subL < size) {
            T cur = null;
            int curIdx = -1;
            if (subR >= size) {
                // 当不存在右子树,
                cur = values[subL];
                curIdx = subL;
            } else {
                if (defaultComparator.compare(values[subR], values[subL]) > 0) {
                    cur = values[subR];
                    curIdx = subR;
                } else {
                    cur = values[subL];
                    curIdx = subL;
                }
            }
            if (cur == null || defaultComparator.compare(values[idx], cur) >= 0) {
                break;
            }
            swap(idx, curIdx);
            idx = curIdx;
            subL = idx * 2 + 1;
            subR = idx * 2 + 2;
        }
        return res;
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
        MinHeap<Integer> integerMaxHeap = new MinHeap<>(new Integer[10]);
        integerMaxHeap.add(10);
        integerMaxHeap.add(6);
        integerMaxHeap.add(8);
        integerMaxHeap.add(19);
        integerMaxHeap.add(15);
        integerMaxHeap.add(18);
        integerMaxHeap.add(16);



        for (int i = 0; i < 7;i ++) {
            System.out.println(Arrays.toString(integerMaxHeap.getValues()));
            System.out.println(integerMaxHeap.poll());
        }
    }
}
