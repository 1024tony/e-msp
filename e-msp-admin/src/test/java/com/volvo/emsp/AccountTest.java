package com.volvo.emsp;

import com.alibaba.fastjson.JSON;
import com.volvo.emsp.account.model.param.AccountParam;
import com.volvo.emsp.account.model.param.AccountQryParam;
import com.volvo.emsp.account.model.vo.AccountVO;
import com.volvo.emsp.account.service.AccountService;
import com.volvo.emsp.common.model.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author yang4.gao
 * @since 2025-04-12 13:27
 */
@Slf4j
@SpringBootTest(classes = EMSPApplication.class)
public class AccountTest {

    @Resource
    private AccountService accountService;

    @Test
    public void createAccount() {
        AccountParam param = new AccountParam();
        param.setAccountNo("test");
        param.setUsername("test");
        param.setPassword("test");
        param.setEmail("test@gmail.com");
        param.setPhone("15233546543");
        param.setAuthLevel("test");
        param.setStatus(2);
        accountService.createAccount(param);
    }

    @Test
    public void enableAccount() {
        accountService.enableAccount("test");
    }

    @Test
    public void disableAccount() {
        accountService.disableAccount("test");
    }

    @Test
    public void pageAccount() {
        AccountQryParam param = new AccountQryParam();
        param.setPageNum(1);
        param.setPageSize(10);
        PageResult<AccountVO> pageResult = accountService.pageAccount(param);
        log.info("pageResult:{}", JSON.toJSONString(pageResult));
    }
}
