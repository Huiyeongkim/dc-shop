package pro.shop.payment.presentation;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.shop.common.ResponseEntity;
import pro.shop.payment.application.PaymentService;
import pro.shop.payment.application.dto.PaymentInfo;

import java.util.List;

@RestController
@RequestMapping("${api.v1}/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Operation(summary = "결제 내열 조회", description = "확정된 결제 정보를 페이지 단위로 조회한다.")
    @GetMapping
    public ResponseEntity<List<PaymentInfo>> findAll(Pageable pageable) {
        return paymentService.findAll(pageable);
    }


}
