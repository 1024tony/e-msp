package com.volvo.emsp.account.model.param;

import com.volvo.emsp.common.model.PageRequest;
import com.volvo.emsp.common.model.Request;
import lombok.Getter;
import lombok.Setter;

/**
 * 账号分页查询
 *
 * @author yang4.gao
 * @since 2025/4/13 20:06
 */
@Getter
@Setter
public class AccountQryParam extends PageRequest {

    /**
     * 账户编码
     */
    private String accountNo;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 状态 1:启用 2:禁用
     */
    private Integer status;
}
