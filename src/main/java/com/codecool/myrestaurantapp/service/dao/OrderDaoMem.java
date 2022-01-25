package com.codecool.myrestaurantapp.service.dao;

import com.codecool.myrestaurantapp.model.Customer;
import com.codecool.myrestaurantapp.model.Order;
import com.codecool.myrestaurantapp.model.Receipt;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class OrderDaoMem implements OrderDAO{

    private Set<Order> activeOrders = new HashSet<>();
    private Set<Order> fulfilledOrders = new HashSet<>();

    public int getStoredOrdersNumber () {
        return activeOrders.size() + fulfilledOrders.size();
    }

    @Override
    public void addOrder(Order order) {
        activeOrders.add(order);
    }

    @Override
    public Set<Order> getActiveOrders() {
        return activeOrders;
    }

    @Override
    public Set<Order> getFulfilledOrders() {
        return fulfilledOrders;
    }

    @Override
    public void deleteOrder(int orderId) {
        activeOrders.removeIf(activeOrder -> activeOrder.getId() == orderId);
    }

    @Override
    public void changeOrderStatus(int orderId) {
        Order fulfilledOrder = null;
        for (Order activeOrder : activeOrders) {
            if (orderId==activeOrder.getId()) {
                fulfilledOrder = activeOrder;
            }
        }
        if (fulfilledOrder!=null) {
            fulfilledOrders.add(fulfilledOrder);
            activeOrders.remove(fulfilledOrder);
        }
    }
}
