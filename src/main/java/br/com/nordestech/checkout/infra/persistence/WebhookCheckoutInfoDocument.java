package br.com.nordestech.checkout.infra.persistence;

import br.com.nordestech.checkout.domain.model.Item;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "webhook_checkout_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebhookCheckoutInfoDocument {

    @Id
    private String checkoutId;

    @Field("invoice_slug")
    private String invoiceSlug;

    private BigDecimal amount;

    @Field("paid_amount")
    private BigDecimal paidAmount;

    private Integer installments;

    @Field("capture_method")
    private String captureMethod;

    @Field("transaction_nsu")
    private String transactionNsu;

    @Field("order_nsu")
    private String orderNsu;

    @Field("receipt_url")
    private String receiptUrl;

    private List<Item> items;

    @Field("created_at")
    private LocalDateTime createdAt;

}
