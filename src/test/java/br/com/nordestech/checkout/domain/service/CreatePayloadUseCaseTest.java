package br.com.nordestech.checkout.domain.service;

import br.com.nordestech.checkout.domain.model.Payload;
import br.com.nordestech.checkout.port.output.infinitePay.exception.CheckoutException;
import br.com.nordestech.checkout.port.output.infinitePay.model.Checkout;
import br.com.nordestech.checkout.port.output.infinitePay.service.CheckoutService;
import feign.FeignException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreatePayloadUseCaseTest {

    private CheckoutService checkoutService;
    private CreatePayloadUseCase useCase;

    @BeforeEach
    void setUp() {
        checkoutService = mock(CheckoutService.class);
        useCase = new CreatePayloadUseCase(checkoutService);
        ReflectionTestUtils.setField(useCase, "handle", "loja-teste");
    }

    @Test
    void shouldSetConfiguredHandleAndReturnCheckout() {
        Payload payload = Payload.builder().orderNsu("pedido-123").build();
        Checkout expected = new Checkout("https://checkout.example/pedido-123");
        when(checkoutService.executeCheckout(payload)).thenReturn(expected);

        Checkout result = useCase.createPayload(payload);

        assertEquals("loja-teste", payload.getHandle());
        assertSame(expected, result);
        verify(checkoutService).executeCheckout(payload);
    }

    @Test
    void shouldTranslateFeignFailureToCheckoutException() {
        Payload payload = Payload.builder().orderNsu("pedido-123").build();
        when(checkoutService.executeCheckout(payload)).thenThrow(mock(FeignException.class));

        assertThrows(CheckoutException.class, () -> useCase.createPayload(payload));
    }
}
