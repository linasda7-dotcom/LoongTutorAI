package com.example.dto;

public class ApiResult<T> {

    private int code;
    private String msg;
    private T data;

    public ApiResult() {
    }

    public ApiResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 默认成功返回 无数据
     * 
     * @param msg
     * @return
     */
    public static <T> ApiResult<T> success() {
        return new ApiResult<>(200, "成功", null);
    }

    /**
     * 全量自定义成功
     * 
     * @param <T>  类型
     * @param code 成功代码
     * @param msg  成功消息
     * @param data 成功数据
     * @return 统一响应
     */
    public static <T> ApiResult<T> success(int code, String msg, T data) {
        return new ApiResult<T>(code, msg, data);
    }

    /**
     * 自定义成功信息
     * 
     * @param <T>  泛型
     * @param msg  成功消息
     * @param data 成功数据
     * @return 统一返回
     */
    public static <T> ApiResult<T> success(String msg, T data) {
        return new ApiResult<>(200, msg, data);
    }

    /**
     * 默认失败
     * 服务器错误失败
     * 
     * @param <T> 泛型
     * @return 统一失败返回
     */
    public static <T> ApiResult<T> failure() {
        return new ApiResult<T>(500, "服务器错误", null);
    }

    /**
     * 自定义失败返回
     * 
     * @param <T>  泛型
     * @param msg  失败原因
     * @param data 失败具体信息
     * @return 统一失败返回
     */
    public static <T> ApiResult<T> failure(String msg, T data) {
        return new ApiResult<T>(500, msg, data);
    }

    /**
     * 全量自定义返回
     * 
     * @param <T>  泛型
     * @param code 失败代码
     * @param msg  失败原因
     * @param data 失败具体消息
     * @return 统一失败返回
     */
    public static <T> ApiResult<T> failure(int code, String msg, T data) {
        return new ApiResult<T>(code, msg, data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
