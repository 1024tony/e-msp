package com.volvo.emsp.common.exception;

import lombok.Getter;

/**
 * 错误码枚举
 *
 * @author gaoyang
 * @since 2025-04-12 13:27
 */
@Getter
public enum ErrorCodeEnum implements ErrorCode {

    /**
     * 错误码枚举
     */
    SUCCESS(0, "操作成功"),

    ERROR(-1, "操作失败"),

    UNKNOWN(100000, "未知错误"),

    PARAMETER_ILLEGAL(100001, "参数非法"),

    TOKEN_VERIFY_FAILED(100002, "Token校验失败"),

    UN_AUTHENTICATION(100401, "尚未登录，请登录后访问"),

    UN_AUTHORIZATION(100403, "权限不足，请联系管理员");

    /**
     * 错误码
     */
    private final Integer code;

    /**
     * 错误消息
     */
    private final String message;

    ErrorCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过 errCode 查找枚举
     */
    public static ErrorCodeEnum getByCode(Integer code) {
        for (ErrorCodeEnum errCode : values()) {
            if (errCode.getCode().equals(code)) {
                return errCode;
            }
        }
        return ErrorCodeEnum.UNKNOWN;
    }
}
