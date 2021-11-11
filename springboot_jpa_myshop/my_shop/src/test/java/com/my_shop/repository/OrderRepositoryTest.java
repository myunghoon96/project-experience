package com.my_shop.repository;

import com.my_shop.constant.ItemStatus;
import com.my_shop.entity.Item;
import com.my_shop.entity.Order;
import com.my_shop.entity.OrderItem;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderRepositoryTest {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    EntityManager em;

    public Item createSampleItem(){
        Item item = new Item();
        item.setItemName("item name");
        item.setDetail("item detail");
        item.setPrice(100);
        item.setItemStatus(ItemStatus.SELL);
        item.setStock(1000);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());
        return  item;
    }

    @Test
    @DisplayName("order, order_item 영속성 전이")
    public void cascadeTest(){
        Order order = new Order();

        for(int i=0; i<5; i++) {
            Item createdItem = this.createSampleItem();
            itemRepository.save(createdItem);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setItem(createdItem);
            orderItem.setOrderPrice(200);
            orderItem.setRegTime(LocalDateTime.now());
            orderItem.setUpdateTime(LocalDateTime.now());

            order.getOrderItems().add(orderItem);
        }

        orderRepository.save(order);
        em.flush();
        em.clear();

        Order savedOrder = orderRepository.findById(order.getId())
                .orElseThrow(EntityNotFoundException::new);

        assertEquals(5, savedOrder.getOrderItems().size());


    }


}