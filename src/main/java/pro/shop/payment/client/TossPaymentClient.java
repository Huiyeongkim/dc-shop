package pro.shop.payment.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import pro.shop.payment.application.dto.PaymentCommand;
import pro.shop.payment.client.dto.TossPaymentResponse;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


@Component
public class TossPaymentClient {

    private static final String CONFIRM_URL = "https://api.tosspayments.com/v1/payments/confirm";

    @Autowired
    private RestTemplate restTemplate;

    @Value("${payment.toss.secrete-key}")
    private String secretKey;

    public TossPaymentResponse confirm(PaymentCommand command) {
        if (secretKey == null) {
            throw new IllegalStateException("Toss secret key is not configured");
        }

        HttpHeaders headers = createHeaders();
        Map<String, Object> body = new HashMap<>();
        body.put("paymentKey", command.paymentKey());
        body.put("orderId", command.orderId());
        body.put("amount", command.amount());

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        try {
            return restTemplate.postForObject(CONFIRM_URL, entity, TossPaymentResponse.class);
        } catch (HttpStatusCodeException ex) {
            HttpStatusCode statusCode = ex.getStatusCode();
            String responseBody = ex.getResponseBodyAsString();
            throw new IllegalStateException("Toss confirm failed (" + statusCode + "): " + responseBody, ex);
        }
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String auth = secretKey + ":";
        String encoded = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
        headers.set(HttpHeaders.AUTHORIZATION, "Basic " + encoded);
        return headers;
    }

}
