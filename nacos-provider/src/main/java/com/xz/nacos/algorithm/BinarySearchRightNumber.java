package com.xz.nacos.algorithm;

/**
 * @author xz
 * @since 2024/4/7 14:55
 */
public class BinarySearchRightNumber {
    static int nums[] = new int[]{1,2,3,3,3,4,4,5,5,6,6,6,7,7};

    public static void main(String[] args) {
        // 当前数字 + 1的最左位置 - 1等于当前数字的最右位置
        System.out.println(binarySearch(8) - 1);
    }

    public static int binarySearch(int target) {
//        int i = 0, j = nums.length - 1; // 初始化双闭区间 [0, n-1]
//        while (i <= j) {
//            int m = i + (j - i) / 2; // 计算中点索引 m
//            if (nums[m] < target) {
//                i = m + 1; // target 在区间 [m+1, j] 中
//            } else if (nums[m] > target) {
//                j = m - 1; // target 在区间 [i, m-1] 中
//            } else {
//                j = m - 1; // 首个小于 target 的元素在区间 [i, m-1] 中
//            }
//        }
//        // 返回插入点 i
//        return i;

        int l = 0, r = nums.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            int cur = nums[mid];
//            // 找最右，那么就是中位值小于等于时, 都缩小左边界往右去搜索
//            if (cur > target) {
//                r = mid;
//            } else {
//                l = mid + 1;
//            }
            // 找最左同理
            if (cur < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}
