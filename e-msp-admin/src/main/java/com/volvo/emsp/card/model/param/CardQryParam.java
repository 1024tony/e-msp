package com.volvo.emsp.card.model.param;

import com.volvo.emsp.common.model.PageRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * 卡片查询
 *
 * @author gaoyang
 * @since 2025-04-13 20:11:48
 */
@Getter
@Setter
public class CardQryParam extends PageRequest {

    /**
     * 卡片编码
     */
    private String cardNo;

    /**
     * 账户编号
     */
    private String accountNo;

    /**
     * 状态 1:激活 2:停用
     */
    private Integer status;
}

