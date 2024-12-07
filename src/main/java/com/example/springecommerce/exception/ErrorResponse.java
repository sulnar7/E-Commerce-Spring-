package com.example.springecommerce.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private int code;
    private String message;
    private String status;
    private LocalDateTime timestamp;
}
