package br.com.nordestech.checkout.presentation.rest;

import br.com.nordestech.checkout.domain.model.PaymentInfo;
import br.com.nordestech.checkout.domain.service.CreatePayloadUseCase;
import br.com.nordestech.checkout.domain.service.GetPaymentInfoUseCase;
import br.com.nordestech.checkout.domain.service.ProcessWebhookCheckoutInfoUseCase;
import br.com.nordestech.checkout.port.output.infinitePay.model.Checkout;
import br.com.nordestech.checkout.presentation.mapper.CheckoutDTOMapper;
import br.com.nordestech.checkout.presentation.mapper.PayloadDTOMapper;
import br.com.nordestech.checkout.presentation.mapper.PaymentInfoDTOMapper;
import br.com.nordestech.checkout.presentation.mapper.WebhookCheckoutInfoDTOMapper;
import br.com.nordestech.checkout.presentation.rest.request.PayloadRequestDTO;
import br.com.nordestech.checkout.presentation.rest.request.WebhookCheckoutInfoRequestDTO;
import br.com.nordestech.checkout.presentation.rest.response.CheckoutResponseDTO;
import br.com.nordestech.checkout.presentation.rest.response.PaymentInfoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Checkout", description = "Endpoints para gerenciamento dos checkouts")
public class PayloadController {

    private final CreatePayloadUseCase createPayloadUseCase;
    private final GetPaymentInfoUseCase getPaymentInfoUseCase;
    private final ProcessWebhookCheckoutInfoUseCase processWebhookCheckoutInfoUseCase;

    private final PayloadDTOMapper payloadDTOMapper;
    private final CheckoutDTOMapper checkoutDTOMapper;
    private final PaymentInfoDTOMapper paymentInfoDTOMapper;
    private final WebhookCheckoutInfoDTOMapper webhookCheckoutInfoDTOMapper;

    @Operation(summary = "Gerar pedido de checkout",
            description = "Gera um novo pedido de checkout por meio de POST na api do InfinitePay")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido de checkout criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos")
    })
    @PostMapping("/create")
    public ResponseEntity<CheckoutResponseDTO> createCheckout(@RequestBody @Valid PayloadRequestDTO payloadRequestDTO) {

        log.info("Creating checkout request received: 'order_nsu' = '{}'", payloadRequestDTO.orderNsu());
        Checkout checkout = createPayloadUseCase.createPayload(payloadDTOMapper.toPayload(payloadRequestDTO));

        log.info("Checkout created: 'checkout_url' = '{}'", checkout);
        return ResponseEntity.ok(checkoutDTOMapper.toCheckoutResponseDTO(checkout));

    }

    @Operation(
            summary = "Receber webhook do checkout",
            description = "Recebe a notificação de pagamento aprovado enviada pela InfinitePay"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Webhook recebido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos")
    })
    @PostMapping("/webhook")
    public ResponseEntity<Void> receiveCheckoutWebhook(@RequestBody @Valid WebhookCheckoutInfoRequestDTO checkoutInfoWebhook) {

        log.info("Webhook request received: 'order_nsu' = '{}'", checkoutInfoWebhook.orderNsu());
        processWebhookCheckoutInfoUseCase.save(webhookCheckoutInfoDTOMapper.toWebhookCheckoutInfo(checkoutInfoWebhook));

        log.info("Webhook processed: 'order_nsu' = '{}'", checkoutInfoWebhook.orderNsu());
        return ResponseEntity.ok().build();

    }

    @Operation(summary = "Busca informações de pagamento do checkout",
            description = "Busca as informações de pagamento do pedido de checkout por meio de parâmetro de URL, " +
                    "enviando o orderNsu do checkout, e o sistema realizado um POST na api do InfinitePay retornando os dados de pagamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca de informações de pagamentos realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum checkout encontrado com o orderNsu fornecido")
    })
    @GetMapping("/status/{orderNsu}")
    public ResponseEntity<PaymentInfoResponseDTO> checkPayment(@PathVariable("orderNsu") String orderNsu) {

        log.info("Check payment request received: 'order_nsu' = '{}'", orderNsu);
        PaymentInfo paymentInfo = getPaymentInfoUseCase.getPayment(orderNsu);

        log.info("Payment info response: 'payment_info' = '{}'", paymentInfo);
        return ResponseEntity.ok().body(paymentInfoDTOMapper.toPaymentInfoResponseDTO(paymentInfo));

    }

}
