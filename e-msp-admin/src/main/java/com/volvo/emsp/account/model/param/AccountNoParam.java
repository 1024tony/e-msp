package com.volvo.emsp.account.model.param;

import com.volvo.emsp.common.model.Request;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 账号分页查询
 *
 * @author yang4.gao
 * @since 2025/4/13 20:06
 */
@Getter
@Setter
public class AccountNoParam extends Request {

    /**
     * 账户编码
     */
    @NotBlank(message = "账户编码不能为空")
    private String accountNo;
}
