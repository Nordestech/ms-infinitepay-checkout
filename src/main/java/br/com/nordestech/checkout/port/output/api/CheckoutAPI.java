package br.com.nordestech.checkout.port.output.api;

import br.com.nordestech.checkout.port.output.model.Checkout;
import br.com.nordestech.checkout.port.output.model.dto.PayloadDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "CheckoutAPI",
        url = "https://api.checkout.infinitepay.io"
)
public interface CheckoutAPI {

    @PostMapping("/links")
    Checkout createCheckoutURL(@RequestBody PayloadDTO payloadDTO);

}
