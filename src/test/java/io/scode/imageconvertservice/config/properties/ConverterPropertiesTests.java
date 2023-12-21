package io.scode.imageconvertservice.config.properties;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@EnableConfigurationProperties(value = { ConverterProperties.class })
class ConverterPropertiesTests {

    @Autowired
    ConverterProperties converterProperties;

    @Test
    @DisplayName("Check property values")
    void test1() throws Exception {
        assertThat(converterProperties.getFonts().getDefaultPath()).isNotBlank();
        assertThat(converterProperties.getFonts().getDefaultPath().equals("/fonts/Pretendard-Regular.woff"));
    }

}