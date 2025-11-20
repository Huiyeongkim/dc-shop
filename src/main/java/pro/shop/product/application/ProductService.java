package pro.shop.product.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.shop.common.ResponseEntity;
import pro.shop.product.application.dto.ProductCommand;
import pro.shop.product.application.dto.ProductInfo;
import pro.shop.product.domain.Product;
import pro.shop.product.domain.ProductRepository;

import java.util.List;
import java.util.UUID;

@Transactional(readOnly = true)
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<List<ProductInfo>> findAll(Pageable pageable) {
        Page<Product> page = productRepository.findAll(pageable);
        List<ProductInfo> products = page.stream()
                .map(ProductInfo::from)
                .toList();
        return new ResponseEntity<>(HttpStatus.OK.value(), products, page.getTotalElements());
    }

    @Transactional
    public ResponseEntity<ProductInfo> create(ProductCommand command) {
        Product product = Product.create(
                command.sellerId(),
                command.name(),
                command.description(),
                command.price(),
                command.stock(),
                command.status(),
                command.operatorId());
        Product savedProduct = productRepository.save(product);
        return new ResponseEntity<>(HttpStatus.OK.value(), ProductInfo.from(savedProduct), 1);
    }

    @Transactional
    public ResponseEntity<ProductInfo> update(UUID id, ProductCommand command) {
        Product findProduct = productRepository.findById(id)
                .orElseThrow();
        findProduct.update(command.name(), command.description(), command.price(), command.stock(), command.status(), command.operatorId());
        return new ResponseEntity<>(HttpStatus.OK.value(), ProductInfo.from(findProduct), 1);
    }

    @Transactional
    public ResponseEntity<?> delete(UUID id) {
        productRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT.value(), null, 1);
    }
}
