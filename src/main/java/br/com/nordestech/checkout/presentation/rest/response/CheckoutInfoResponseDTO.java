package br.com.nordestech.checkout.presentation.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record CheckoutInfoResponseDTO(

        @JsonProperty("checkout_info_id")
        String checkoutInfoId,

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
