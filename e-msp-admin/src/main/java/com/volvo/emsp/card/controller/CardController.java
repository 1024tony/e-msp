package com.volvo.emsp.card.controller;

import com.volvo.emsp.account.model.vo.AccountVO;
import com.volvo.emsp.card.model.param.CardAssignParam;
import com.volvo.emsp.card.model.param.CardNoParam;
import com.volvo.emsp.card.model.param.CardParam;
import com.volvo.emsp.card.model.param.CardQryParam;
import com.volvo.emsp.card.model.vo.CardVO;
import com.volvo.emsp.card.service.CardService;
import com.volvo.emsp.common.model.PageResult;
import com.volvo.emsp.common.model.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 卡片API
 *
 * @author gaoyang
 * @since 2025-04-13 20:11:46
 */
@RestController
@RequestMapping("/card")
public class CardController {

    @Resource
    private CardService cardService;

    /**
     * 分页查询
     */
    @GetMapping("/page")
    public Result<PageResult<CardVO>> selectAll(@RequestBody @Valid CardQryParam param) {
        PageResult<CardVO> page = cardService.pageCard(param);

        return Result.success(page);
    }

    /**
     * 创建卡片
     */
    @PostMapping("/create")
    public Result<Void> createCard(@RequestBody @Valid CardParam cardParam) {
        cardService.createCard(cardParam);

        return Result.success();
    }

    /**
     * 分配账户
     */
    @PostMapping("/assign/account")
    public Result<Void> assignAccount(@RequestBody @Valid CardAssignParam param) {
        cardService.assignAccount(param);

        return Result.success();
    }

    /**
     * 激活卡片
     */
    @PostMapping("/enable")
    public Result<Void> enableCard(@RequestBody @Valid CardNoParam param) {
        cardService.enableCard(param.getCardNo());

        return Result.success();
    }

    /**
     * 停用卡片
     */
    @PostMapping("/disable")
    public Result<Void> disableCard(@RequestBody @Valid CardNoParam param) {
        cardService.disableCard(param.getCardNo());

        return Result.success();
    }
}

