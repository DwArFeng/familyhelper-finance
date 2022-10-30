package com.dwarfeng.familyhelper.finance.sdk.util;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 提醒范围类型字段有效性验证注解。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
@Documented
@Constraint(
        validatedBy = {ValidRemindScopeType.InternalConstraintValidator.class}
)
@Target({
        ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
        ElementType.PARAMETER, ElementType.TYPE_USE
})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRemindScopeType {

    String message() default "invalid data type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class InternalConstraintValidator implements ConstraintValidator<ValidRemindScopeType, Integer> {

        // 执行校验操作
        @Override
        public boolean isValid(Integer value, ConstraintValidatorContext context) {
            try {
                return Constants.remindScopeTypeSpace().contains(value);
            } catch (Exception e) {
                return false;
            }
        }
    }
}
