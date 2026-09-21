package br.com.nordestech.checkout.domain.exception;

public class PayloadBadRequestException extends RuntimeException {
    public PayloadBadRequestException(String message) {
        super(message);
    }
}
