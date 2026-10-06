package br.com.nordestech.checkout.infra.persistence.mapper;

import br.com.nordestech.checkout.domain.model.WebhookCheckoutInfo;
import br.com.nordestech.checkout.infra.persistence.WebhookCheckoutInfoDocument;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class WebhookCheckoutInfoMapper {

    public WebhookCheckoutInfo toWebhookCheckoutInfo(WebhookCheckoutInfoDocument document) {

        return WebhookCheckoutInfo.builder()
                .checkoutId(document.getCheckoutId())
                .invoiceSlug(document.getInvoiceSlug())
                .amount(document.getAmount())
                .paidAmount(document.getPaidAmount())
                .installments(document.getInstallments())
                .captureMethod(document.getCaptureMethod())
                .transactionNsu(document.getTransactionNsu())
                .orderNsu(document.getOrderNsu())
                .receiptUrl(document.getReceiptUrl())
                .items(document.getItems())
                .createdAt(document.getCreatedAt())
                .build();
    }

    public WebhookCheckoutInfoDocument toWebhookCheckoutInfoDocument(WebhookCheckoutInfo checkout) {

        return WebhookCheckoutInfoDocument.builder()
                .checkoutId(checkout.getCheckoutId())
                .invoiceSlug(checkout.getInvoiceSlug())
                .amount(checkout.getAmount())
                .paidAmount(checkout.getPaidAmount())
                .installments(checkout.getInstallments())
                .captureMethod(checkout.getCaptureMethod())
                .transactionNsu(checkout.getTransactionNsu())
                .orderNsu(checkout.getOrderNsu())
                .receiptUrl(checkout.getReceiptUrl())
                .items(checkout.getItems())
                .createdAt(LocalDateTime.now())
                .build();
    }

}
