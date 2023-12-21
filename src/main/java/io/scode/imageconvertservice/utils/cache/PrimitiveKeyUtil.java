package io.scode.imageconvertservice.utils.cache;

import io.scode.imageconvertservice.config.properties.ConverterProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.util.Base64;

@Slf4j
@Component
@RequiredArgsConstructor
public class PrimitiveKeyUtil {

    private final MessageDigest messageDigest;
    private final ConverterProperties converterProperties;

    public String getHeaderName() {
        return converterProperties.getAuth().getHeaderName();
    }

    public boolean verify(String key) {
        String input = Base64.getEncoder().encodeToString(messageDigest.digest((converterProperties.getAuth().getId() + key).getBytes()));

        return converterProperties.getAuth()
                .getKeys()
                .stream()
                .anyMatch(k -> k.equals(input));
    }


}
