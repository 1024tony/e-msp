package com.volvo.emsp.account.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.volvo.emsp.account.entity.Account;
import com.volvo.emsp.account.model.param.AccountParam;
import com.volvo.emsp.account.model.param.AccountQryParam;
import com.volvo.emsp.account.model.vo.AccountVO;
import com.volvo.emsp.common.model.PageResult;

/**
 * 账户表(Account)服务接口
 *
 * @author gaoyang
 * @since 2025-04-13 20:01:43
 */
public interface AccountService extends IService<Account> {

    /**
     * 分页查询账户信息
     *
     * @param param AccountQryParam
     * @return PageResult
     */
    PageResult<AccountVO> pageAccount(AccountQryParam param);

    /**
     * 激活账户
     *
     * @param accountNo 账户编码
     */
    void enableAccount(String accountNo);

    /**
     * 停用账户
     *
     * @param accountNo 账户编码
     */
    void disableAccount(String accountNo);

    /**
     * 创建账户
     *
     * @param param AccountParam
     */
    void createAccount(AccountParam param);
}

