package com.xz.nacos.domain;

/**
 * @author xz
 * @ClassName BaseResult
 * @Description
 * @date 2020/12/6 13:48
 **/
public class BaseResult<T> {
    private String code;

    private String msg;

    private T data;

    public static BaseResult success() {
        BaseResult result = new BaseResult();
        result.code = "200";
        return result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
