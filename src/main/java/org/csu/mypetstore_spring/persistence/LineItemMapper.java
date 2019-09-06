package org.csu.mypetstore_spring.persistence;

import org.apache.ibatis.annotations.Mapper;
import org.csu.mypetstore_spring.domain.LineItem;

import java.util.List;

@Mapper
public interface LineItemMapper {

    List<LineItem> getLineItemsByOrderId(int orderId);

    void insertLineItem(LineItem lineItem);
}
