package io.scode.imageconvertservice.utils.validator;

import io.scode.imageconvertservice.utils.ValidateTestDataCreator;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EncodedSourceValidatorTests {

    EncodedSourceValidator validator = new EncodedSourceValidator();
    @Mock
    ConstraintValidatorContext context;

    static Stream<String> getSafeStrings() {
        ValidateTestDataCreator factory = new ValidateTestDataCreator();
        return factory.getEncodedStrings(factory.getSafeStrings()).stream();
    }

    static Stream<String> getUnsafeStrings() {
        ValidateTestDataCreator factory = new ValidateTestDataCreator();
        return factory.getEncodedStrings(factory.getUnSafeStrings()).stream();
    }

    @ParameterizedTest
    @MethodSource("getSafeStrings")
    @DisplayName("Safety data validation test")
    void test1(String source) throws Exception {
        assertTrue(validator.isValid(source, context));
    }

    @ParameterizedTest
    @MethodSource("getUnsafeStrings")
    @DisplayName("Unsafe data validation test")
    void test2(String source) throws Exception {
        assertFalse(validator.isValid(source, context));
    }

}