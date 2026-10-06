package br.com.nordestech.checkout.domain.model;

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
    private BigDecimal paidAmount;
    private Integer installments;
    private String captureMethod;

}
