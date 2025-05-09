package com.volvo.emsp;

import com.alibaba.fastjson.JSON;
import com.volvo.emsp.card.model.param.CardAssignParam;
import com.volvo.emsp.card.model.param.CardParam;
import com.volvo.emsp.card.model.param.CardQryParam;
import com.volvo.emsp.card.model.vo.CardVO;
import com.volvo.emsp.card.service.CardService;
import com.volvo.emsp.common.model.PageResult;
import com.volvo.emsp.common.util.EMAIDGenerator;
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
public class CardTest {

    @Resource
    private CardService cardService;

    @Test
    public void generateEMAID() {
        log.info(EMAIDGenerator.generateEMAID("CN", 1L, 1L));
    }

    @Test
    public void createCard() {
        CardParam cardParam = new CardParam();
        //cardParam.setCardId(1L);
        cardParam.setSupplierId(2L);
        //cardParam.setAccountId(1L);
        //cardParam.setAccountNo("C00001");
        cardParam.setStatus(2);
        cardService.createCard(cardParam);
    }

    @Test
    public void assignAccount() {
        CardAssignParam cardParam = new CardAssignParam();
        cardParam.setCardNo("C00001");
        cardParam.setAccountNo("A00001");
        cardService.assignAccount(cardParam);
    }

    @Test
    public void enableCard() {
        cardService.enableCard("CN001000000001");
    }

    @Test
    public void disableCard() {
        cardService.disableCard("CN001000000001");
    }

    @Test
    public void pageCard() {
        CardQryParam param = new CardQryParam();
        param.setPageNum(1);
        param.setPageSize(10);
        PageResult<CardVO> result = cardService.pageCard(param);
        log.info("result: {}", JSON.toJSONString(result));
    }
}
