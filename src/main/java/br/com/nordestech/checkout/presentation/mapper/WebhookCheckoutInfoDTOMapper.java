package br.com.nordestech.checkout.presentation.mapper;

import br.com.nordestech.checkout.domain.model.WebhookCheckoutInfo;
import br.com.nordestech.checkout.presentation.rest.request.WebhookCheckoutInfoRequestDTO;
import br.com.nordestech.checkout.presentation.rest.response.WebhookCheckoutInfoResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class WebhookCheckoutInfoDTOMapper {

    public WebhookCheckoutInfo toWebhookCheckoutInfo(WebhookCheckoutInfoRequestDTO dto) {

        return WebhookCheckoutInfo.builder()
                .invoiceSlug(dto.invoiceSlug())
                .amount(dto.amount())
                .paidAmount(dto.paidAmount())
                .installments(dto.installments())
                .captureMethod(dto.captureMethod())
                .transactionNsu(dto.transactionNsu())
                .orderNsu(dto.orderNsu())
                .receiptUrl(dto.receiptUrl())
                .items(dto.items().stream().map(WebhookCheckoutInfoRequestDTO.ItemDTO::toModel).toList())
                .build();
    }

    public WebhookCheckoutInfoResponseDTO toWebhookCheckoutInfoResponseDTO(WebhookCheckoutInfo checkout) {

        return WebhookCheckoutInfoResponseDTO.builder()
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
                .createdAt(checkout.getCreatedAt())
                .build();
    }

}
