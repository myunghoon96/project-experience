package com.springboot_jpa_mall.repository.query;

import com.springboot_jpa_mall.entity.Order;
import com.springboot_jpa_mall.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@AllArgsConstructor
public class OrderQueryRepository{

    private final EntityManager em;

    public List<Order> findAllPaging2(int offset, int limit) {
        return em.createQuery(
                        "select distinct o from Order o" +
                                " join fetch o.member m", Order.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();

    }


}
