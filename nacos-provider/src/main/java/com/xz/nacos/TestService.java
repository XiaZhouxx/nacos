package com.xz.nacos;

import com.sun.tools.attach.VirtualMachine;
import com.xz.nacos.annotation.CustomService;

import java.io.IOException;

/**
 * @author xz
 * @date 2023/6/23 11:13
 */
@CustomService
public class TestService {
    public static void main(String[] args) throws Exception {
        // 获取jvm进程, 88904是进程id(jps获取)
        VirtualMachine vm = VirtualMachine.attach("88904");
        // agent包的绝对路径
        vm.loadAgent("/Users/xiazhou/IdeaProjects/nacos/nacos-agent/target/nacos-agent-1.0-SNAPSHOT-jar-with-dependencies.jar", "args");
//        List<int[]> i = new ArrayList<>();
//        i.add(new int[]{1,3,5});
//        i.add(new int[]{2,4,6});
//        // stream流里面的元素也是集合类型时, 单纯的map要么类型不变 要么只能映射为某个单对象
//        List<Integer> collect = i.stream().flatMap(id -> {
//            List<Integer> i1 = new ArrayList<>();
//            for (int ii : id) {
//                i1.add(ii);
//            }
//            return i1.stream();
//        }).collect(Collectors.toList());
//        System.out.println(collect);

//        List<String[]> i = new ArrayList<>();
//        i.add(new String[]{"1","3","5"});
//        i.add(new String[]{"2","4","6"});
//
//        List<String> collect = i.stream().flatMap(Arrays::stream).collect(Collectors.toList());

    }
}
