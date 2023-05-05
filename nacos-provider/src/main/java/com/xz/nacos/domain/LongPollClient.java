package com.xz.nacos.domain;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletResponse;

public class LongPollClient {
    /**
     * http 请求对象
     */
    HttpServletResponse rsp;

    /**
     * 异步上下文
     */
    AsyncContext ac;

    public HttpServletResponse getRsp() {
        return rsp;
    }

    public void setRsp(HttpServletResponse rsp) {
        this.rsp = rsp;
    }

    public AsyncContext getAc() {
        return ac;
    }

    public void setAc(AsyncContext ac) {
        this.ac = ac;
    }
}
