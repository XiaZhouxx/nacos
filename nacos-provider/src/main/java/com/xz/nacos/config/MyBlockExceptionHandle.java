package com.xz.nacos.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.AbstractSentinelInterceptor;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.util.StringUtil;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author xz
 * @ClassName MyBlockExceptionHandle
 * @Description
 * @date 2021/9/23 0023 15:33
 **/
@Component
public class MyBlockExceptionHandle implements BlockExceptionHandler {
    /**
     * 该配置是针对于SpringMVC体系的BlockException处理. Sentinel会默认生成对于MVC接口的处理
     * {@link AbstractSentinelInterceptor}
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        response.setStatus(429);
        StringBuffer url = request.getRequestURL();
        if ("GET".equals(request.getMethod()) && StringUtil.isNotBlank(request.getQueryString())) {
            url.append("?").append(request.getQueryString());
        }

        PrintWriter out = response.getWriter();
        out.print("request fail");
        out.flush();
        out.close();
    }
}
