package io.scode.imageconvertservice.service.interfaces;

import io.scode.imageconvertservice.dto.ImageSource;

public interface Convertable {

    byte[] convert(ImageSource source) throws Exception;

}
