package br.com.nordestech.checkout.presentation.rest;

import br.com.nordestech.checkout.domain.model.CheckoutInfo;
import br.com.nordestech.checkout.domain.model.PaymentInfo;
import br.com.nordestech.checkout.domain.service.CreatePayloadUseCase;
import br.com.nordestech.checkout.domain.service.GetPaymentInfoUseService;
import br.com.nordestech.checkout.domain.service.ProcessCheckoutInfoUseCase;
import br.com.nordestech.checkout.port.output.model.Checkout;
import br.com.nordestech.checkout.presentation.mapper.CheckoutDTOMapper;
import br.com.nordestech.checkout.presentation.mapper.CheckoutInfoDTOMapper;
import br.com.nordestech.checkout.presentation.mapper.PayloadDTOMapper;
import br.com.nordestech.checkout.presentation.mapper.PaymentInfoDTOMapper;
import br.com.nordestech.checkout.presentation.rest.request.PayloadRequestDTO;
import br.com.nordestech.checkout.presentation.rest.response.CheckoutInfoResponseDTO;
import br.com.nordestech.checkout.presentation.rest.response.CheckoutResponseDTO;
import br.com.nordestech.checkout.presentation.rest.response.PaymentInfoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/checkout")
@RequiredArgsConstructor
@Tag(name = "Checkout", description = "Endpoints para gerenciamento dos checkouts")
public class PayloadController {

    private final CreatePayloadUseCase createPayloadUseCase;
    private final ProcessCheckoutInfoUseCase processCheckoutInfoUseCase;
    private final GetPaymentInfoUseService getPaymentInfoUseCase;

    private final CheckoutInfoDTOMapper checkoutInfoDTOMapper;
    private final PayloadDTOMapper payloadDTOMapper;
    private final CheckoutDTOMapper checkoutDTOMapper;
    private final PaymentInfoDTOMapper paymentInfoDTOMapper;

    @Operation(summary = "Gerar pedido de checkout",
            description = "Gera um novo pedido de checkout por meio de POST na api do InfinitePay")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido de checkout criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos")
    })
    @PostMapping("new")
    public ResponseEntity<CheckoutResponseDTO> newCheckout(@RequestBody @Valid PayloadRequestDTO payloadRequestDTO) {

        Checkout checkout = createPayloadUseCase.createPayload(payloadDTOMapper.toPayload(payloadRequestDTO));

        return ResponseEntity.ok(checkoutDTOMapper.toCheckoutResponseDTO(checkout));

    }

    @Operation(
            summary = "Retornar informações do checkout",
            description = "Retorna as informações de um pedido de checkout por meio de parâmetros na URL gerado pelo api do InfinitePay"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informações de checkout retornadas com sucesso " +
                    "(Caso não exista as informações salvas, é realizado uma persistência antes de retornar os dados"),
            @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos")
    })
    @GetMapping("return")
    public ResponseEntity<CheckoutInfoResponseDTO> returnCheckout(
            @RequestParam(name = "receipt_url") String receiptUrl,
            @RequestParam(name = "order_nsu") String orderNsu,
            @RequestParam(name = "slug") String slug,
            @RequestParam(name = "capture_method") String captureMethod,
            @RequestParam(name = "transaction_nsu") String transactionNsu,
            @RequestParam(name = "transaction_id") String transactionId) {

        CheckoutInfo checkoutInfo = processCheckoutInfoUseCase.process(checkoutInfoDTOMapper.toCheckoutInfo(
                receiptUrl, orderNsu, slug, captureMethod, transactionNsu, transactionId
        ));

        return ResponseEntity.ok().body(checkoutInfoDTOMapper.toCheckoutInfoResponseDTO(checkoutInfo));

    }

    @Operation(summary = "Busca informações de pagamento do checkout",
            description = "Busca as informações de pagamento do pedido de checkout por meio de parâmetro de URL, " +
                    "enviando o id do checkout, e o sistema realizado um POST na api do InfinitePay retornando os dados de pagamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca de informações de pagamentos realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum checkout encontrado com o id fornecido")
    })
    @GetMapping("status/{id}")
    public ResponseEntity<PaymentInfoResponseDTO> checkPayment(@PathVariable("id") String checkoutInfoId) {

        PaymentInfo paymentInfo = getPaymentInfoUseCase.getPayment(checkoutInfoId);

        return ResponseEntity.ok().body(paymentInfoDTOMapper.toPaymentInfoResponseDTO(paymentInfo));

    }

}
