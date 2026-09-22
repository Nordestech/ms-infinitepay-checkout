package br.com.nordestech.checkout.presentation.mapper;

import br.com.nordestech.checkout.domain.model.PaymentInfo;
import br.com.nordestech.checkout.presentation.rest.response.PaymentInfoResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class PaymentInfoDTOMapper {

    public PaymentInfoResponseDTO toPaymentInfoResponseDTO(PaymentInfo paymentInfo) {

        return PaymentInfoResponseDTO.builder()
                .success(paymentInfo.getSuccess())
                .paid(paymentInfo.getPaid())
                .amount(paymentInfo.getAmount())
                .paidAmount(paymentInfo.getPaidAmount())
                .installments(paymentInfo.getInstallments())
                .captureMethod(paymentInfo.getCaptureMethod())
                .build();
    }

}
