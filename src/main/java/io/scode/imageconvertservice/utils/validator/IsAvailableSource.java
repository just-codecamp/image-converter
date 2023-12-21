package io.scode.imageconvertservice.utils.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EncodedSourceValidator.class)
public @interface IsAvailableSource {

    String message() default "데이터는 반드시 Base64 방식으로 인코딩 되어있어야 하며, <div> 혹은 <style> 태그로 시작 하여야 합니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
