package pro.shop.seller.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.shop.seller.domain.Seller;

import java.util.UUID;

public interface SellerJpaRepository extends JpaRepository<Seller, UUID> {
}
