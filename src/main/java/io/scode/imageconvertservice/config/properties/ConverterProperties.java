package io.scode.imageconvertservice.config.properties;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "converter")
public class ConverterProperties {

    private Fonts fonts;
    private Auth auth;
    private CachePolicy cachePolicy;

    @Setter
    @Getter
    public static class Fonts {
        private String defaultPath;
    }

    @Setter
    @Getter
    public static class CachePolicy {
        private Integer browserCacheTime;
    }

    @Setter
    @Getter
    public static class Auth {

        private String headerName;
        private String algorithm;
        private String id;
        private List<String> keys;

    }


}
