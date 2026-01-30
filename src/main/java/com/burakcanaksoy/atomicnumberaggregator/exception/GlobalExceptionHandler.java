package com.burakcanaksoy.atomicnumberaggregator.exception;

import com.burakcanaksoy.atomicnumberaggregator.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SoapServiceException.class)
    public ResponseEntity<ApiResponse<Void>> handleSoapException(SoapServiceException ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Girilen sayı SOAP servisi tarafından işlenemedi: " + ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneralException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Beklenmedik bir hata oluştu: " + ex.getMessage()));
    }
}
