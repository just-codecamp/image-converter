package io.scode.imageconvertservice.utils;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class DocumentParserTests {

    DocumentParser documentParser;
    static RequestDataCreator requestDataCreator = new RequestDataCreator();

    @BeforeEach
    void setUp() {
        documentParser = new DocumentParser();
    }

    static Stream<String> getSafeXmlDataStream() {
        return requestDataCreator.getSafeRequestList().stream();
    }

    @Test
    @DisplayName("Get empty html file Test")
    void test1() throws Exception {

        Document document = documentParser.initDocument();

        log.info("document = {}", document);

        assertThat(document).isNotNull();
        assertFalse(document.toString().isEmpty());

    }

    @ParameterizedTest
    @MethodSource("getSafeXmlDataStream")
    @DisplayName("Parse Safe XML tag to HTML tag")
    void test2(String encodedXml) throws Exception {

        String xml = DataCodec.decodeBase64(encodedXml);
        documentParser.setDocument(xml, DocumentParser.XML);

        Element div = documentParser.getElement(DocumentParser.DIV);
        Element style = documentParser.getElement(DocumentParser.STYLE);

        log.info("style = {}", style);
        log.info("div = {}", div);

        assertThat(div).isNotNull();
        assertThat(style).isNotNull();

        String divString = documentParser.getElementString(DocumentParser.DIV);
        String styleString = documentParser.getElementString(DocumentParser.STYLE);

        assertFalse(divString.isEmpty());
        assertFalse(styleString.isEmpty());

        assertThat(div.toString()).isEqualTo(divString);
        assertThat(style.toString()).isEqualTo(styleString);

    }

    @ParameterizedTest
    @MethodSource("getSafeXmlDataStream")
    @DisplayName("Parse Safe XML tag and append New Tag at new document")
    void test3(String encodedXml) throws Exception {

        String xml = DataCodec.decodeBase64(encodedXml);
        documentParser.setDocument(xml, DocumentParser.XML);

        Element div = documentParser.getElement(DocumentParser.DIV);
        Element style = documentParser.getElement(DocumentParser.STYLE);

        log.info("style = {}", style);
        log.info("div = {}", div);

        assertThat(div).isNotNull();
        assertThat(style).isNotNull();

        // Initialize existing document
        documentParser.initDocument();

        documentParser.appendElementAtTag(
                style
                , DocumentParser.HEAD
        );

        documentParser.appendElementAtTag(
                div
                , DocumentParser.BODY
        );

        Document document = documentParser.getDocument();

        log.info("document = {}", document);

        // style
        assertThat(document.head().children().size()).isGreaterThan(0);
        assertThat(document.head().children().first()).isNotNull();

        assertEquals("style", document.head().children().first().nodeName());

        // div
        assertThat(document.body().children().size()).isGreaterThan(0);
        assertThat(document.body().children().first()).isNotNull();

        assertEquals("div", document.body().children().first().nodeName());

    }

    @ParameterizedTest
    @MethodSource("getSafeXmlDataStream")
    @DisplayName("Parse Safe Xml and append new tag that added new properties")
    void test4(String encodedXml) throws Exception {

        String xml = DataCodec.decodeBase64(encodedXml);
        documentParser.setDocument(xml, DocumentParser.XML);

        Element div = documentParser.getElement(DocumentParser.DIV);
        Element style = documentParser.getElement(DocumentParser.STYLE);

        log.info("style = {}", style);
        log.info("div = {}", div);

        assertThat(div).isNotNull();
        assertThat(style).isNotNull();

        // Initialize existing document
        documentParser.initDocument();

        documentParser.appendElementAtTag(
                style
                , DocumentParser.HEAD
        );

        documentParser.appendElementAtTag(
                div
                , DocumentParser.BODY
        );

        String expectedStyleCss = """
                @font-face {
                    font-family: 'defaultFont';
                    src: url('%s');
                    font-weight: 400;
                    font-style: normal;
                }
                """.formatted(
                ImageSourceCreator.getDefaultFontUrl()
        );

        String expectedDivCss = """
                div {
                    font-family: 'defaultFont';
                }
                """;

        documentParser.appendCssToTag(expectedStyleCss, DocumentParser.STYLE);
        documentParser.appendCssToTag(expectedDivCss, DocumentParser.STYLE);

        Document document = documentParser.getDocument();

        log.info("document = {}", document);

        Elements selectedStyle = document.select(DocumentParser.STYLE);

        log.info("merged style = {}", selectedStyle);

        assertTrue(selectedStyle.text().contains("@font-face"));
        assertTrue(selectedStyle.text().contains("font-family: 'defaultFont';"));

    }


}