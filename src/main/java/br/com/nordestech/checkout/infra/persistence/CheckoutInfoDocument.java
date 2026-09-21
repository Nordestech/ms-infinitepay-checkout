package br.com.nordestech.checkout.infra.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "checkout_info")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class CheckoutInfoDocument {

    @Id
    private String id;

    @Field("receipt_url")
    private String receiptUrl;

    @Field("order_nsu")
    private String orderNsu;

    @Field("slug")
    private String slug;

    @Field("capture_method")
    private String captureMethod;

    @Field("transaction_nsu")
    private String transactionNsu;

    @Field("created_at")
    private LocalDateTime createdAt;

    public CheckoutInfoDocument() {
        this.createdAt = LocalDateTime.now();
    }

    public CheckoutInfoDocument(String receiptUrl, String orderNsu, String slug, String captureMethod, String transactionNsu) {
        this();
        this.receiptUrl = receiptUrl;
        this.orderNsu = orderNsu;
        this.slug = slug;
        this.captureMethod = captureMethod;
        this.transactionNsu = transactionNsu;
    }
}
