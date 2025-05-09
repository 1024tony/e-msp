package com.volvo.emsp.common.enums;

import lombok.Getter;

/**
 * 删除
 *
 * @author gaoyang
 * @since 2025-04-12 13:27
 */
@Getter
public enum IsDeletedEnum {

    /**
     * 否
     */
    NO(0, "否"),

    /**
     * 是
     */
    YES(1, "是");

    /**
     * 编码
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String desc;


    IsDeletedEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 通过 code 查找枚举
     */
    public static IsDeletedEnum getByCode(Integer code) {
        for (IsDeletedEnum e : values()) {
            if (e.code.intValue() == code) {
                return e;
            }
        }
        return null;
    }
}
