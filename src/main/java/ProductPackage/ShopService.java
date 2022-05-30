package ProductPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShopService {

    private ProductRepo productRepo;
    private OrderRepo orderRepo = new OrderRepo();

    public ShopService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Product getProductById(String id) {
        Optional<Product> currentProduct = productRepo.getProductById(id);
        return currentProduct.orElseThrow(() -> new RuntimeException("Product does not exist"));
    }

    public List<Product> listProducts() {
        return productRepo.list();
    }

    public void addOrder(List<String> idList) {
        List<Product> productList = new ArrayList<>();
        for(String id : idList) {
            Product product = productRepo.getProductById(id).orElseThrow(() -> new RuntimeException("Product does not exist"));
            productList.add(product);

        }
        orderRepo.addOrder(productList);
    }

    public List<Order> listOrders() {
        return orderRepo.list();
    }

    public Order getOrderById(String id) {
        Optional<Order> currentOrder = orderRepo.getOrderById(id);
        return currentOrder.orElseThrow(() -> new RuntimeException("Order does not exist"));
    }
}
