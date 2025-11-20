package pro.shop.product.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.shop.product.domain.Product;

import java.util.UUID;

public interface ProductJpaRepository extends JpaRepository<Product, UUID> {


}
