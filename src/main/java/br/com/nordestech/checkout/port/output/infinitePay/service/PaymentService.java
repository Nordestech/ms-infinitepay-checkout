package br.com.nordestech.checkout.port.output.infinitePay.service;

import br.com.nordestech.checkout.domain.exception.PaymentNotFoundException;
import br.com.nordestech.checkout.domain.model.CheckoutInfo;
import br.com.nordestech.checkout.domain.model.PaymentInfo;
import br.com.nordestech.checkout.port.output.infinitePay.api.PaymentInfoAPI;
import br.com.nordestech.checkout.port.output.infinitePay.mapper.PaymentMapper;
import br.com.nordestech.checkout.port.output.infinitePay.model.Payment;
import br.com.nordestech.checkout.port.output.infinitePay.model.dto.PaymentRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentInfoAPI paymentInfoAPI;
    private final PaymentMapper paymentMapper;

    public PaymentInfo checkPayment(CheckoutInfo checkoutInfo, String handle) {

        try {

            PaymentRequestDTO paymentRequestDTO = buildPaymentRequestDTO(checkoutInfo, handle);

            Payment payment = paymentInfoAPI.getPaymentInfo(paymentRequestDTO);

            return paymentMapper.toPaymentInfo(payment);

        } catch (feign.FeignException e) {
            throw new PaymentNotFoundException("Payment not found: " + e.getMessage());
        }

    }

    private PaymentRequestDTO buildPaymentRequestDTO(CheckoutInfo checkoutInfo, String handle) {

        return PaymentRequestDTO.builder()
                .handle(handle)
                .orderNsu(checkoutInfo.getOrderNsu())
                .transactionNsu(checkoutInfo.getTransactionNsu())
                .slug(checkoutInfo.getSlug())
                .build();

    }

}
