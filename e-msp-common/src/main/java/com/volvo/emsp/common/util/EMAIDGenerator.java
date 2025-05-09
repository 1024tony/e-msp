package com.volvo.emsp.common.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yang4.gao
 * @since 2025/4/26 15:29
 */
public class EMAIDGenerator {

    private static final String EMAID_PATTERN = "[A-Z]{2}[\\dA-Z]{3}[\\dA-Z]{9}";
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Random random = new Random();

    /**
     * 生成EMAID
     *
     * @return EMAID
     */
    public static String generateEMAID(String countryCode, Long supplierId, Long cardId) {
        /*// 国家地区代码 todo
        for (int i = 0; i < 2; i++) {
            emaid.append(CHARACTERS.charAt(random.nextInt(26)));
        }
        // 供应商ID todo
        for (int i = 0; i < 3; i++) {
            emaid.append(CHARACTERS.charAt(random.nextInt(36)));
        }
        // 表自增ID + 随机补齐前9位 todo
        for (int i = 0; i < 9; i++) {
            emaid.append(CHARACTERS.charAt(random.nextInt(36)));
        }*/

        return countryCode + String.format("%03d", supplierId) + String.format("%09d", cardId);
    }

    /**
     * 验证生成的EMAID是否符合格式
     *
     * @param emaid EMAID
     * @return boolean
     */
    public static boolean isValidEMAID(String emaid) {
        Pattern pattern = Pattern.compile(EMAID_PATTERN);
        Matcher matcher = pattern.matcher(emaid);
        return matcher.matches();
    }
}
