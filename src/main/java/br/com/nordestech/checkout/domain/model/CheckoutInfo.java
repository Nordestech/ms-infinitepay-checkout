package br.com.nordestech.checkout.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class CheckoutInfo {

    @JsonProperty("checkout_info_id")
    private String checkoutInfoId;

    @JsonProperty("receipt_url")
    private String receiptUrl;

    @JsonProperty("order_nsu")
    private String orderNsu;

    private String slug;

    @JsonProperty("capture_method")
    private String captureMethod;

    @JsonProperty("transaction_nsu")
    private String transactionNsu;

}
