package io.scode.imageconvertservice.config.exceptions;

public class FileProcessException extends Exception{
    public FileProcessException() {
        super();
    }

    public FileProcessException(String message) {
        super(message);
    }

    public FileProcessException(String message, Throwable cause) {
        super(message, cause);
    }
}
