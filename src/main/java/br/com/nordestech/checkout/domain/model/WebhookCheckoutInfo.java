package br.com.nordestech.checkout.domain.model;

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
    private String invoiceSlug;
    private BigDecimal amount;
    private BigDecimal paidAmount;
    private Integer installments;
    private String captureMethod;
    private String transactionNsu;
    private String orderNsu;
    private String receiptUrl;
    private List<Item> items;
    private LocalDateTime createdAt;

}
