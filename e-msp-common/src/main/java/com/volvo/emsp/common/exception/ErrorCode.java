package com.volvo.emsp.common.exception;

/**
 * 错误码接口
 *
 * @author gaoyang
 * @since 2025-04-12 13:27
 */
public interface ErrorCode {

    /**
     * 获取错误码
     *
     * @return 错误码
     */
    Integer getCode();

    /**
     * 获取错误消息
     *
     * @return 错误消息
     */
    String getMessage();
}
