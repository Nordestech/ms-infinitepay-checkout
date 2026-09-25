package br.com.nordestech.checkout.port.output.infinitePay.mapper;

import br.com.nordestech.checkout.domain.model.Payload;
import br.com.nordestech.checkout.port.output.infinitePay.model.dto.PayloadDTO;
import org.springframework.stereotype.Component;

@Component
public class PayloadMapper {

    public PayloadDTO toPayloadDTO(Payload payload) {

        return PayloadDTO.builder()
                .handle(payload.getHandle())
                .items(payload.getItems())
                .orderNsu(payload.getOrderNsu())
                .redirectUrl(payload.getRedirectUrl())
                .customer(payload.getCustomer())
                .address(payload.getAddress())
                .build();
    }

}
