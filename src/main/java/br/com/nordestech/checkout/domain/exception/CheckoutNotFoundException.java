package br.com.nordestech.checkout.domain.exception;

public class CheckoutNotFoundException extends RuntimeException {
    public CheckoutNotFoundException(String message) {
        super(message);
    }
}
