package br.com.nordestech.checkout.port.output.mapper;

import br.com.nordestech.checkout.domain.model.PaymentInfo;
import br.com.nordestech.checkout.port.output.model.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentInfo toPaymentInfo(Payment payment) {
        return PaymentInfo.builder()
                .success(payment.success())
                .paid(payment.paid())
                .amount(payment.amount())
                .paidAmount(payment.paidAmount())
                .installments(payment.installments())
                .captureMethod(payment.captureMethod())
                .build();
    }

}
