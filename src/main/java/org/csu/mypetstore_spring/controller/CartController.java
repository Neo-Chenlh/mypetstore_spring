package org.csu.mypetstore_spring.controller;

import org.csu.mypetstore_spring.domain.*;
import org.csu.mypetstore_spring.service.CatelogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
public class CartController {

    @Autowired
    private CatelogService catelogService;

    @RequestMapping("/viewCart")
    public String viewCart(){

        return "cart/cart";
    }

    @RequestMapping("/addItemToCart")
    public String addItemToCart(@RequestParam("workingItemID")String workingItemID, HttpSession session) {

        Cart cart = (Cart) session.getAttribute("cart");
        //判断是否存在购物车
        if (cart == null) {
            cart = new Cart();
        }

        if (cart.containsItemId(workingItemID)) {
            cart.incrementQuantityByItemId(workingItemID);
        }
        else{

            Boolean isInStock = catelogService.isItemInStock(workingItemID);
            Item item = catelogService.getItem(workingItemID);
            cart.addItem(item, isInStock); //生成了LineItem和cartItem

            Order order = (Order) session.getAttribute("order");
            if (order == null) {
                order = new Order();
                order.addLineItem(cart.addItem(item, isInStock));
            } else {
                order.addLineItem(cart.addItem(item, isInStock));
            }

            List<Order> orderList = (List<Order>) session.getAttribute("orderList");
            if (orderList == null) {
                orderList = new ArrayList<>();
            }

            session.setAttribute("order", order);
            session.setAttribute("orderList", orderList);
        }

        session.setAttribute("cart", cart);



        return "cart/cart";
    }

    @RequestMapping("/removeViewCart")
    public String removeViewCart(@RequestParam("workingItemID")String workingItemID,HttpSession session) {

        Cart cart = (Cart) session.getAttribute("cart");
        Item item = cart.removeItemById(workingItemID);

        session.setAttribute("cart",cart);

        if (item == null)
        {
            session.setAttribute("msg","Attempted to remove null cartItem from cart");
            return "common/Error";
        }
        else
        {
            return "cart/cart";
        }
    }
}
