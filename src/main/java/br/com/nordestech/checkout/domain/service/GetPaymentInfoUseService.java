package br.com.nordestech.checkout.domain.service;

import br.com.nordestech.checkout.domain.model.CheckoutInfo;
import br.com.nordestech.checkout.domain.model.PaymentInfo;
import br.com.nordestech.checkout.domain.repository.CheckoutInfoRepository;
import br.com.nordestech.checkout.port.output.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPaymentInfoUseService {

    private final PaymentService paymentService;
    private final CheckoutInfoRepository checkoutInfoRepository;

    @Value("${HANDLE}")
    String handle;

    public PaymentInfo getPayment(String checkoutInfoId) {

        CheckoutInfo checkoutInfo = checkoutInfoRepository.getCheckoutInfo(checkoutInfoId);

        return paymentService.checkPayment(checkoutInfo, handle);
    }

}
