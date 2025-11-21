package pro.shop.payment.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pro.shop.payment.domain.Payment;
import pro.shop.payment.domain.PaymentRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class PaymentRepositoryAdapter implements PaymentRepository {

    @Autowired
    private PaymentJpaRepository paymentJpaRepository;

    @Override
    public Page<Payment> findAll(Pageable pageable) {
        return paymentJpaRepository.findAll(pageable);
    }

    @Override
    public Optional<Payment> findById(UUID id) {
        return paymentJpaRepository.findById(id);
    }

    @Override
    public Payment save(Payment payment) {
        return paymentJpaRepository.save(payment);
    }

}
