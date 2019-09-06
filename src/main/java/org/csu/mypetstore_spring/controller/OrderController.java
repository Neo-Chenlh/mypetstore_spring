package org.csu.mypetstore_spring.controller;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.csu.mypetstore_spring.domain.*;
import org.csu.mypetstore_spring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;


    @RequestMapping("/addNewOrder")
    public String addNewOrder(Cart cart, Order order,HttpSession session) {

        session.setAttribute("cart",cart);
        session.setAttribute("order",order);

        List<CartItem> cartItems = cart.getCartItemList();
        for(int i=0;i<cartItems.size();i++){
            CartItem cartItem = cartItems.get(i);
            order.addLineItem(cartItem);
        }
        orderService.insertOrder(order);

        session.setAttribute("order",order);

        return "order/newOrderForm";
    }

    @RequestMapping("/confirmNewOrder")
    public String  confirmNewOrder(Order order , HttpSession session){

        java.sql.Date orderDate = new java.sql.Date(System.currentTimeMillis());

        Account account = (Account) session.getAttribute("account");
        String username = account.getUsername();
        String shipToFirstName = account.getFirstName();
        String shipToLastName = account.getLastName();

        if (username != null) {
            order.setUsername(username);
        }

        order.setShipToFirstName(shipToFirstName);
        order.setShipToLastName(shipToLastName);
        order.setOrderDate(orderDate);

        Order ord = (Order)session.getAttribute("order");
        List<LineItem> lineItems = ord.getLineItems();
        order.setLineItems(lineItems);
        List<Order> orderList = (List<Order>) session.getAttribute("orderList");
        orderList.add(order);
        Cart cart = (Cart)session.getAttribute("cart");
        order.setTotalPrice(cart.getSubTotal());

        orderService.insertOrder(order);
        session.setAttribute("order", order);
        session.setAttribute("orderList", orderList);


        return "order/confirmOrder";
    }

    @RequestMapping("/shippingForm")
    public String shippingForm(){

        return "order/shippingForm";
    }

    @RequestMapping("/viewListOrders")
    public String viewListOrders(){

        return "order/listOrders";
    }

    @RequestMapping("/viewOrder")
    public String viewOrder(){

        return "order/viewOrder";
    }

}
