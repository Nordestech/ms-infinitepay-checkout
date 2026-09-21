package br.com.nordestech.checkout.port.output.exception;

public class CheckoutException extends RuntimeException {
    public CheckoutException(String message) {
        super(message);
    }
}
