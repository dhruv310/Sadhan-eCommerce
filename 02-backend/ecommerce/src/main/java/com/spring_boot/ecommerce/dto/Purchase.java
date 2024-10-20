package com.spring_boot.ecommerce.dto;

import com.spring_boot.ecommerce.entity.Address;
import com.spring_boot.ecommerce.entity.Customer;
import com.spring_boot.ecommerce.entity.Order;
import com.spring_boot.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;

}
