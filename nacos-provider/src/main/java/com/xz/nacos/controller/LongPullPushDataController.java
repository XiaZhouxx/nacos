package com.xz.nacos.controller;

import com.xz.nacos.domain.LongPollClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 长轮询推送变更数据
 * @author xz
 */
@RestController
public class LongPullPushDataController {
    /**
     * 第一种 通过 DeferredResult 实现
     */

    // 可能一个ID对应多个监听？ guava有提供Multimap 封装了一key对多value. 这里就简单使用HashMap
    Map<String, DeferredResult<String>> watchMap = new HashMap<>();

    private static final Long TIME_OUT = 10000L;

    @RequestMapping("/watch/{id}")
    public DeferredResult<String> watch(@PathVariable String id) {
        // 延迟对象设置超时时间
        DeferredResult<String> deferredResult = new DeferredResult<>(TIME_OUT);
        // 异步请求完成时移除 key，防止内存溢出
        deferredResult.onCompletion(() -> {
            watchMap.remove(id);
        });
        // 注册长轮询请求
        watchMap.put(id, deferredResult);
        return deferredResult;
    }

    @RequestMapping("change/{id}")
    public String change(@PathVariable String id) {
        Optional.ofNullable(watchMap.get(id))
                .map(request -> request.setResult("change id : " + id));

        return "success";
    }

    /**
     * 第二种 通过 request.startAsync() 的AsyncContext实现 实现
     */

    Map<String, LongPollClient> watchMapV2 = new HashMap<>();

    @RequestMapping("/v2/watch/{id}")
    public void watchV2(@PathVariable String id, HttpServletRequest req, HttpServletResponse rsp) {
        AsyncContext asyncContext = req.startAsync();
        // 超时时间. 0L 表示不超时
        asyncContext.setTimeout(0L);

        LongPollClient client = new LongPollClient();
        client.setAc(asyncContext);
        client.setRsp(rsp);
        watchMapV2.put(id, client);
    }

    @RequestMapping("/v2/change/{id}")
    public void changeV2(@PathVariable String id) {
        Optional.ofNullable(watchMapV2.get(id))
                .map(client -> {
                    HttpServletResponse response = client.getRsp();
                    try {
                        // Disable cache.
                        response.setHeader("Pragma", "no-cache");
                        response.setDateHeader("Expires", 0);
                        response.setHeader("Cache-Control", "no-cache,no-store");
                        response.setStatus(HttpServletResponse.SC_OK);
                        response.getWriter().println("change v2 id : " + id);
                    } catch (Exception ex) {
                        System.out.println("error");
                    }
                    client.getAc().complete();
                   return client;
                });
    }
}
