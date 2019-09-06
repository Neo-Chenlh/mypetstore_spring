package org.csu.mypetstore_spring.service;

import org.csu.mypetstore_spring.domain.Category;
import org.csu.mypetstore_spring.domain.Item;
import org.csu.mypetstore_spring.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatelogService {

    List<Category> getCategoryList();

    Category getCategory(String categoryID);

    Product getProduct(String productID);

    List<Product> getProductListByCategory(String categoryID);

    List<Product> searchProductList(String keyword);

    List<Item> getItemListByProduct(String productID);

    Item getItem(String itemID);

    boolean isItemInStock(String itemID);

    int getItemQuantity(String itemID);

}
