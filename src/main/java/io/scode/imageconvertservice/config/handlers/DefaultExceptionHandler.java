package io.scode.imageconvertservice.config.handlers;

import io.scode.imageconvertservice.config.exceptions.FileProcessException;
import io.scode.imageconvertservice.config.exceptions.FontLoadException;
import io.scode.imageconvertservice.config.exceptions.ImageWriteException;
import io.scode.imageconvertservice.config.exceptions.NoSuchKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DefaultExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FileProcessException.class)
    public ResponseEntity<Void> fileProcessException(FileProcessException e) {
        return ResponseEntity.badRequest().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FontLoadException.class)
    public ResponseEntity<Void> fontloadException(FontLoadException e) {
        return ResponseEntity.badRequest().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ImageWriteException.class)
    public ResponseEntity<Void> imageWriteException(ImageWriteException e) {
        return ResponseEntity.badRequest().build();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(NoSuchKeyException.class)
    public ResponseEntity<String> noSuchKeyException(NoSuchKeyException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Could not find your key. please check your key again");
    }


}
