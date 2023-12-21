package io.scode.imageconvertservice.service.v1;

import io.scode.imageconvertservice.config.exceptions.FileProcessException;
import io.scode.imageconvertservice.config.exceptions.ImageWriteException;
import io.scode.imageconvertservice.config.properties.ConverterProperties;
import io.scode.imageconvertservice.service.interfaces.Converter;
import io.scode.imageconvertservice.dto.ImageSource;
import io.scode.imageconvertservice.utils.DataCodec;
import io.scode.imageconvertservice.utils.DocumentParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.simple.Graphics2DRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConverterV1 implements Converter {

    private final String HTML = ".html";
    private final String TEMP_FILE = "temp_file";

    private final ConverterProperties converterProperties;

    @Override
    public byte[] convert(ImageSource source) throws Exception {

        String decoded = DataCodec.decodeBase64(source.getSource());

        String document = wrapHtml(decoded.toString());

        try {

            File tmpHtml = File.createTempFile(TEMP_FILE + UUID.randomUUID(), HTML);

            try (OutputStreamWriter htmlWriter =
                         new OutputStreamWriter(new FileOutputStream(tmpHtml), StandardCharsets.UTF_8)) {
                htmlWriter.write(document);
            }

            BufferedImage bufferedImage = Graphics2DRenderer.renderToImage(
                    tmpHtml.getAbsolutePath(), source.getWidth(), source.getHeight());

            return convertToByteArray(bufferedImage);

        } catch (IOException e) {
            throw new FileProcessException("While processing file occurred error.");
        }

    }

    private String wrapHtml(String source) throws IOException {

//        System.setProperty("java.library.path", "/app/resources/fonts");
        //src: url('/usr/share/fonts/Pretendard-SemiBold.otf');

        String styleCss = """
                @font-face {
                    font-family: 'defaultFont';
                    src: url('/usr/share/fonts/Pretendard-Regular.otf') format('opentype');
                    font-weight: 400;
                    font-style: normal;
                }
                """;
//                .formatted(
//                        ConverterV1.class.getResource(
//                            converterProperties.getFonts().getDefaultPath()
//                        )
//                );

        String divCss = """
                div {
                    font-family: 'defaultFont';
                }
                """;

        DocumentParser document = createImageForm(source, styleCss, divCss);

        return document.getDocument().toString();
    }

    private DocumentParser createImageForm(String source, String styleCss, String divStyleProperty) {
        DocumentParser document = new DocumentParser();
        document.setDocument(source, DocumentParser.XML);

        Element div = document.getElement("div");
        Element style = document.getElement("style");

        document.initDocument();

        document.appendElementAtTag(style, DocumentParser.HEAD);
        document.appendElementAtTag(div, DocumentParser.BODY);

        document.appendCssToTag(styleCss, DocumentParser.STYLE);
        document.appendCssToTag(divStyleProperty, DocumentParser.STYLE);

        return document;
    }

    private byte[] convertToByteArray(BufferedImage data) throws ImageWriteException {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            ImageIO.write(data, "png", stream);
        } catch (IOException e) {
            throw new ImageWriteException("While writing image file occurred error.");
        }
        return stream.toByteArray();

    }

    @Override
    public void extractThumbnail() {
        // TODO: Extract Thumbnail image from video
    }

}
