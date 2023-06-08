package com.xz.nacos.algorithm;

import com.xz.nacos.domain.User;

import java.util.*;

/**
 * 加强堆
 * 增强对于堆存储的对象如果内部属性发生变化的感知, 并能够调整堆
 */
public class HeapGreater<T> {
    /**
     * 底层堆结构
     */
    private List<T> heap;

    /**
     * 堆内值的索引
     */
    private Map<T, Integer> valIndex;

    /**
     * 堆大小, 同时也是堆存储位置的索引
     */
    private int heapSize;

    /**
     * 比较器
     */
    private Comparator<T> cmp;


    public HeapGreater(Comparator<T> cmp) {
        this.cmp = cmp;
        heap = new ArrayList<>();
        valIndex = new HashMap<>();
    }

    public void add(T t) {
        heap.add(t);

        valIndex.put(t, heapSize);

        heapUp(heapSize);

        heapSize++;
    }

    public T poll() {
        swap(0, --heapSize);

        heapDown(0);

        T res = heap.remove(heapSize);

        valIndex.remove(res);

        return res;
    }

    public T peek() {
        return heap.get(0);
    }

    public boolean change(T t) {
        if (valIndex.containsKey(t)) {
            int idx = valIndex.get(t);
            swap(idx, heapSize - 1);
            resign(idx);
            return true;
        }

        return false;
    }

    public boolean contains(T t) {
        return valIndex.containsKey(t);
    }

    public int size() {
        return heapSize;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    private void resign(int idx) {
        heapDown(idx);
        heapUp(idx);
    }

    private void heapDown(int idx) {
        int l = idx * 2 + 1;
        while (l < heapSize) {
            int validIdx = l, r = l + 1;
            if (r < heapSize && cmp.compare(heap.get(r), heap.get(l)) < 0) {
                validIdx = r;
            }
            if (cmp.compare(heap.get(idx), heap.get(validIdx)) < 0) {
                break;
            }
            swap(validIdx, idx);
            idx = validIdx;
            l = idx * 2 + 1;
        }
    }

    private void heapUp(int idx) {
        while (idx > 0) {
            int parent = (idx - 1) >> 1;
            if (cmp.compare(heap.get(parent), heap.get(idx)) < 0) {
                break;
            }
            swap(parent, idx);
            idx = parent;
        }
    }

    private void swap(int i1, int i2) {
        T t1 = heap.get(i1);
        T t2 = heap.get(i2);

        heap.set(i1, t2);
        heap.set(i2, t1);

        valIndex.put(t1, i2);
        valIndex.put(t2, i1);
    }

    public static void main(String[] args) {
        HeapGreater<User> heapGreater = new HeapGreater<>((u1, u2) -> {
            if (!u1.getId().equals(u2.getId())) {
                return u1.getId() - u2.getId();
            } else if (!u1.getAge().equals(u2.getAge())) {
                return u1.getAge() - u2.getAge();
            } else {
                return u1.getTime() - u2.getTime();
            }
        });

        User u = new User(0, 0, 1);
        User u1 = new User(1293, 0, 1);
        User u3 = new User(91, 0, 1);
        User u2 = new User(523, 0, 1);
        User u4 = new User(12433, 0, 1);

        heapGreater.add(u);
        heapGreater.add(u1);
        heapGreater.add(u2);
        heapGreater.add(u3);
        heapGreater.add(u4);

        u4.setId(12);

        heapGreater.change(u4);

        int size = heapGreater.size();
        for (int i = 0; i < size; i++) {
            System.out.println(heapGreater.poll());
        }
    }


}
