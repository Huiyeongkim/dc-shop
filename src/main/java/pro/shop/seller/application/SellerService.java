package pro.shop.seller.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.shop.common.ResponseEntity;
import pro.shop.seller.application.dto.SellerCommand;
import pro.shop.seller.application.dto.SellerInfo;
import pro.shop.seller.domain.Seller;
import pro.shop.seller.domain.SellerRepository;

import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class SellerService {

    @Autowired private SellerRepository sellerRepository;

    public ResponseEntity<List<SellerInfo>> findAll(Pageable pageable) {
        Page<Seller> page = sellerRepository.findAll(pageable);
        List<SellerInfo> infos = page.stream()
                .map(SellerInfo::from)
                .toList();
        return new ResponseEntity<>(HttpStatus.OK.value(), infos, page.getTotalElements());
    }

    public ResponseEntity<SellerInfo> create(SellerCommand command) {
        Seller seller = Seller.register(
                command.companyName(),
                command.representativeName(),
                command.email(),
                command.phone(),
                command.businessNumber(),
                command.address(),
                command.status()
        );
        Seller saved = sellerRepository.save(seller);
        return new ResponseEntity<>(HttpStatus.CREATED.value(), SellerInfo.from(saved), 1);
    }

    public ResponseEntity<SellerInfo> update(UUID id, SellerCommand command) {
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Seller not found: " + id));

        seller.update(
                command.companyName(),
                command.representativeName(),
                command.email(),
                command.phone(),
                command.businessNumber(),
                command.address(),
                command.status()
        );

        Seller updated = sellerRepository.save(seller);
        return new ResponseEntity<>(HttpStatus.OK.value(), SellerInfo.from(updated), 1);
    }

    public ResponseEntity<Void> delete(UUID id) {
        sellerRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT.value(), null, 0);
    }
}