package io.scode.imageconvertservice.api.v1;

import io.scode.imageconvertservice.config.exceptions.NoSuchKeyException;
import io.scode.imageconvertservice.config.properties.ConverterProperties;
import io.scode.imageconvertservice.dto.CacheVo;
import io.scode.imageconvertservice.dto.ImageSource;
import io.scode.imageconvertservice.service.interfaces.Converter;
import io.scode.imageconvertservice.utils.cache.CacheUtil;
import io.scode.imageconvertservice.utils.cache.PrimitiveKeyUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class ConverterController {

    private final Converter converter;
    private final CacheUtil cacheUtil;

    private final PrimitiveKeyUtil keyUtil;
    private final ConverterProperties converterProperties;

    @GetMapping("/images")
    public ResponseEntity<byte[]> convertImage(
            @Valid ImageSource source
    ) throws Exception {

        if ( !keyUtil.verify(source.getKey()) ) {
            throw new NoSuchKeyException();
        }

        byte[] createdImage = converter.convert(source);
        CacheVo caching = cacheUtil.caching(createdImage);
        return ResponseEntity
                .status(201)
                .cacheControl(
                        CacheControl.maxAge(
                                converterProperties.getCachePolicy().getBrowserCacheTime()
                                , TimeUnit.SECONDS
                        )
                        .immutable()
                        .cachePrivate()
                )
                .eTag(caching.getData())
                .headers(h -> {
                    h.setContentType(MediaType.IMAGE_PNG);
                    h.setContentLength(createdImage.length);
                        })
                .body(createdImage);
    }

}
