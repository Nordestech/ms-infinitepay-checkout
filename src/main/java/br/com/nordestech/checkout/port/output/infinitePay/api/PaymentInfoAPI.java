package br.com.nordestech.checkout.port.output.infinitePay.api;

import br.com.nordestech.checkout.port.output.infinitePay.model.Payment;
import br.com.nordestech.checkout.port.output.infinitePay.model.dto.PaymentRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "PaymentInfoAPI",
        url = "https://api.infinitepay.io"
)
public interface PaymentInfoAPI {

    @PostMapping("/invoices/public/checkout/payment_check")
    Payment getPaymentInfo(@RequestBody PaymentRequestDTO paymentRequestDTO);

}
