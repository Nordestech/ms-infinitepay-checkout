package br.com.nordestech.checkout.presentation.mapper;

import br.com.nordestech.checkout.domain.model.Payload;
import br.com.nordestech.checkout.presentation.rest.request.PayloadRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PayloadDTOMapper {

    @Value("${HANDLE}")
    public String handle;

    public Payload toPayload(PayloadRequestDTO dto) {

        return Payload.builder()
                .handle(handle)
                .items(dto.items().stream().map(PayloadRequestDTO.ItemDTO::toModel).toList())
                .orderNsu(dto.orderNsu())
                .redirectUrl(dto.redirectUrl())
                .customer(dto.customer().toModel())
                .address(dto.address().toModel())
                .build();
    }

}
