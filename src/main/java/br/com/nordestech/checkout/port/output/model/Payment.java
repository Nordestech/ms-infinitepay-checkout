package br.com.nordestech.checkout.port.output.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record Payment(

        Boolean success,

        Boolean paid,

        BigDecimal amount,

        @JsonProperty("paid_amount")
        BigDecimal paidAmount,

        Integer installments,

        @JsonProperty("capture_method")
        String captureMethod

) {
}
