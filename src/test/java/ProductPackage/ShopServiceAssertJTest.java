package ProductPackage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceAssertJTest {

    @Test
    void shouldSucceedIfBothProductListsIdentical() {
        var product1 = new EntertainmentProduct("TV");
        var product2 = new EntertainmentProduct("PlayStation");
        var product3 = new ElectronicProduct("CoffeeMaker");
        var productList = new ArrayList<Product>(List.of(product1, product2, product3));
        var productRepo = new ProductRepo(productList);
        var shopService = new ShopService(productRepo);
        List<Product> actual = shopService.listProducts();
        assertThat(actual).containsExactlyInAnyOrderElementsOf(productList);
    }

    @Test
    void shouldFailIfNonExistentProductAddedToOrder() {
        var product1 = new EntertainmentProduct("TV");
        var product2 = new EntertainmentProduct("PlayStation");
        var product3 = new ElectronicProduct("CoffeeMaker");
        var product4 = new ElectronicProduct("Microwave");
        var productList = new ArrayList<Product>(List.of(product1, product2, product3));
        var productRepo = new ProductRepo(productList);
        var shopService = new ShopService(productRepo);
        var productListWithId = new ArrayList<>(List.of(product1.getId(), product2.getId(), product3.getId(), product4.getId()));

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> shopService.addOrder(productListWithId))
                .withMessage("Product does not exist");

        assertThat(shopService.listProducts().size()).isEqualTo(3);
    }

}