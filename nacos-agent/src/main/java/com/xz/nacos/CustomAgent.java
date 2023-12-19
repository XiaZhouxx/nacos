package com.xz.nacos;

import java.lang.instrument.Instrumentation;

/**
 * @author xz
 * @since 2023/12/15 15:01
 */
public class CustomAgent {
    /**
     * 静态载入时会调用的方法
     *
     * -javaagent:/Users/xiazhou/IdeaProjects/nacos/nacos-agent/target/nacos-agent-1.0-SNAPSHOT-jar-with-dependencies.jar
     *
     * @param agentArgs
     * @param inst
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("premain, I'm static agent!");
        System.out.println("args: " + agentArgs);
    }

    /**
     * 动态加载agent时会调用的方法
     *
     * VirtualMachine vm = VirtualMachine.attach("88904");
     * vm.loadAgent("/Users/xiazhou/IdeaProjects/nacos/nacos-agent/target/nacos-agent-1.0-SNAPSHOT-jar-with-dependencies.jar", "args");
     *
     * @param agentArgs
     * @param inst
     */
    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("agentmain, I'm dynamic agent!");
        System.out.println("args: " + agentArgs);
    }
}