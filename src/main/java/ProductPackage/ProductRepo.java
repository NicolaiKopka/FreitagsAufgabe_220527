package ProductPackage;

import java.util.*;

public class ProductRepo {

    private Map<String, Product> allProducts = new HashMap<>();

    public ProductRepo(List<Product> productList) {
        for(Product product : productList){
            this.allProducts.putIfAbsent(product.getId(), product);
        }

    }

    public Optional<Product> getProductById(String id) {
        return Optional.ofNullable(allProducts.get(id));
    }

    public List<Product> list() {
        return new ArrayList<>(allProducts.values());
    }
}
