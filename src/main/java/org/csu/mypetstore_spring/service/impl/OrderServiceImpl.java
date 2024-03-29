package org.csu.mypetstore_spring.service.impl;

import org.csu.mypetstore_spring.domain.Item;
import org.csu.mypetstore_spring.domain.LineItem;
import org.csu.mypetstore_spring.domain.Order;
import org.csu.mypetstore_spring.domain.Sequence;
import org.csu.mypetstore_spring.persistence.ItemMapper;
import org.csu.mypetstore_spring.persistence.LineItemMapper;
import org.csu.mypetstore_spring.persistence.OrderMapper;
import org.csu.mypetstore_spring.persistence.SequenceMapper;
import org.csu.mypetstore_spring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private LineItemMapper lineItemMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private SequenceMapper sequenceMapper;

    @Override
    public int getnextID(String name)
    {
        Sequence sequence=new Sequence(name,-1);
        sequence = (Sequence) sequenceMapper.getSequence(sequence);
        if(sequence==null)
        {
            throw new RuntimeException("Error:A null sequence was returned from the database (could not get next \"+name\n"+"+\" sequence).");
        }
        Sequence parameterObject=new Sequence(name,sequence.getnextID()+1);
        sequenceMapper.updateSequence(parameterObject);
        return sequence.getnextID();
    }

    @Override
    public void insertOrder(Order order)
    {
        order.setorderID(getnextID("ordernum"));
        for(int i=0;i<order.getLineItems().size();i++)
        {
            LineItem lineItem=(LineItem)order.getLineItems().get(i);
            itemMapper.updateInventoryQuantity(lineItem.getItemId(),lineItem.getQuantity());
        }
        orderMapper.insertOrder(order);
        orderMapper.insertOrderStatus(order);
        for(int i=0;i<order.getLineItems().size();i++)
        {
            LineItem lineItem=(LineItem)order.getLineItems().get(i);
            lineItem.setOrderId(order.getorderID());
            lineItemMapper.insertLineItem(lineItem);
        }
    }

    @Override
    public Order getOrder(int orderId){
        Order order=orderMapper.getOrder(orderId);
        order.setLineItems(lineItemMapper.getLineItemsByOrderId(orderId));
        for(int i=0;i<order.getLineItems().size();i++){
            LineItem lineItem=(LineItem)order.getLineItems().get(i);
            Item item=itemMapper.getItem(lineItem.getItemId());

            item.setQuantity(itemMapper.getInventoryQuantity(lineItem.getItemId()));
            lineItem.setItem(item);
        }

        return order;
    }

    @Override
    public List<Order> getOrdersByUsername(String username){
        return orderMapper.getOrdersByUsername(username);
    }

}
