package pro.shop.payment.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pro.shop.payment.domain.PaymentFailure;
import pro.shop.payment.domain.PaymentFailureRepository;

@Repository
public class PaymentFailureRepositoryAdapter implements PaymentFailureRepository {

    @Autowired
    private PaymentFailureJpaRepository failureJpaRepository;

    @Override
    public PaymentFailure save(PaymentFailure paymentFailure) {
        return failureJpaRepository.save(paymentFailure);
    }
}
