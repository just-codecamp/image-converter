package io.scode.imageconvertservice.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

public class DocumentParser {

    public static String HEAD = "head";
    public static String BODY = "body";

    public static String HTML = "HTML";
    public static String XML = "XML";

    public static String DIV = "div";
    public static String STYLE = "style";

    private final String htmlForm = "<html>" +
            "<head></head>" +
            "<body></body>" +
            "</html>";

    private Document document;

    public DocumentParser() {
        this.document = Jsoup.parse(htmlForm);
    }

    public void setDocument(String data, String docType) {
        if ( docType.toUpperCase().equals(DocumentParser.HTML) ) {
            this.document = Jsoup.parse(data, Parser.htmlParser());
        } else if (docType.toUpperCase().equals(DocumentParser.XML)) {
            this.document = Jsoup.parse(data, Parser.xmlParser());
        } else {
            this.document = Jsoup.parse(htmlForm);
        }
    }

    public Document getDocument() {
        return this.document;
    }

    public Document initDocument() {
        this.document = Jsoup.parse(htmlForm);
        return this.document;
    }

    public Document appendElementAtTag(Element element, String tag) {
        if ( tag.equals(DocumentParser.HEAD) ) {
            this.document.head().appendChild(element);
        } else if ( tag.equals(DocumentParser.BODY)) {
            this.document.body().appendChild(element);
        }
        return this.document;
    }

    public Document appendStringAtTag(String element, String tag) {
        Element appendElement = Jsoup.parseBodyFragment(element).body().children().first();
        return appendElementAtTag(appendElement, tag);
    }

    public String getElementString(String tagName) {
        return getElement(tagName).toString();
    }

    public Element getElement(String tagName) {
        return document.select(tagName.toLowerCase()).get(0);
    }

    public Document appendCssToTag(String css, String tagName) {

        tagName = tagName.equals(DocumentParser.DIV) || tagName.equals(DocumentParser.STYLE)
                ? tagName
                : "head style";

        Element selected = this.document.select(tagName).first();

        if (selected == null) {
            return this.document;
        }

        selected.appendText(css);

        return this.document;

    }


}
