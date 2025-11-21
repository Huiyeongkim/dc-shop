package pro.shop.payment.presentation.dto;

import pro.shop.payment.application.dto.PaymentFailCommand;

public record PaymentFailureRequest(
        String orderId,
        String paymentKey,
        String code,
        String message,
        Long amount,
        String rawPayload
) {
    public PaymentFailCommand toCommand() {
        return new PaymentFailCommand(orderId, paymentKey, code, message, amount, rawPayload);
    }
}
