package com.volvo.emsp.account.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 账户表(Account)实体类
 *
 * @author gaoyang
 * @since 2025-04-13 20:01:43
 */
@Getter
@Setter
public class Account implements Serializable {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

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

    /**
     * 是否删除 0:否 1:是
     */
    private Integer isDeleted;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}

