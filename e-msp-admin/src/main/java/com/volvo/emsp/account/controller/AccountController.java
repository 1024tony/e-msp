package com.volvo.emsp.account.controller;

import com.volvo.emsp.account.model.param.AccountNoParam;
import com.volvo.emsp.account.model.param.AccountParam;
import com.volvo.emsp.account.model.param.AccountQryParam;
import com.volvo.emsp.account.model.vo.AccountVO;
import com.volvo.emsp.account.service.AccountService;
import com.volvo.emsp.common.model.PageResult;
import com.volvo.emsp.common.model.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 账户API
 *
 * @author gaoyang
 * @since 2025-04-13 20:01:40
 */
@RestController
@RequestMapping("account")
public class AccountController {

    @Resource
    private AccountService accountService;

    /**
     * 分页查询
     */
    @PostMapping("/page")
    public Result<PageResult<AccountVO>> pageAccount(@RequestBody AccountQryParam param) {
        PageResult<AccountVO> page = accountService.pageAccount(param);

        return Result.success(page);
    }

    /**
     * 创建账户
     */
    @PostMapping("/create")
    public Result<Void> createAccount(@RequestBody @Valid AccountParam param) {
        accountService.createAccount(param);

        return Result.success();
    }

    /**
     * 激活账户
     */
    @PostMapping("/enable")
    public Result<Void> enableAccount(@RequestBody @Valid AccountNoParam param) {
        accountService.enableAccount(param.getAccountNo());

        return Result.success();
    }

    /**
     * 停用账户
     */
    @PostMapping("/disable")
    public Result<Void> disableAccount(@RequestBody @Valid AccountNoParam param) {
        accountService.disableAccount(param.getAccountNo());

        return Result.success();
    }
}

