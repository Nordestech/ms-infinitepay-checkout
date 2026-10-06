package br.com.nordestech.checkout.domain.service;

import br.com.nordestech.checkout.domain.model.Payload;
import br.com.nordestech.checkout.port.output.infinitePay.exception.CheckoutException;
import br.com.nordestech.checkout.port.output.infinitePay.model.Checkout;
import br.com.nordestech.checkout.port.output.infinitePay.service.CheckoutService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreatePayloadUseCase {

    private final CheckoutService checkoutService;

    @Value("${HANDLE}")
    String handle;

    public Checkout createPayload(Payload payload) {
        try {

            log.info("Setting handle to payload: 'handle' = '{}'", handle);
            payload.setHandle(handle);

            return checkoutService.executeCheckout(payload);

        } catch (FeignException e) {

            throw new CheckoutException("Checkout", "message", e.getMessage());

        }
    }

}
