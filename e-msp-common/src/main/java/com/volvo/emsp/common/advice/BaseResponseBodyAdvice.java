package com.volvo.emsp.common.advice;

import com.volvo.emsp.common.annotation.IgnoreResponseAdvice;
import com.volvo.emsp.common.model.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 全局响应拦截器
 *
 * @author gaoyang
 * @since 2025-04-12 13:27
 */
@RestControllerAdvice(value = "com.volvo.emsp")
public class BaseResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 判断是否需要对响应进行处理
     *
     * @param methodParameter methodParameter
     * @param aClass          aClass
     * @return boolean
     */
    @Override
    @SuppressWarnings("all")
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        // 类标识了 IgnoreResponseAdvice 不处理
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }

        // 方法标识了 IgnoreResponseAdvice 不处理
        if (methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }

        return true;
    }

    @Override
    @SuppressWarnings("all")
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 定义最终返回对象
        Result<Object> response = new Result<>();

        if (o == null) {
            return response;
        } else if (o instanceof Result) {
            response = (Result<Object>) o;
        } else {
            response.setData(o);
        }

        return response;
    }
}
