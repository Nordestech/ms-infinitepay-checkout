package br.com.nordestech.checkout.application;

import br.com.nordestech.checkout.domain.exception.CheckoutNotFoundException;
import br.com.nordestech.checkout.domain.exception.PayloadBadRequestException;
import br.com.nordestech.checkout.domain.exception.PaymentNotFoundException;
import br.com.nordestech.checkout.port.output.exception.CheckoutException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalHandlerException {


    //------------------------------- PAYLOAD EXCEPTIONS -------------------------------------------------


    @ExceptionHandler(PayloadBadRequestException.class)
    public ResponseEntity<StandardErrorMessage> handlePayloadBadRequestException(PayloadBadRequestException ex, HttpServletRequest request) {
        String error = "Erro na requisição";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardErrorMessage message = new StandardErrorMessage(Instant.now(), status.value(),
                error, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(message);
    }


    //------------------------------- CHECKOUT EXCEPTIONS -------------------------------------------------


    @ExceptionHandler(CheckoutNotFoundException.class)
    public ResponseEntity<StandardErrorMessage> handleCheckoutNotFoundException(CheckoutNotFoundException ex, HttpServletRequest request) {
        String error = "Erro na requisição";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardErrorMessage message = new StandardErrorMessage(Instant.now(), status.value(),
                error, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(message);
    }

    @ExceptionHandler(CheckoutException.class)
    public ResponseEntity<StandardErrorMessage> handleCheckoutException(CheckoutException ex, HttpServletRequest request) {
        String error = "Erro na requisição";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardErrorMessage message = new StandardErrorMessage(Instant.now(), status.value(),
                error, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(message);
    }


    //------------------------------- PAYLOAD EXCEPTIONS -------------------------------------------------


    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<StandardErrorMessage> handlePaymentNotFoundException(PaymentNotFoundException ex, HttpServletRequest request) {
        String error = "Erro na requisição";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardErrorMessage message = new StandardErrorMessage(Instant.now(), status.value(),
                error, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(message);
    }


    //------------------------------- METHOD EXCEPTIONS -------------------------------------------------


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        String errors = e.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardErrorMessage message = new StandardErrorMessage(Instant.now(), status.value(),
                "Bad request error", errors, request.getRequestURI());
        return ResponseEntity.status(status).body(message);
    }

}
