package com.xz.nacos.bitcompute;

/**
 * @author xz
 * @ClassName BitTest
 * @Description
 * @date 2020/11/21 0021 13:59
 **/
public class BitTest {
    public static void main(String[] args) {
        /**
         * 位运算都是基于整数类型进行运算，且是对于基础的Bit进行运算操作
         */
        // 按位与 对应位都为1 则为1
        // 3 = 0011 << 2 = 1100 = 12 = 3 * 2的2次方
        System.out.println(3 << 2);
        // 按位或 对应位只要有 则为1
        // 15 = 1111 >> 2 = 0011 = 3 = 15 / 2的2次方
        System.out.println(15 >> 2);
        System.out.println(Integer.toBinaryString(15));
        // 异或 对应位相等则为0 不等则为1
        System.out.println(14 ^ 7);
        // 一元操作符 位取分
        System.out.println(~15);
        System.out.println(~5);
        // 不使用模板变量的情况下交换两个变量的值
        int a = 15;
        int b = 7;
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("a="+a+",b="+b);
    }
}
