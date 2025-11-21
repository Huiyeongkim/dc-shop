package pro.shop.settlement.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.shop.settlement.domain.SellerSettlement;
import pro.shop.settlement.domain.SettlementStatus;

import java.util.List;
import java.util.UUID;

public interface SellerSettlementJpaRepository extends JpaRepository<SellerSettlement, UUID> {
    List<SellerSettlement> findByStatusAndSellerId(SettlementStatus status, UUID sellerId);

    List<SellerSettlement> findByStatus(SettlementStatus status);
}