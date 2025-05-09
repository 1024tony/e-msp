package com.volvo.emsp.account.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.volvo.emsp.account.entity.Account;
import com.volvo.emsp.account.mapper.AccountMapper;
import com.volvo.emsp.account.model.param.AccountParam;
import com.volvo.emsp.account.model.param.AccountQryParam;
import com.volvo.emsp.account.model.vo.AccountVO;
import com.volvo.emsp.account.service.AccountService;
import com.volvo.emsp.common.enums.EnableStatusEnum;
import com.volvo.emsp.common.enums.IsDeletedEnum;
import com.volvo.emsp.common.exception.BizException;
import com.volvo.emsp.common.model.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 账户表(Account)服务实现类
 *
 * @author gaoyang
 * @since 2025-04-13 20:01:43
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Override
    public PageResult<AccountVO> pageAccount(AccountQryParam param) {
        Page<Account> page = new Page<>();
        page.setCurrent(param.getPageNum());
        page.setSize(param.getPageSize());

        LambdaQueryWrapper<Account> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Account::getIsDeleted, IsDeletedEnum.NO.getCode())
                .eq(StringUtils.isNotBlank(param.getAccountNo()), Account::getAccountNo, param.getAccountNo())
                .eq(StringUtils.isNotBlank(param.getPhone()), Account::getPhone, param.getPhone())
                .eq(StringUtils.isNotBlank(param.getEmail()), Account::getEmail, param.getEmail())
                .eq(Objects.nonNull(param.getStatus()), Account::getStatus, param.getStatus())
                .orderByDesc(Account::getId);

        IPage<Account> iPage = this.page(page, queryWrapper);

        PageResult<AccountVO> pageResult = new PageResult<>();
        pageResult.setTotalPage((int) iPage.getPages());
        pageResult.setTotalCount((int) iPage.getTotal());
        pageResult.setPageSize((int) iPage.getSize());
        pageResult.setPageNum((int) iPage.getCurrent());
        if (iPage.getTotal() == 0) {
            pageResult.setList(Collections.emptyList());
        }

        pageResult.setList(Convert.toList(AccountVO.class, iPage.getRecords()));

        return pageResult;
    }

    @Override
    public void enableAccount(String accountNo) {
        Account account = this.lambdaQuery().eq(Account::getIsDeleted, IsDeletedEnum.NO.getCode())
                .eq(Account::getAccountNo, accountNo)
                .one();
        if (Objects.isNull(account)) {
            throw new BizException("账户不存在");
        }
        if (EnableStatusEnum.ENABLE.getCode().equals(account.getStatus())) {
            throw new BizException("账户已激活");
        }

        boolean update = this.lambdaUpdate().eq(Account::getAccountNo, accountNo)
                .set(Account::getStatus, EnableStatusEnum.ENABLE.getCode())
                .update();
        if (!update) {
            throw new BizException("激活账户失败");
        }
    }

    @Override
    public void disableAccount(String accountNo) {
        Account account = this.lambdaQuery().eq(Account::getIsDeleted, IsDeletedEnum.NO.getCode())
                .eq(Account::getAccountNo, accountNo)
                .one();
        if (Objects.isNull(account)) {
            throw new BizException("账户不存在");
        }
        if (EnableStatusEnum.DISABLE.getCode().equals(account.getStatus())) {
            throw new BizException("账户已停用");
        }

        boolean update = this.lambdaUpdate().eq(Account::getAccountNo, accountNo)
                .set(Account::getStatus, EnableStatusEnum.DISABLE.getCode())
                .update();
        if (!update) {
            throw new BizException("停用账户失败");
        }
    }

    @Override
    public void createAccount(AccountParam param) {
        Account account = Convert.convert(Account.class, param);

        boolean save = this.save(account);
        if (!save) {
            throw new BizException("创建账户失败");
        }
    }
}

