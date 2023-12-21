package io.scode.imageconvertservice.utils.cache;

import io.scode.imageconvertservice.config.properties.ConverterProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Configuration
@RequiredArgsConstructor
public class HashUtil {

    private final ConverterProperties converterProperties;

    @Bean
    public MessageDigest messageDigest() throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(converterProperties.getAuth().getAlgorithm());
    }

}
