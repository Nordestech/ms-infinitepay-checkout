package br.com.nordestech.checkout.domain.service;

import br.com.nordestech.checkout.domain.model.CheckoutInfo;
import br.com.nordestech.checkout.domain.repository.CheckoutInfoRepository;
import br.com.nordestech.checkout.port.output.exception.CheckoutException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcessCheckoutInfoUseCase {

    private final CheckoutInfoRepository checkoutInfoRepository;

    public CheckoutInfo process(CheckoutInfo checkoutInfo){

        if(!checkoutInfo.getOrderNsu().matches("^order-\\d+$") ||
        !checkoutInfo.getReceiptUrl().matches("^https://recibo\\.infinitepay\\.io/[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-4[0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$") ||
        !checkoutInfo.getTransactionNsu().matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-4[0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$") ||
        !checkoutInfo.getTransactionId().matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-4[0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$") ||
        !checkoutInfo.getSlug().matches("^[a-zA-Z0-9]{10}$")) {
            throw new CheckoutException("Erro no salvamento do checkout");
        }

        if(!(checkoutInfo.getTransactionNsu().equals(checkoutInfo.getTransactionId()))) {
            throw new CheckoutException("Erro no salvamento do checkout");
        }

        if (!checkoutInfo.getCaptureMethod().equals(PaymentMethod.pix.toString()) &&
            !checkoutInfo.getCaptureMethod().equals(PaymentMethod.credit_card.toString())) {
            throw new CheckoutException("Erro no salvamento do checkout");
        }

        CheckoutInfo checkoutInfoByTransactionNsu = checkoutInfoRepository.getCheckoutInfoByTransactionNsu(checkoutInfo.getTransactionNsu());

        if (checkoutInfoByTransactionNsu == null) {
            return checkoutInfoRepository.save(checkoutInfo);
        }

        return checkoutInfoByTransactionNsu;

    }

    private enum PaymentMethod {
        pix,
        credit_card
    }

}
