package br.com.nordestech.checkout.port.output.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record PaymentRequestDTO(

        String handle,

        @JsonProperty("order_nsu")
        String orderNsu,

        @JsonProperty("transaction_nsu")
        String transactionNsu,

        String slug

) {
}
