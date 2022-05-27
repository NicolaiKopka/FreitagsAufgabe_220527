package ProductPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class Order {

    private String id;
    private List<Product> productsInOrder = new ArrayList<>();

    public Order(List<Product> productList) {
        this.id = UUID.randomUUID().toString();
        productsInOrder.addAll(productList);
    }

    public String getId() {
        return id;
    }

    public List<Product> getProductList() {
        return productsInOrder;
    }
}
