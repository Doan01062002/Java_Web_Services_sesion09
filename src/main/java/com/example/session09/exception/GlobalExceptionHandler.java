package com.example.session09.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        logger.error("Lỗi xảy ra: {}", ex.getMessage(), ex); // Ghi cả message và stack trace
        return new ResponseEntity<>("Không tìm thấy người dùng!", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOtherExceptions(Exception ex) {
        logger.error("Lỗi không xác định: {}", ex.getMessage(), ex);
        return new ResponseEntity<>("Đã xảy ra lỗi không xác định!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}