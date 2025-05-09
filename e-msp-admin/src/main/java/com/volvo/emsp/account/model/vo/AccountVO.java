package com.volvo.emsp.account.model.vo;

import com.volvo.emsp.common.model.Response;
import lombok.Getter;
import lombok.Setter;

/**
 * 账户VO
 *
 * @author gaoyang
 * @since 2025-04-13 20:01:43
 */
@Getter
@Setter
public class AccountVO extends Response {

    /**
     * 主键ID
     */
    private Long accountId;

    /**
     * 账户编号
     */
    private String accountNo;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 盐，用于个人敏感信息处理
     */
    private String secret;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 认证级别 DEFAULT:默认级别 REAL_NAME:实名认证 ENTERPRISE:企业认证
     */
    private String authLevel;

    /**
     * 状态 1:激活 2:停用
     */
    private Integer status;
}

