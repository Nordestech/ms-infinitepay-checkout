package br.com.nordestech.checkout.presentation.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CheckoutInfoRequestDTO(
        @JsonProperty("receipt_url")
        String receiptUrl,

        @JsonProperty("order_nsu")
        String orderNsu,

        String slug,

        @JsonProperty("capture_method")
        String captureMethod,

        @JsonProperty("transaction_nsu")
        String transactionNsu
) {
}
