package com.xz.nacos;

import com.google.common.collect.Lists;
import com.xz.nacos.annotation.AutoService;
import com.xz.nacos.annotation.CustomService;
import org.springframework.cloud.openfeign.FeignClient;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xz
 * @date 2023/6/23 11:13
 */
@CustomService
public class TestService {

    public static void main(String[] args) {
        List<int[]> i = new ArrayList<>();
        i.add(new int[]{1,3,5});
        i.add(new int[]{2,4,6});
        // stream流里面的元素也是集合类型时, 单纯的map要么类型不变 要么只能映射为某个单对象
        List<Integer> collect = i.stream().flatMap(id -> {
            List<Integer> i1 = new ArrayList<>();
            for (int ii : id) {
                i1.add(ii);
            }
            return i1.stream();
        }).collect(Collectors.toList());
        System.out.println(collect);

//        List<String[]> i = new ArrayList<>();
//        i.add(new String[]{"1","3","5"});
//        i.add(new String[]{"2","4","6"});
//
//        List<String> collect = i.stream().flatMap(Arrays::stream).collect(Collectors.toList());

    }
}
