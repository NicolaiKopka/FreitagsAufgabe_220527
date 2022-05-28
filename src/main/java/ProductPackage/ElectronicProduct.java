package ProductPackage;

import java.util.UUID;

class ElectronicProduct extends BaseProduct implements Product {

    private String category = "Electronics";

    public ElectronicProduct(String productName) {
        super(productName);
    }

    public String getCategory() { return category;}
 }
