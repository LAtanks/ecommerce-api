package me.latanks.ecommerce_api.exception;

import io.swagger.v3.core.util.Json;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.latanks.ecommerce_api.exception.impl.ProductNotFoundException;
import me.latanks.ecommerce_api.exception.impl.UserNotFoundedException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        int statusCode = HttpStatus.BAD_REQUEST.value();

        response.setStatus(statusCode);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ErrorResponse errorResponse = new ErrorResponse(statusCode, exception.getMessage(), exception.getClass().getSimpleName(), request.getContextPath(), LocalDateTime.now());
        response.getWriter().print(Json.pretty(errorResponse));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotFoundedException.class)
    public ResponseEntity<Object> handleUserNotFound(HttpServletRequest req, HttpServletResponse resp, UserNotFoundedException ex) throws IOException {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex, req);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFound(HttpServletRequest req, HttpServletResponse resp, ProductNotFoundException ex) throws IOException {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex, req);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentials(HttpServletRequest req, HttpServletResponse resp, BadCredentialsException ex) throws IOException {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex, req);
    }

    private ResponseEntity<Object> buildErrorResponse(HttpStatus status, Exception ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(status.value(), ex.getMessage(), ex.getClass().getSimpleName(), request.getContextPath(), LocalDateTime.now());

        return ResponseEntity.status(status).body(errorResponse);
    }

    private ResponseEntity<Object> buildErrorResponse(HttpStatus status, Exception ex, WebRequest request) {
        ServletWebRequest webRequest = new ServletWebRequest((HttpServletRequest) request);
        return this.buildErrorResponse(status, ex, webRequest);
    }
}
