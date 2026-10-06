package br.com.nordestech.checkout.port.output.producer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentApprovedDTO(

        @JsonProperty("invoice_slug")
        String checkoutId,

        @JsonProperty("order_nsu")
        String orderNsu,

        @JsonProperty("transaction_nsu")
        String transactionNsu,

        @JsonProperty("receipt_url")
        String receiptUrl,

        BigDecimal amount,

        @JsonProperty("paid_amount")
        BigDecimal paidAmount

){

}
