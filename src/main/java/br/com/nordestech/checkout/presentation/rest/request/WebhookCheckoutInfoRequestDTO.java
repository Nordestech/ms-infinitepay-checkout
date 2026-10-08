package br.com.nordestech.checkout.presentation.rest.request;

import br.com.nordestech.checkout.domain.model.Item;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record WebhookCheckoutInfoRequestDTO(

        @JsonProperty("invoice_slug")
        String invoiceSlug,

        BigDecimal amount,

        @JsonProperty("paid_amount")
        BigDecimal paidAmount,

        Integer installments,

        @JsonProperty("capture_method")
        String captureMethod,

        @JsonProperty("transaction_nsu")
        String transactionNsu,

        @JsonProperty("order_nsu")
        String orderNsu,

        @JsonProperty("receipt_url")
        String receiptUrl,

        List<ItemDTO>items

) {

    public record ItemDTO(

            Integer quantity,

            BigDecimal price,

            String description
    ) {
        public Item toModel() {
            return new Item(this.quantity, this.price, this.description);
        }
    }
}
