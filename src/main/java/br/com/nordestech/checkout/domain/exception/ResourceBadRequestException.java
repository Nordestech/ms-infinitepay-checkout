package br.com.nordestech.checkout.domain.exception;

public class ResourceBadRequestException extends RuntimeException {
    public ResourceBadRequestException(
            String resourceName,
            String fieldName,
            Object fieldValue
    ) {
        super(String.format("%s with invalid field '%s': '%s'", resourceName, fieldName, fieldValue));
    }
}
