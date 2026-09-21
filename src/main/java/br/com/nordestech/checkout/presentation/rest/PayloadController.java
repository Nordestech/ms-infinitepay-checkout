package br.com.nordestech.checkout.presentation.rest;

import br.com.nordestech.checkout.domain.model.CheckoutInfo;
import br.com.nordestech.checkout.domain.model.dto.CheckoutDTO;
import br.com.nordestech.checkout.domain.service.CreatePayloadUseCase;
import br.com.nordestech.checkout.domain.service.ProcessCheckoutInfoUseCase;
import br.com.nordestech.checkout.presentation.mapper.CheckoutDTOMapper;
import br.com.nordestech.checkout.presentation.mapper.CheckoutInfoDTOMapper;
import br.com.nordestech.checkout.presentation.mapper.PayloadDTOMapper;
import br.com.nordestech.checkout.presentation.rest.request.PayloadRequestDTO;
import br.com.nordestech.checkout.presentation.rest.response.CheckoutInfoResponseDTO;
import br.com.nordestech.checkout.presentation.rest.response.CheckoutResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/checkout")
@RequiredArgsConstructor
public class PayloadController {

    private final CreatePayloadUseCase createPayloadUseCase;
    private final ProcessCheckoutInfoUseCase processCheckoutInfoUseCase;

    private final CheckoutInfoDTOMapper checkoutInfoDTOMapper;
    private final PayloadDTOMapper payloadDTOMapper;
    private final CheckoutDTOMapper checkoutDTOMapper;

    @PostMapping("new")
    public ResponseEntity<CheckoutResponseDTO> newCheckout(@RequestBody @Valid PayloadRequestDTO payloadRequestDTO) {

        CheckoutDTO checkout = createPayloadUseCase.createPayload(payloadDTOMapper.toPayload(payloadRequestDTO));
        return ResponseEntity.ok(checkoutDTOMapper.toCheckoutResponseDTO(checkout));

    }

    @GetMapping("return")
    public ResponseEntity<CheckoutInfoResponseDTO> returnCheckout(
            @RequestParam(name = "receipt_url") String receiptUrl,
            @RequestParam(name = "order_nsu") String orderNsu,
            @RequestParam(name = "slug") String slug,
            @RequestParam(name = "capture_method") String captureMethod,
            @RequestParam(name = "transaction_nsu") String transactionNsu) {

        CheckoutInfo checkoutInfo = processCheckoutInfoUseCase.process(checkoutInfoDTOMapper.toCheckoutInfo(
                receiptUrl, orderNsu, slug, captureMethod, transactionNsu
        ));

        return ResponseEntity.ok().body(checkoutInfoDTOMapper.toCheckoutInfoResponseDTO(checkoutInfo));

    }

}
