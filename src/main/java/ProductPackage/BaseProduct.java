package ProductPackage;

import java.util.UUID;

public abstract class BaseProduct {

    private String id;
    private String productName;

    public BaseProduct(String productName) {
        this.id = UUID.randomUUID().toString();
        this.productName = productName;
    }

    public String getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }
}
