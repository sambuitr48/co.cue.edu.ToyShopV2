package services.impl.order;

import models.Order;
import repository.Repository;
import repository.impl.order.OrderRepositoryJDBCImpl;
import services.Service;

import java.util.List;

public class OrderServiceImpl implements Service<Order> {
    private Repository<Order> orderRepository;

    public OrderServiceImpl() {
        this.orderRepository = new OrderRepositoryJDBCImpl();
    }

    @Override
    public void add(Order order) {

    }

    @Override
    public List<Order> listAll() {
        return null;
    }
}
