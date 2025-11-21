package pro.shop.settlement.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pro.shop.settlement.domain.SellerSettlement;
import pro.shop.settlement.domain.SellerSettlementRepository;
import pro.shop.settlement.domain.SettlementStatus;

import java.util.List;
import java.util.UUID;

@Repository
public class SellerSettlementRepositoryAdapter implements SellerSettlementRepository {

    @Autowired
    private SellerSettlementJpaRepository sellerSettlementJpaRepository;

    @Override
    public SellerSettlement save(SellerSettlement settlement) {
        return sellerSettlementJpaRepository.save(settlement);
    }

    @Override
    public List<SellerSettlement> findByStatus(SettlementStatus status) {
        return sellerSettlementJpaRepository.findByStatus(status);
    }

    @Override
    public List<SellerSettlement> findByStatusAndSeller(SettlementStatus status, UUID sellerId) {
        return sellerSettlementJpaRepository.findByStatusAndSellerId(status, sellerId);
    }

    @Override
    public void saveAll(List<SellerSettlement> settlements) {
        sellerSettlementJpaRepository.saveAll(settlements);
    }
}