package br.com.nordestech.checkout.domain.repository;

import br.com.nordestech.checkout.domain.model.CheckoutInfo;

public interface CheckoutInfoRepository {

    CheckoutInfo save(CheckoutInfo checkoutInfo);

    CheckoutInfo getCheckoutInfo(String checkoutInfoId);

    CheckoutInfo getCheckoutInfoByTransactionNsu(String transactionNsu);

}
