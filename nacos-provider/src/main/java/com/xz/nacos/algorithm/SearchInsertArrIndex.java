package com.xz.nacos.algorithm;

/**
 * 查找数字插入数组的下标
 * @author xz
 * @since 2024/4/7 11:23
 */
public class SearchInsertArrIndex {
    static int arr[] = new int[]{1,2,2,2,2,3,3,3,4,4,4,4,5,5,5,5,5,6};
    public static void main(String[] args) {
        System.out.println(insert(61));
    }

    public static int insert(int number) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            int cur = arr[mid];

            if (cur < number) {
                l = mid + 1;
            } else if (cur > number) {
                // 之前一直没搞懂什么情况可以r = mid但是不能 l = mid。
                // 当元素可以重复时 找最左/最右的时候, 可以保持 r边界, l会持续 +1 达到阈值
                // 但是 l = mid 时, 当二分到最后 两个元素的时候如果不满足条件会死循环
                // [3,4] -> search '6'  1 / 2 = 0.
                r = mid;
            }
        }

        // 1 2 3 4 5
        // 3 4 5
        // 4 5
        //
        return arr.length;
    }
}
