package com.volvo.emsp.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 基础异常封装
 *
 * @author gaoyang
 * @since 2025-04-12 13:27
 */
@Getter
@Setter
public class BizException extends RuntimeException {

    /**
     * 错误码枚举
     */
    private ErrorCode errCode;

    public BizException(String message) {
        super(message);
        this.errCode = ErrorCodeEnum.ERROR;
    }

    public BizException(ErrorCode errCode) {
        super(errCode.getMessage());
        this.errCode = errCode;
    }

    public BizException(ErrorCode errCode, String message) {
        super(message);
        this.errCode = errCode;
    }

    public BizException(Throwable e) {
        super(e);
    }

    public BizException(String message, Throwable e) {
        super(message, e);
    }

    public BizException(ErrorCode errCode, String message, Throwable e) {
        super(message, e);
        this.errCode = errCode;
    }
}
