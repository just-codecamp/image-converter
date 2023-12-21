package io.scode.imageconvertservice.utils.validator;

import io.scode.imageconvertservice.utils.DataCodec;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import java.util.List;

@Slf4j
public class EncodedSourceValidator implements ConstraintValidator<IsAvailableSource, String> {

    private String decoded;

    private boolean isValidSource() {

        try {
            Document doc = Jsoup.parse(decoded, Parser.xmlParser());
            Element firstElement = doc.child(0);

            if (isAvailableFirst(firstElement)) return false;

            List<Element> siblingElements = firstElement.siblingElements().stream().filter(
                    e -> e.nodeName().equals("div") || e.nodeName().equals("style")
            ).toList();

            return !siblingElements.isEmpty();
        } catch ( Exception e ) {
            return false;
        }

    }

    private boolean isAvailableFirst(Element firstElement) {
        return !firstElement.nodeName().equals("div") && !firstElement.nodeName().equals("style");
    }

    private boolean isEncodedSource(String source) {
        try {
            decoded = DataCodec.decodeBase64(source);
            return true;
        } catch ( Exception e )  {
            log.info("error = {}", e.getMessage());
            log.info("An value {} raise error.", source);
            return false;
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return isEncodedSource(value) && isValidSource();
    }

}
