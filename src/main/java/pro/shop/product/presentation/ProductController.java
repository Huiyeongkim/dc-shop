package pro.shop.product.presentation;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pro.shop.common.ResponseEntity;
import pro.shop.product.application.ProductService;
import pro.shop.product.application.dto.ProductInfo;
import pro.shop.product.presentation.dto.ProductRequest;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("${api.v1}/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "상품 목록 조회", description = "등록된 상품을 페이지 단위로 조회한다.")
    @GetMapping
    public ResponseEntity<List<ProductInfo>> findAll(Pageable pageable) {
        return productService.findAll(pageable);
    }

    @Operation(summary = "상품 생성", description = "상품을 등록합니다.")
    @PostMapping
    public ResponseEntity<ProductInfo> create(@RequestBody ProductRequest request) {
        return productService.create(request.toCommand());
    }

    @Operation(summary = "상품 수정", description = "등록된 상품을 수정합니다.")
    @PutMapping("/{id}")
    public ResponseEntity<ProductInfo> update(@RequestBody ProductRequest request, @PathVariable UUID id) {
        return productService.update(id, request.toCommand());
    }

    @Operation(summary = "상품 삭제", description = "등록된 상품을 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        return productService.delete(id);
    }
}
