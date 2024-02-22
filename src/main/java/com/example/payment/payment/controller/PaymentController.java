package com.example.payment.payment.controller;

import com.example.payment.payment.model.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PaymentController {
    Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @PostMapping("/payments")
    public ResponseEntity<Payment> getPayment(
            @RequestHeader String requestId,
            @RequestBody Payment payment
    ) {
        logger.info("Received payment requestId: " + requestId+
                " Payment amount: "+payment.getAmount());

        payment.setId(UUID.randomUUID().toString());
        payment.setAmount(payment.getAmount()+2);
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("requestId", requestId)
                .body(payment);
    }
}
