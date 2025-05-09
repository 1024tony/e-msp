
package com.volvo.emsp.card.model.param;

import com.volvo.emsp.common.model.Response;
import lombok.Getter;
import lombok.Setter;

/**
 * 卡片Param
 *
 * @author gaoyang
 * @since 2025-04-13 20:11:48
 */
@Getter
@Setter
public class CardParam extends Response {

    /**
     * 主键ID
     */
    private Long cardId;

    /**
     * 供应商ID
     */
    private Long supplierId;

    /**
     * 账户ID
     */
    private Long accountId;

    /**
     * 账户编号
     */
    private String accountNo;

    /**
     * 状态 1:激活 2:停用
     */
    private Integer status;
}

