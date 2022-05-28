package ProductPackage;

import java.util.UUID;

public class EntertainmentProduct extends BaseProduct implements Product {

    private String category = "Entertainment";

    public EntertainmentProduct(String productName) {
        super(productName);
    }

    public String getCategory() { return category;}
}
