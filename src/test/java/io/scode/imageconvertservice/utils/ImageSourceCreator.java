package io.scode.imageconvertservice.utils;

import io.scode.imageconvertservice.config.properties.ConverterProperties;
import io.scode.imageconvertservice.service.v1.ConverterV1;
import lombok.Getter;

import java.net.URL;
public class ImageSourceCreator {

    public static String DEFAULT_FONT_PATH = "/fonts/Pretendard-Regular.woff";

    public String getBasicSource() {
        return DataCodec.encodeBase64("""
                <html>
                <head>
                <style>
                div {
                  background-color: 'green';
                  color: 'white';
                  width:100px;
                  height:100px;
                }
                </style>
                </head>
                <body>
                <div>
                  ss-code
                </div>
                </body>
                </html>""");
    }

    public String getBgImageSource() {
        return DataCodec.encodeBase64("""
                <html>
                <head>
                <style>
                div {
                  background-image:url('https://picsum.photos/200/300');
                  width:100px;
                  height:100px;
                }
                </style>
                </head>
                <body>
                <div>
                  에스코드
                </div>
                </body>
                </html>
                """);
    }

    public String getFontSource() {

        String source = """
                <html>
                <head>
                <style>
                @font-face {
                    font-family: 'defaultFont';
                    src: url('%s');
                    font-weight: 400;
                    font-style: normal;
                }
                
                div {
                  font-family: 'defaultFont';
                  width:100px;
                  height:100px;
                  background-color:black;
                  color: white;
                }
                
                </style>
                </head>
                <body>
                <div>
                  에스코드 폰트 적용
                </div>
                </body>
                </html>
                """.formatted(getDefaultFontUrl());

        return DataCodec.encodeBase64(source);
    }

    public static URL getDefaultFontUrl() {
        return ConverterV1.class.getResource(DEFAULT_FONT_PATH);
    }

    public ConverterProperties getConverterProperties() {
        ConverterProperties converterProperties = new ConverterProperties();
        ConverterProperties.Fonts fonts = new ConverterProperties.Fonts();
        fonts.setDefaultPath(DEFAULT_FONT_PATH);
        converterProperties.setFonts(fonts);
        return converterProperties;
    }

}
