package com.xz.nacos;

import java.lang.instrument.Instrumentation;

/**
 * @author xz
 * @since 2023/12/15 15:01
 */
public class CustomAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("Hi, I'm agent!");
        System.out.println("args: " + agentArgs);
    }
    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("Hi, I'm agent!");
        System.out.println("args: " + agentArgs);
    }
}