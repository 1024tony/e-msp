package com.volvo.emsp.card.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.volvo.emsp.card.entity.Card;
import com.volvo.emsp.card.model.param.CardAssignParam;
import com.volvo.emsp.card.model.param.CardParam;
import com.volvo.emsp.card.model.param.CardQryParam;
import com.volvo.emsp.card.model.vo.CardVO;
import com.volvo.emsp.common.model.PageResult;

import javax.validation.Valid;

/**
 * 卡片表(Card)服务接口
 *
 * @author gaoyang
 * @since 2025-04-13 20:11:48
 */
public interface CardService extends IService<Card> {

    /**
     * 激活卡片
     *
     * @param cardNo 卡片编码
     */
    void enableCard(String cardNo);

    /**
     * 停用卡片
     *
     * @param cardNo 卡片编码
     */
    void disableCard(String cardNo);

    /**
     * 分配卡片给账户
     *
     * @param param CardAssignParam
     */
    void assignAccount(CardAssignParam param);

    /**
     * 创建卡片
     *
     * @param cardParam CardParam
     */
    void createCard(CardParam cardParam);

    /**
     * 分页查询
     *
     * @param param CardQryParam
     * @return PageResult<CardVO>
     */
    PageResult<CardVO> pageCard(CardQryParam param);
}

