package com.smartdz.dzboss.domain;

import java.io.Serializable;

/**
 * 响应基类
 *
 * @author Guangkuo
 */
public class BaseResponse<T> implements Serializable {
    private int result = Code.SUCCESS;
    private String message = "请求成功";
    private T data;

    public BaseResponse() {
    }

    public BaseResponse(int result, String message) {
        this.result = result;
        this.message = message;
    }

    public BaseResponse(int result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
