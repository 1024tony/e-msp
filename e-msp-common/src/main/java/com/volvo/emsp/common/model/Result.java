package com.volvo.emsp.common.model;

import com.alibaba.fastjson.JSONObject;
import com.volvo.emsp.common.exception.ErrorCode;
import com.volvo.emsp.common.exception.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 统一响应结果
 *
 * @author gaoyang
 * @since 2025-04-12 13:27
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 响应状态码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应结果
     */
    private T data;

    public static Result<Void> success() {
        return new Result<>(true, ErrorCodeEnum.SUCCESS.getCode(), ErrorCodeEnum.SUCCESS.getMessage(), null);
    }

    public static Result<Void> success(String message) {
        return new Result<>(true, ErrorCodeEnum.SUCCESS.getCode(), message, null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(true, ErrorCodeEnum.SUCCESS.getCode(), ErrorCodeEnum.SUCCESS.getMessage(), data);
    }

    public static <T> Result<T> success(T data, String message) {
        return new Result<>(true, ErrorCodeEnum.SUCCESS.getCode(), message, data);
    }

    public static Result<Void> error() {
        return new Result<>(false, ErrorCodeEnum.ERROR.getCode(), ErrorCodeEnum.ERROR.getMessage(), null);
    }

    public static Result<Void> error(String message) {
        return new Result<>(false, ErrorCodeEnum.ERROR.getCode(), message, null);
    }

    public static Result<Void> error(Integer code, String message) {
        return new Result<>(false, code, message, null);
    }

    public static Result<Void> error(ErrorCode errorCode) {
        return new Result<>(false, errorCode.getCode(), errorCode.getMessage(), null);
    }

    public T getData() {
        return this.data == null ? (T) new JSONObject() : this.data;
    }
}
