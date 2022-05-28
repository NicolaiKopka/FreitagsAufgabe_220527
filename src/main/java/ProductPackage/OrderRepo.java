package ProductPackage;

import java.util.*;

public class OrderRepo {

    private Map<String, Order> orders;

    public OrderRepo() {
        orders = new HashMap<>();
    }

    public void addOrder(List<Product> productList) {
        var newOrder = new Order(productList);
        orders.putIfAbsent(newOrder.getId(), newOrder);
    }

    public List<Order> list() {
        return new ArrayList<>(orders.values());
    }

    public Optional<Order> getOrderById(String id) {
        return Optional.ofNullable(orders.get(id));
    }
}
