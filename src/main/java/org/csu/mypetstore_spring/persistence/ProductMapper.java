package org.csu.mypetstore_spring.persistence;

import org.apache.ibatis.annotations.Mapper;
import org.csu.mypetstore_spring.domain.Product;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<Product> getProductListByCategory(String categoryID);

    Product getProduct(String productID);

    List<Product> searchProductList(String keyword);

}
