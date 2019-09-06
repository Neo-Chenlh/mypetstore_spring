package org.csu.mypetstore_spring.service.impl;

import org.csu.mypetstore_spring.domain.Category;
import org.csu.mypetstore_spring.domain.Item;
import org.csu.mypetstore_spring.domain.Product;
import org.csu.mypetstore_spring.persistence.CategoryMapper;
import org.csu.mypetstore_spring.persistence.ItemMapper;
import org.csu.mypetstore_spring.persistence.ProductMapper;
import org.csu.mypetstore_spring.service.CatelogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatelogServiceImpl implements CatelogService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public List<Category> getCategoryList() {
        return categoryMapper.getCategoryList();
    }

    @Override
    public Category getCategory(String categoryID) {
        return categoryMapper.getCategory(categoryID);
    }

    @Override
    public Product getProduct(String productID) {
        return productMapper.getProduct(productID);
    }

    @Override
    public List<Product> getProductListByCategory(String categoryID) {
        return productMapper.getProductListByCategory(categoryID);
    }

    @Override
    public List<Product> searchProductList(String keyword) {
        return productMapper.searchProductList(keyword);
    }

    @Override
    public List<Item> getItemListByProduct(String productID) {
        return itemMapper.getItemListByProduct(productID);
    }

    @Override
    public Item getItem(String itemID) {
        return itemMapper.getItem(itemID);
    }

    @Override
    public boolean isItemInStock(String itemID) {
        return itemMapper.getInventoryQuantity(itemID)>0;
    }

    @Override
    public int getItemQuantity(String itemID) {
        return itemMapper.getInventoryQuantity(itemID);
    }
}
