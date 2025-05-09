package com.volvo.emsp.account.model.param;

import com.volvo.emsp.common.model.Response;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 账户Param
 *
 * @author gaoyang
 * @since 2025-04-13 20:01:43
 */
@Getter
@Setter
public class AccountParam extends Response {

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
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 邮箱
     */
    @Email
    @NotBlank(message = "邮箱不能为空")
    private String email;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    private String phone;

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

