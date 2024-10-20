package com.spring_boot.ecommerce.service;

import com.spring_boot.ecommerce.dto.Purchase;
import com.spring_boot.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
