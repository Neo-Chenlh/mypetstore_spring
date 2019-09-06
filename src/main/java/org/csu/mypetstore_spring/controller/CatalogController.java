package org.csu.mypetstore_spring.controller;

import org.csu.mypetstore_spring.domain.Category;
import org.csu.mypetstore_spring.domain.Item;
import org.csu.mypetstore_spring.domain.Product;
import org.csu.mypetstore_spring.service.CatelogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CatalogController {

    @Autowired
    private CatelogService catelogService;

    @GetMapping("/viewMain")
    public String viewMain(){
        return "catalog/main";
    }

    @GetMapping("/viewCategory")
    public String viewCategory(@RequestParam("categoryID")String categoryID, Model model){

        if(categoryID != null){

            Category category = catelogService.getCategory(categoryID);
            List<Product> productList = catelogService.getProductListByCategory(categoryID);
            model.addAttribute("category",category);
            model.addAttribute("productList",productList);
        }

        return "catalog/category";
    }

    @GetMapping("/viewProduct")
    public String viewProduct(@RequestParam("productID")String productID, Model model) {

        if(productID != null){

            Product product = catelogService.getProduct(productID);
            List<Item> itemList = catelogService.getItemListByProduct(productID);
            model.addAttribute("product",product);
            model.addAttribute("itemList",itemList);
        }

        return "catalog/product";
    }

    @GetMapping("/viewItem")
    public String viewItem(@RequestParam("itemID")String itemID, Model model) {

        if(itemID != null){

            Item item = catelogService.getItem(itemID);
            Product product = catelogService.getProduct(item.getProductID());
            model.addAttribute("item",item);
            model.addAttribute("product",product);
        }

        return "catalog/item";
    }

    @PostMapping("/searchProducts")
    public String searchProducts(@RequestParam("keyword")String keyword, Model model) {

        if(keyword != null){

            List<Product> productList = catelogService.searchProductList(keyword);
            model.addAttribute("productList",productList);
        }

        return "catalog/searchProducts";
    }

}
