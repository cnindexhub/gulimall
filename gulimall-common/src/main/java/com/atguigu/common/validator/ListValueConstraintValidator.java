package com.atguigu.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * @author DengPengFei
 * @Description 验证有限结果集
 * @email DengTPengTFei@qq.com
 * @date 2023-03-25 00:08
 */
public class ListValueConstraintValidator implements ConstraintValidator<ListValue, Integer> {

    /**
     * 有限结果集
     */
    Set<Integer> set = new HashSet();

    @Override
    public void initialize(ListValue constraintAnnotation) {
        int[] values = constraintAnnotation.values();
        for (int item: values) {
            set.add(item);
        }
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {

        return set.contains(integer);
    }
}
