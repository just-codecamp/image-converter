package io.scode.imageconvertservice.service.v1;

import io.scode.imageconvertservice.dto.ImageSource;
import io.scode.imageconvertservice.utils.ImageSourceCreator;
import io.scode.imageconvertservice.utils.RequestDataCreator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class ConverterJavaTests {

    RequestDataCreator sourceCreator;
    ImageSourceCreator creator;
    ConverterV1 converter;

    long startTimeLap;
    long startMemLap;

    final String TEST_DIR = "tests/outputs";

    @BeforeEach
    void init() {
        creator = new ImageSourceCreator();
        sourceCreator = new RequestDataCreator();
        converter = new ConverterV1(creator.getConverterProperties());
        startTimeLap = System.currentTimeMillis();
        startMemLap = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        log.info("Start memory laps :: {}MB", startMemLap / (1024 * 1024));

        File outputDir = new File(TEST_DIR);

        if ( !outputDir.exists() ) {
            if ( outputDir.mkdirs() ) log.info("ConverterJavaTests test directory created");
            else log.info("ConverterJavaTests test directory creating failed");
        }

    }

    @AfterEach
    void endMethod() {
        long endTimeLap = System.currentTimeMillis();
        long endMemLap = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        log.info("Test time taken :: {}s", (endTimeLap - startTimeLap) / 1000.0);
        log.info("Memory used :: {}MB", (endMemLap - startMemLap) / (1024 * 1024));

        File outputDir = new File(TEST_DIR);

        if ( outputDir.exists() ) {

            File[] files = outputDir.listFiles();
            if (files != null ) {
                for (File file : files) {
                    file.delete();
                }
            }

            if ( outputDir.delete() ) log.info("ConverterJavaTests test directory successfully removed");
            else log.info("ConverterJavaTests test directory removing failed");
        }

    }

    void saveImageToFile(BufferedImage image, String fileName) throws IOException {
        try {
            File outputfile = new File(TEST_DIR + "/" + fileName + ".png");
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            throw new IOException("While saving file occurred error.");
        }
    }

    private ImageSource getImageSource(String source) {
        ImageSource imageSource = new ImageSource();
        imageSource.setSource(source);
        imageSource.setWidth(1000);
        imageSource.setHeight(1000);
        return imageSource;
    }

    @Test
    @DisplayName("Create Basic Image")
    void test1() throws Exception {

        ImageSource imageSource = getImageSource(sourceCreator.getBasicRequest());


        byte[] convertedBytes = converter.convert(imageSource);

        assertThat(convertedBytes).isNotNull();
        assertTrue(convertedBytes.length > 0);

        BufferedImage image = ImageIO.read(new ByteArrayInputStream(convertedBytes));
        saveImageToFile(image, "basic-image-test" + UUID.randomUUID());

    }

    @Test
    @DisplayName("Create image with background image")
    void test2() throws Exception {

        ImageSource imageSource = getImageSource(sourceCreator.getBgImageRequest());

        byte[] convertedBytes = converter.convert(imageSource);

        assertThat(convertedBytes).isNotNull();
        assertTrue(convertedBytes.length > 0);

        BufferedImage image = ImageIO.read(new ByteArrayInputStream(convertedBytes));
        saveImageToFile(image, "bg-image-test" + UUID.randomUUID());

    }

    @Test
    @DisplayName("Create image with background image and default font")
    void test3() throws Exception {

        ImageSource imageSource = getImageSource(sourceCreator.getFontSource());

        byte[] convertedBytes = converter.convert(imageSource);

        assertThat(convertedBytes).isNotNull();
        assertTrue(convertedBytes.length > 0);

        BufferedImage image = ImageIO.read(new ByteArrayInputStream(convertedBytes));
        saveImageToFile(image, "font-image-test" + UUID.randomUUID());

    }

}