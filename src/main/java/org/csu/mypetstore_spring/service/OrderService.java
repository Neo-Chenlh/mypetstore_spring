package org.csu.mypetstore_spring.service;

import org.csu.mypetstore_spring.domain.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    int getnextID(String name);

    void insertOrder(Order order);

    Order getOrder(int orderId);

    List<Order> getOrdersByUsername(String username);

}
