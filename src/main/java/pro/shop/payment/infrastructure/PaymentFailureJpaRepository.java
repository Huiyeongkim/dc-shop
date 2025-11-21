package pro.shop.payment.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.shop.payment.domain.PaymentFailure;

import java.util.UUID;

public interface PaymentFailureJpaRepository extends JpaRepository<PaymentFailure, UUID> {

}
