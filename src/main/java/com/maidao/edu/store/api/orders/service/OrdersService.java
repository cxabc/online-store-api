package com.maidao.edu.store.api.orders.service;

/**
 * @author: chen.star
 * @date: 2024/3/12 13:03
 * @description: null
 **/

import com.maidao.edu.store.api.orders.model.Orders;
import com.maidao.edu.store.api.orders.qo.OrdersQo;
import com.maidao.edu.store.common.exception.ServiceException;
import org.springframework.data.domain.Page;

public interface OrdersService {

    Page<Orders> orders(OrdersQo qo, boolean admin);

    Orders order(int id);

    void save(Orders order) throws ServiceException;

    void pay(Orders order) throws ServiceException;

    void receive(Orders order) throws ServiceException;

    void evalProduct(Orders order) throws ServiceException;

    void remove(int id);
}

