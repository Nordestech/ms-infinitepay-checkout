package br.com.nordestech.checkout.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class WebhookCheckoutInfo {

    private String checkoutId;

    @JsonProperty("invoice_slug")
    private String invoiceSlug;

    private BigDecimal amount;

    @JsonProperty("paid_amount")
    private BigDecimal paidAmount;

    private Integer installments;

    @JsonProperty("capture_method")
    private String captureMethod;

    @JsonProperty("transaction_nsu")
    private String transactionNsu;

    @JsonProperty("order_nsu")
    private String orderNsu;

    @JsonProperty("receipt_url")
    private String receiptUrl;

    private List<Item> items;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

}
