package org.csu.mypetstore_spring.persistence;

import org.apache.ibatis.annotations.Mapper;
import org.csu.mypetstore_spring.domain.Item;

import java.util.List;

@Mapper
public interface ItemMapper {

    void updateInventoryQuantity(String itemID, int increment);

    int getInventoryQuantity(String itemID);

    List<Item> getItemListByProduct(String productID);

    Item getItem(String itemID);
}
