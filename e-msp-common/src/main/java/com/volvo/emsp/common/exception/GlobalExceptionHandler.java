package com.volvo.emsp.common.exception;

import com.volvo.emsp.common.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;

/**
 * 全局异常捕获处理
 *
 * @author gaoyang
 * @since 2025-04-12 13:27
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 自定义服务异常
     */
    @ExceptionHandler(value = {BizException.class})
    public Result<Void> businessExceptionHandler(BizException e) {
        log.error("[基础服务][统一异常处理] - 自定义服务异常 - ", e);

        return Result.error(e.getErrCode());
    }

    /**
     * 400 - BAD REQUEST Exception
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ConstraintViolationException.class})
    public Result<Void> badRequestHandler(ConstraintViolationException e) {
        log.error("[基础服务][统一异常处理] - 请求错误 - ", e);

        return Result.error(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase());
    }

    /**
     * 404 - NOT FOUND Exception
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {NoHandlerFoundException.class})
    public Result<Void> notFoundHandler(Exception e) {
        log.error("[基础服务][统一异常处理] - 请求未找到 - ", e);

        return Result.error(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result<Void> unsupportedMediaTypeHandler(HttpMediaTypeNotSupportedException e) {
        log.error("[基础服务][统一异常处理] - 不支持的媒体类型 - ", e);

        return Result.error(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase());
    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Result<Void> internalServerErrorHandler(Exception e) {
        log.error("[基础服务][统一异常处理] - 服务器内部错误 - ", e);

        return Result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    /**
     * 参数校验异常
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public Result<Void> argumentVerifyExceptionHandler(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        assert fieldError != null;
        log.error("[基础服务][统一异常处理] - 参数校验异常 - {}: {}", fieldError.getField(), fieldError.getDefaultMessage());

        return Result.error(ErrorCodeEnum.PARAMETER_ILLEGAL.getCode(), fieldError.getDefaultMessage());
    }

    /**
     * 参数绑定异常
     */
    @ExceptionHandler(value = {BindException.class})
    public Result<Void> bindExceptionHandler(BindException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        assert fieldError != null;
        log.error("[基础服务][统一异常处理] - 参数绑定异常 - {}: {}", fieldError.getField(), fieldError.getDefaultMessage());

        return Result.error(ErrorCodeEnum.PARAMETER_ILLEGAL.getCode(), fieldError.getDefaultMessage());
    }
}
