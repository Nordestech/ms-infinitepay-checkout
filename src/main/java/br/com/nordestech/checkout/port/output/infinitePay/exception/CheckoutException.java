package br.com.nordestech.checkout.port.output.infinitePay.exception;

public class CheckoutException extends RuntimeException {
    public CheckoutException(
            String resourceName,
            String fieldName,
            Object fieldValue
    ) {
        super(String.format("%s exception '%s': '%s'", resourceName, fieldName, fieldValue));
    }
}
