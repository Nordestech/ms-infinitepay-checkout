package br.com.nordestech.checkout.presentation.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public record CheckoutInfoWebhook(

        @JsonProperty("invoice_slug")
        String invoiceSlug,

        BigDecimal amount,

        @JsonProperty("paid_amount")
        BigDecimal paidAmount,

        Integer installments,

        @JsonProperty("capture_method")
        String captureMethod,

        @JsonProperty("transaction_nsu")
        String transactionNsu,

        @JsonProperty("order_nsu")
        String orderNsu,

        @JsonProperty("receipt_url")
        String receiptUrl,

        List<Item> items

) {

    public record Item(

            Integer quantity,

            BigDecimal price,

            String description

    ) {
    }
}
