package io.scode.imageconvertservice.api.v1;

import io.scode.imageconvertservice.config.properties.ConverterProperties;
import io.scode.imageconvertservice.dto.ImageSource;
import io.scode.imageconvertservice.service.interfaces.Converter;
import io.scode.imageconvertservice.service.v1.ConverterV1;
import io.scode.imageconvertservice.utils.ImageSourceCreator;
import io.scode.imageconvertservice.utils.RequestDataCreator;
import io.scode.imageconvertservice.utils.RequestDataFactory;
import io.scode.imageconvertservice.utils.ValidateTestDataCreator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class ImageConvertControllerTests {

    @Autowired
    MockMvc mockMvc;

    long startTimeLap;
    long startMemLap;

    @BeforeEach
    void init() {
        startTimeLap = System.currentTimeMillis();
        startMemLap = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        log.info("Start memory laps :: {}MB", startMemLap / (1024 * 1024));
    }

    @AfterEach
    void endMethod() {
        long endTimeLap = System.currentTimeMillis();
        long endMemLap = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        log.info("Test time taken :: {}s", (endTimeLap - startTimeLap) / 1000.0);
        log.info("Memory used :: {}MB", (endMemLap - startMemLap) / (1024 * 1024));
    }

    static Stream<ImageSource> getSafeImageSource() {
        RequestDataFactory factory = new RequestDataFactory();
        return factory.getSafeImageSources().stream();
    }

    @Disabled
    @ParameterizedTest
    @MethodSource("getSafeImageSource")
    @DisplayName("Safe source validation test")
    void test1(ImageSource source) throws Exception {
        ResultActions actions = mockMvc.perform(get("/v1/images")
                        .param("source", source.getSource())
                        .param("width", source.getWidth().toString())
                        .param("height", source.getHeight().toString()));

        actions.andExpect(status().isCreated());
    }



}