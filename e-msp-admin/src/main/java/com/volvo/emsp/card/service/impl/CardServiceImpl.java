package com.volvo.emsp.card.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.volvo.emsp.account.entity.Account;
import com.volvo.emsp.account.service.AccountService;
import com.volvo.emsp.card.entity.Card;
import com.volvo.emsp.card.mapper.CardMapper;
import com.volvo.emsp.card.model.param.CardAssignParam;
import com.volvo.emsp.card.model.param.CardParam;
import com.volvo.emsp.card.model.param.CardQryParam;
import com.volvo.emsp.card.model.vo.CardVO;
import com.volvo.emsp.card.service.CardService;
import com.volvo.emsp.common.enums.EnableStatusEnum;
import com.volvo.emsp.common.enums.IsDeletedEnum;
import com.volvo.emsp.common.exception.BizException;
import com.volvo.emsp.common.model.PageResult;
import com.volvo.emsp.common.util.EMAIDGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Objects;

/**
 * 卡片表(Card)服务实现类
 *
 * @author gaoyang
 * @since 2025-04-13 20:11:48
 */
@Service
public class CardServiceImpl extends ServiceImpl<CardMapper, Card> implements CardService {

    @Resource
    private AccountService accountService;

    @Override
    public PageResult<CardVO> pageCard(CardQryParam param) {
        Page<Card> page = new Page<>();
        page.setCurrent(param.getPageNum());
        page.setSize(param.getPageSize());

        LambdaQueryWrapper<Card> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Card::getIsDeleted, IsDeletedEnum.NO.getCode())
                .eq(StringUtils.isNotBlank(param.getAccountNo()), Card::getAccountNo, param.getAccountNo())
                .eq(StringUtils.isNotBlank(param.getCardNo()), Card::getCardNo, param.getCardNo())
                .eq(Objects.nonNull(param.getStatus()), Card::getStatus, param.getStatus())
                .orderByDesc(Card::getId);

        Page<Card> iPage = this.page(page, queryWrapper);

        PageResult<CardVO> pageResult = new PageResult<>();
        pageResult.setTotalPage((int) iPage.getPages());
        pageResult.setTotalCount((int) iPage.getTotal());
        pageResult.setPageSize((int) iPage.getSize());
        pageResult.setPageNum((int) iPage.getCurrent());
        if (iPage.getTotal() == 0) {
            pageResult.setList(Collections.emptyList());
        }

        pageResult.setList(Convert.toList(CardVO.class, iPage.getRecords()));

        return pageResult;
    }

    @Override
    public void enableCard(String cardNo) {
        Card card = this.lambdaQuery().eq(Card::getIsDeleted, IsDeletedEnum.NO.getCode())
                .eq(Card::getCardNo, cardNo)
                .one();
        if (Objects.isNull(card)) {
            throw new BizException("卡片不存在");
        }
        if (card.getStatus().equals(EnableStatusEnum.ENABLE.getCode())) {
            throw new BizException("卡片已启用");
        }

        boolean update = this.lambdaUpdate().eq(Card::getCardNo, cardNo)
                .set(Card::getStatus, EnableStatusEnum.ENABLE.getCode())
                .update();
        if (!update) {
            throw new BizException("启用卡片失败");
        }
    }

    @Override
    public void disableCard(String cardNo) {
        Card card = this.lambdaQuery().eq(Card::getIsDeleted, IsDeletedEnum.NO.getCode())
                .eq(Card::getCardNo, cardNo)
                .one();
        if (Objects.isNull(card)) {
            throw new BizException("卡片不存在");
        }
        if (card.getStatus().equals(EnableStatusEnum.DISABLE.getCode())) {
            throw new BizException("卡片已停用");
        }

        boolean update = this.lambdaUpdate().eq(Card::getCardNo, cardNo)
                .set(Card::getStatus, EnableStatusEnum.DISABLE.getCode())
                .update();
        if (!update) {
            throw new BizException("停用卡片失败");
        }
    }

    @Override
    public void assignAccount(CardAssignParam param) {
        Card card = this.lambdaQuery().eq(Card::getIsDeleted, IsDeletedEnum.NO.getCode())
                .eq(Card::getCardNo, param.getCardNo()).one();
        if (Objects.isNull(card)) {
            throw new BizException("卡片不存在");
        }
        Account account = accountService.lambdaQuery().eq(Account::getIsDeleted, IsDeletedEnum.NO.getCode())
                .eq(Account::getAccountNo, param.getAccountNo()).one();
        if (Objects.isNull(account)) {
            throw new BizException("账户不存在");
        }

        // 分配账户
        card.setAccountId(account.getId());
        card.setAccountNo(account.getAccountNo());
        card.setModifier("");
        card.setUpdateTime(LocalDateTime.now());

        boolean update = this.updateById(card);
        if (!update) {
            throw new BizException("分配账户失败");
        }
    }

    @Override
    public void createCard(CardParam cardParam) {
        Card card = Convert.convert(Card.class, cardParam);
        card.setCardNo(EMAIDGenerator.generateEMAID("", cardParam.getSupplierId(), 1L));

        this.save(card);
    }
}

