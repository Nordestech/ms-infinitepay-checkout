package br.com.nordestech.checkout.domain.service;

import br.com.nordestech.checkout.domain.model.PaymentInfo;
import br.com.nordestech.checkout.domain.model.WebhookCheckoutInfo;
import br.com.nordestech.checkout.port.output.infinitePay.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPaymentInfoUseCase {

    private final PaymentService paymentService;
    private final GetWebhookCheckoutInfoUseCase getWebhookCheckoutInfoUseCase;

    @Value("${HANDLE}")
    String handle;

    public PaymentInfo getPayment(String checkoutInfoId) {

        WebhookCheckoutInfo webhookCheckoutInfo = getWebhookCheckoutInfoUseCase.findByOrderNsu(checkoutInfoId);

        return paymentService.checkPayment(webhookCheckoutInfo, handle);
    }

}
