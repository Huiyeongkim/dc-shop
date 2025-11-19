package pro.shop.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Operation(
            summary = "루트 경로 테스트",
            description = "루트 경로 \n"
    )
    @GetMapping("/")
    public String test() {
        return "hello";
    }

}
