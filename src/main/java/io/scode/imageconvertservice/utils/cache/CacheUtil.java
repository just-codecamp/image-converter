package io.scode.imageconvertservice.utils.cache;

import io.scode.imageconvertservice.dto.CacheVo;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class CacheUtil {
    private final MessageDigest messageDigest;

    @Cacheable(cacheNames = "cropperCache")
    public CacheVo caching(byte[] bytes){
        return CacheVo.builder()
                .data(Base64.getEncoder().encodeToString(messageDigest.digest(bytes)))
                .lastModified(LocalDateTime.now())
                .build();
    }

}
