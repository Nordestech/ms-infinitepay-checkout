package br.com.nordestech.checkout.presentation.rest.response;

import br.com.nordestech.checkout.domain.model.Item;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record WebhookCheckoutInfoResponseDTO(

        String checkoutId,

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

        List<Item> items,

        @JsonProperty("created_at")
        LocalDateTime createdAt

) {
}
