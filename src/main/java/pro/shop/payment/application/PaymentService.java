package pro.shop.payment.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pro.shop.common.ResponseEntity;
import pro.shop.payment.application.dto.PaymentCommand;
import pro.shop.payment.application.dto.PaymentFailCommand;
import pro.shop.payment.application.dto.PaymentFailureInfo;
import pro.shop.payment.application.dto.PaymentInfo;
import pro.shop.payment.client.TossPaymentClient;
import pro.shop.payment.client.dto.TossPaymentResponse;
import pro.shop.payment.domain.Payment;
import pro.shop.payment.domain.PaymentFailure;
import pro.shop.payment.domain.PaymentFailureRepository;
import pro.shop.payment.domain.PaymentRepository;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final TossPaymentClient tossPaymentClient;
    private final PaymentFailureRepository paymentFailureRepository;

    public ResponseEntity<List<PaymentInfo>> findAll(Pageable pageable) {
        Page<Payment> page = paymentRepository.findAll(pageable);
        List<PaymentInfo> paymentInfos = page.stream()
                .map(PaymentInfo::from)
                .toList();

        return new ResponseEntity<>(HttpStatus.OK.value(), paymentInfos, page.getTotalElements());
    }

    public ResponseEntity<PaymentInfo> confirm(PaymentCommand command) {
        TossPaymentResponse tossPayment = tossPaymentClient.confirm(command);
        Payment payment = Payment.create(
                tossPayment.paymentKey(),
                tossPayment.orderId(),
                tossPayment.totalAmount()
        );
        LocalDateTime approvedAt = tossPayment.approvedAt() != null ? tossPayment.approvedAt().toLocalDateTime() : null;
        LocalDateTime requestedAt = tossPayment.requestedAt() != null ? tossPayment.requestedAt().toLocalDateTime() : null;
        payment.markConfirmed(tossPayment.method(), approvedAt, requestedAt);

        Payment savedPayment = paymentRepository.save(payment);
        return new ResponseEntity<>(HttpStatus.CREATED.value(), PaymentInfo.from(savedPayment), 1);
    }

    public ResponseEntity<PaymentFailureInfo> recordFailure(PaymentFailCommand command) {
        PaymentFailure failure = PaymentFailure.from(
                command.orderId(),
                command.paymentKey(),
                command.errorCode(),
                command.errorMessage(),
                command.amount(),
                command.rawPayload()
        );
        PaymentFailure saved = paymentFailureRepository.save(failure);
        return new ResponseEntity<>(HttpStatus.OK.value(), PaymentFailureInfo.from(saved), 1);
    }

}
