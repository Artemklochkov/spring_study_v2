package com.example.payment.payment.controller;

import com.example.payment.payment.model.Purchase;
import com.example.payment.payment.repository.PurchaseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    private final PurchaseRepository purchaseRepository;

    public PurchaseController(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @PostMapping
    public void storePurchase(
            @RequestBody Purchase  purchase
            ) {
        purchaseRepository.storePurchase(purchase);
    }

    @GetMapping
    public List<Purchase> getPurchase(){
        return purchaseRepository.findAllPurchase();
    }
}
