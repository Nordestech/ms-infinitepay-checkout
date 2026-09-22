package br.com.nordestech.checkout.presentation.mapper;


import br.com.nordestech.checkout.domain.model.CheckoutInfo;
import br.com.nordestech.checkout.presentation.rest.response.CheckoutInfoResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class CheckoutInfoDTOMapper {

    public CheckoutInfo toCheckoutInfo(
            String receiptUrl,
            String orderNsu,
            String slug,
            String captureMethod,
            String transactionNsu,
            String transactionId
    ){

        return CheckoutInfo.builder()
                .receiptUrl(receiptUrl)
                .orderNsu(orderNsu)
                .slug(slug)
                .captureMethod(captureMethod)
                .transactionNsu(transactionNsu)
                .transactionId(transactionId)
                .build();

    }

    public CheckoutInfoResponseDTO toCheckoutInfoResponseDTO(CheckoutInfo checkoutInfo){
        return CheckoutInfoResponseDTO.builder()
                .checkoutInfoId(checkoutInfo.getCheckoutInfoId())
                .receiptUrl(checkoutInfo.getReceiptUrl())
                .orderNsu(checkoutInfo.getOrderNsu())
                .slug(checkoutInfo.getSlug())
                .captureMethod(checkoutInfo.getCaptureMethod())
                .transactionNsu(checkoutInfo.getTransactionNsu())
                .build();
    }

}
