package pro.shop.payment.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.shop.payment.domain.Payment;

import java.util.UUID;

public interface PaymentJpaRepository extends JpaRepository<Payment, UUID> {
}
