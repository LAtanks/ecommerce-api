package me.latanks.ecommerce_api.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
        int statusCode,
        String message,
        String exception,
        String path,
        LocalDateTime timeStamp
) {
}
