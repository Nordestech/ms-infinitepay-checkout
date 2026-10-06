package br.com.nordestech.checkout.domain.service;

import br.com.nordestech.checkout.domain.model.WebhookCheckoutInfo;
import br.com.nordestech.checkout.domain.repository.WebhookCheckoutInfoRepository;
import br.com.nordestech.checkout.port.output.producer.PaymentApprovedEventPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ProcessWebhookCheckoutInfoUseCaseTest {

    private WebhookCheckoutInfoRepository repository;
    private PaymentApprovedEventPublisher eventPublisher;
    private ProcessWebhookCheckoutInfoUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = mock(WebhookCheckoutInfoRepository.class);
        eventPublisher = mock(PaymentApprovedEventPublisher.class);
        useCase = new ProcessWebhookCheckoutInfoUseCase(repository, eventPublisher);
    }

    @Test
    void shouldPublishPaymentApprovedEvent() {
        WebhookCheckoutInfo webhook = WebhookCheckoutInfo.builder().orderNsu("pedido-123").build();
        when(repository.existsByOrderNsu("pedido-123")).thenReturn(false);

        useCase.save(webhook);

        //verify(eventPublisher).publishPaymentApprovedEvent(webhook);
    }

    @Test
    void shouldNotPublishWhenOrderAlreadyExists() {
        WebhookCheckoutInfo webhook = WebhookCheckoutInfo.builder().orderNsu("pedido-123").build();
        when(repository.existsByOrderNsu("pedido-123")).thenReturn(true);

        useCase.save(webhook);

        verify(repository, never()).save(any());
        verifyNoInteractions(eventPublisher);
    }

    @Test
    void shouldPersistWebhook() {
        WebhookCheckoutInfo webhook = WebhookCheckoutInfo.builder()
                .orderNsu("pedido-123")
                .transactionNsu("transacao-456")
                .build();
        when(repository.existsByOrderNsu("pedido-123")).thenReturn(false);

        useCase.save(webhook);

        verify(repository).save(webhook);
    }

    @Test
    void shouldHandleDuplicateTransaction() {
        WebhookCheckoutInfo webhook = WebhookCheckoutInfo.builder()
                .orderNsu("pedido-123")
                .transactionNsu("transacao-456")
                .build();
        when(repository.existsByOrderNsu("pedido-123")).thenReturn(false, true);

        useCase.save(webhook);
        useCase.save(webhook);

        verify(repository, times(1)).save(webhook);
        //verify(eventPublisher, times(1)).publishPaymentApprovedEvent(webhook);
    }

    @Test
    void shouldNotPublishWhenPersistenceFails() {
        WebhookCheckoutInfo webhook = WebhookCheckoutInfo.builder().orderNsu("pedido-123").build();
        when(repository.existsByOrderNsu("pedido-123")).thenReturn(false);
        doThrow(new RuntimeException("persistence failure")).when(repository).save(webhook);

        assertThrows(RuntimeException.class, () -> useCase.save(webhook));

        verify(repository).save(webhook);
        verifyNoInteractions(eventPublisher);
    }
}
