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

//    public Product getProductById(String id) {
//        Optional<Product> currentProduct = productRepo.getProductById(id);
//        return currentProduct.orElseThrow(() -> new RuntimeException("Product does not exist"));
//    }

    // as Stream
    public Product getProductById(String id) {
        return listProducts().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst().orElseThrow(() -> new RuntimeException("Product does not exist"));
    }

    public List<Product> listProducts() {
        return productRepo.list();
    }

//    public void addOrder(List<String> idList) {
//        List<Product> productList = new ArrayList<>();
//        for(String id : idList) {
//            Product product = productRepo.getProductById(id).orElseThrow(() -> new RuntimeException("Product does not exist"));
//            productList.add(product);
//
//        }
//        orderRepo.addOrder(productList);
//    }

        // as Stream
        public void addOrder(List<String> idList) {
        List<Product> productList = idList.stream()
                .map(this::getProductById)
                .toList();
        orderRepo.addOrder(productList);
    }

    public List<Order> listOrders() {
        return orderRepo.list();
    }

    public Order getOrderById(String id) {
        Optional<Order> currentOrder = orderRepo.getOrderById(id);
        return currentOrder.orElseThrow(() -> new RuntimeException("Order does not exist"));
    }

    public Product getProductsByName(String name) {
        return listProducts().stream()
                .filter(p -> p.getProductName().equals(name))
                .findFirst().orElseThrow(() -> new RuntimeException("Product does not exist"));
    }
}
