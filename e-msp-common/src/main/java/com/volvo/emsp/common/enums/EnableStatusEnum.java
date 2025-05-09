package com.volvo.emsp.common.enums;

import lombok.Getter;

/**
 * 启用状态
 *
 * @author gaoyang
 * @since 2025-04-12 13:27
 */
@Getter
public enum EnableStatusEnum {

    /**
     * 未知
     */
    UNKNOWN(0, "未知"),

    /**
     * 启用
     */
    ENABLE(1, "启用"),

    /**
     * 禁用
     */
    DISABLE(2, "禁用");

    /**
     * 编码
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String desc;

    EnableStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 通过 code 查找枚举
     */
    public static EnableStatusEnum getByCode(Integer code) {
        for (EnableStatusEnum e : values()) {
            if (e.code.intValue() == code) {
                return e;
            }
        }
        return UNKNOWN;
    }
}