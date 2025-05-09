package com.volvo.emsp.card.model.vo;

import com.volvo.emsp.common.model.Response;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 卡片VO
 *
 * @author gaoyang
 * @since 2025-04-13 20:11:48
 */
@Getter
@Setter
public class CardVO extends Response {

    /**
     * 主键ID
     */
    private Long cardId;

    /**
     * 卡片编码
     */
    private String cardNo;

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

