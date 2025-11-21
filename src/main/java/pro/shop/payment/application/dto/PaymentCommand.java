package pro.shop.payment.application.dto;

public record PaymentCommand(
        String paymentKey,
        String orderId,
        Long amount
) {
}
