package br.com.nordestech.checkout.infra.persistence.mapper;

import br.com.nordestech.checkout.domain.model.CheckoutInfo;
import br.com.nordestech.checkout.infra.persistence.CheckoutInfoDocument;
import org.springframework.stereotype.Component;

@Component
public class CheckoutInfoMapper {

    public CheckoutInfo toCheckoutInfo(CheckoutInfoDocument checkoutInfoDocument) {
        return CheckoutInfo.builder()
                .checkoutInfoId(checkoutInfoDocument.getId())
                .receiptUrl(checkoutInfoDocument.getReceiptUrl())
                .orderNsu(checkoutInfoDocument.getOrderNsu())
                .slug(checkoutInfoDocument.getSlug())
                .captureMethod(checkoutInfoDocument.getCaptureMethod())
                .transactionNsu(checkoutInfoDocument.getTransactionNsu())
                .build();
    }

    public CheckoutInfoDocument toCheckoutInfoDocument(CheckoutInfo checkoutInfo) {
        return CheckoutInfoDocument.builder()
                .receiptUrl(checkoutInfo.getReceiptUrl())
                .orderNsu(checkoutInfo.getOrderNsu())
                .slug(checkoutInfo.getSlug())
                .captureMethod(checkoutInfo.getCaptureMethod())
                .transactionNsu(checkoutInfo.getTransactionNsu())
                .build();
    }

}
