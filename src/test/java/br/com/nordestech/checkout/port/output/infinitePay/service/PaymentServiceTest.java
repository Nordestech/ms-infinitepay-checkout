package br.com.nordestech.checkout.port.output.infinitePay.service;

import br.com.nordestech.checkout.domain.exception.ResourceNotFoundException;
import br.com.nordestech.checkout.domain.model.PaymentInfo;
import br.com.nordestech.checkout.domain.model.WebhookCheckoutInfo;
import br.com.nordestech.checkout.port.output.infinitePay.api.PaymentInfoAPI;
import br.com.nordestech.checkout.port.output.infinitePay.mapper.PaymentMapper;
import br.com.nordestech.checkout.port.output.infinitePay.model.Payment;
import br.com.nordestech.checkout.port.output.infinitePay.model.dto.PaymentRequestDTO;
import feign.FeignException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PaymentServiceTest {

    private PaymentInfoAPI paymentInfoAPI;
    private PaymentMapper paymentMapper;
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        paymentInfoAPI = mock(PaymentInfoAPI.class);
        paymentMapper = mock(PaymentMapper.class);
        paymentService = new PaymentService(paymentInfoAPI, paymentMapper);
    }

    @Test
    void shouldBuildProviderRequestAndMapPayment() {
        WebhookCheckoutInfo webhook = WebhookCheckoutInfo.builder()
                .orderNsu("pedido-123")
                .transactionNsu("transacao-456")
                .invoiceSlug("fatura-789")
                .build();
        Payment payment = Payment.builder().success(true).paid(true).amount(new BigDecimal("25.00")).build();
        PaymentInfo expected = PaymentInfo.builder().success(true).paid(true).amount(new BigDecimal("25.00")).build();
        when(paymentInfoAPI.getPaymentInfo(any(PaymentRequestDTO.class))).thenReturn(payment);
        when(paymentMapper.toPaymentInfo(payment)).thenReturn(expected);

        PaymentInfo result = paymentService.checkPayment(webhook, "loja-teste");

        assertSame(expected, result);
        verify(paymentInfoAPI).getPaymentInfo(argThat(request ->
                request.handle().equals("loja-teste")
                        && request.orderNsu().equals("pedido-123")
                        && request.transactionNsu().equals("transacao-456")
                        && request.slug().equals("fatura-789")));
        verify(paymentMapper).toPaymentInfo(payment);
    }

    @Test
    void shouldTranslateProviderFailureToNotFoundException() {
        WebhookCheckoutInfo webhook = WebhookCheckoutInfo.builder().orderNsu("pedido-123").build();
        when(paymentInfoAPI.getPaymentInfo(any(PaymentRequestDTO.class))).thenThrow(mock(FeignException.class));

        assertThrows(ResourceNotFoundException.class, () -> paymentService.checkPayment(webhook, "loja-teste"));
        verifyNoInteractions(paymentMapper);
    }
}
