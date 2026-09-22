package br.com.nordestech.checkout.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class PaymentInfo {

    private Boolean success;

    private Boolean paid;

    private BigDecimal amount;

    @JsonProperty("paid_amount")
    private BigDecimal paidAmount;

    private Integer installments;

    @JsonProperty("capture_method")
    private String captureMethod;

}
