package com.volvo.emsp.card.model.param;

import com.volvo.emsp.common.model.Request;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 卡片查询
 *
 * @author gaoyang
 * @since 2025-04-13 20:11:48
 */
@Getter
@Setter
public class CardNoParam extends Request {

    /**
     * 卡片编码
     */
    @NotBlank(message = "卡片编码不能为空")
    private String cardNo;
}

