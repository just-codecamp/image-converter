package io.scode.imageconvertservice.config.exceptions;

public class ImageWriteException extends Exception{

    public ImageWriteException() {
        super();
    }

    public ImageWriteException(String message) {
        super(message);
    }

    public ImageWriteException(String message, Throwable cause) {
        super(message, cause);
    }

}
