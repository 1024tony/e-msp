package com.volvo.emsp.common.util;

import com.volvo.emsp.common.exception.BizException;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;

/**
 * 参数校验工具类
 *
 * @author gaoyang
 * @since 2025-04-12 13:27
 */
public class ValidatorUtil {

    private static final Validator VALIDATOR = Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();

    private ValidatorUtil() {
    }

    public static <T> void validate(T obj) {
        Set<ConstraintViolation<T>> constraintViolations = VALIDATOR.validate(obj);
        if (CollectionUtils.isEmpty(constraintViolations)) {
            return;
        }

        Optional<ConstraintViolation<T>> optional = constraintViolations.stream().findFirst();
        if (optional.isPresent()) {
            throw new BizException(optional.get().getMessage());
        }
    }
}
