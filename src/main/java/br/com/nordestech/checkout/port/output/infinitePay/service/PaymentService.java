package br.com.nordestech.checkout.port.output.infinitePay.service;

import br.com.nordestech.checkout.domain.exception.ResourceNotFoundException;
import br.com.nordestech.checkout.domain.model.PaymentInfo;
import br.com.nordestech.checkout.domain.model.WebhookCheckoutInfo;
import br.com.nordestech.checkout.port.output.infinitePay.api.PaymentInfoAPI;
import br.com.nordestech.checkout.port.output.infinitePay.mapper.PaymentMapper;
import br.com.nordestech.checkout.port.output.infinitePay.model.Payment;
import br.com.nordestech.checkout.port.output.infinitePay.model.dto.PaymentRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final PaymentInfoAPI paymentInfoAPI;
    private final PaymentMapper paymentMapper;

    public PaymentInfo checkPayment(WebhookCheckoutInfo webhookCheckoutInfo, String handle) {

        try {

            PaymentRequestDTO paymentRequestDTO = buildPaymentRequestDTO(webhookCheckoutInfo, handle);

            log.info("Checking payment info: {}", paymentRequestDTO);
            Payment payment = paymentInfoAPI.getPaymentInfo(paymentRequestDTO);

            return paymentMapper.toPaymentInfo(payment);

        } catch (feign.FeignException e) {
            throw new ResourceNotFoundException("Payment", "Checkout Info", webhookCheckoutInfo);
        }

    }

    private PaymentRequestDTO buildPaymentRequestDTO(WebhookCheckoutInfo webhookCheckoutInfo, String handle) {

        return PaymentRequestDTO.builder()
                .handle(handle)
                .orderNsu(webhookCheckoutInfo.getOrderNsu())
                .transactionNsu(webhookCheckoutInfo.getTransactionNsu())
                .slug(webhookCheckoutInfo.getInvoiceSlug())
                .build();

    }

}
