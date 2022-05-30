package ProductPackage;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void shouldReturnTrueIfAllProductsReturned() {
        var product1 = new EntertainmentProduct("TV");
        var product2 = new EntertainmentProduct("PlayStation");
        var product3 = new ElectronicProduct("CoffeeMaker");
        var productList = new ArrayList<Product>(List.of(product1, product2, product3));
        var productRepo = new ProductRepo(productList);
        var shopService = new ShopService(productRepo);
        List<Product> actual = shopService.listProducts();
        assertTrue(actual.containsAll(productList));
    }

    @Test
    void shouldReturnCorrectProductById() {
        var product1 = new EntertainmentProduct("TV");
        var product2 = new EntertainmentProduct("PlayStation");
        var product3 = new ElectronicProduct("CoffeeMaker");
        var productList = new ArrayList<Product>(List.of(product1, product2, product3));
        var productRepo = new ProductRepo(productList);
        var shopService = new ShopService(productRepo);
        Product actual = shopService.getProductById(product2.getId());
        assertEquals(product2, actual);
    }

    @Test
    void shouldReturnTrueIfAllOrdersAddedAndReturnedCorrectly() {
        var product1 = new EntertainmentProduct("TV");
        var product2 = new EntertainmentProduct("PlayStation");
        var product3 = new ElectronicProduct("CoffeeMaker");
        var product4 = new ElectronicProduct("Microwave");
        var product5 = new ElectronicProduct("Oven");
        var product6 = new ElectronicProduct("CellPhone");
        var productList = new ArrayList<Product>(List.of(product1, product2, product3, product4, product5, product6));

        var productRepo = new ProductRepo(productList);
        var shopService = new ShopService(productRepo);

        shopService.addOrder(List.of(product1.getId(), product2.getId(), product3.getId()));
        shopService.addOrder(List.of(product4.getId(), product5.getId(), product6.getId()));

        List<Order> orderList = shopService.listOrders();
        assertEquals(2, orderList.size());
    }

    @Test
    void shouldReturnCorrectOrderById() {
        var product1 = new EntertainmentProduct("TV");
        var product2 = new EntertainmentProduct("PlayStation");
        var product3 = new ElectronicProduct("CoffeeMaker");
        var product4 = new ElectronicProduct("Microwave");
        var product5 = new ElectronicProduct("Oven");
        var product6 = new ElectronicProduct("CellPhone");
        var productList = new ArrayList<Product>(List.of(product1, product2, product3, product4, product5, product6));
        var productRepo = new ProductRepo(productList);
        var shopService = new ShopService(productRepo);

        shopService.addOrder(List.of(product1.getId(), product2.getId(), product3.getId()));
        List<Order> orders = shopService.listOrders();
        String orderId1 = orders.get(0).getId();
        List<Product> actual = shopService.getOrderById(orderId1).getProductList();
        assertTrue(actual.containsAll(List.of(product1, product2, product3)));
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

        try{
            shopService.addOrder(List.of(product1.getId(), product2.getId(), product3.getId(), product4.getId()));
            fail();
        } catch (RuntimeException e) {}
    }

    @Test
    void shouldReturnCorrectCategoryOfProduct() {
        var product1 = new EntertainmentProduct("TV");
        var product2 = new EntertainmentProduct("PlayStation");
        var product3 = new ElectronicProduct("CoffeeMaker");
        var productList = new ArrayList<Product>(List.of(product1, product2, product3));
        var productRepo = new ProductRepo(productList);
        var shopService = new ShopService(productRepo);
        String electronicActual = shopService.getProductById(product3.getId()).getCategory();
        assertEquals("Electronics", electronicActual);
        String entertainmentActual = shopService.getProductById(product1.getId()).getCategory();
        assertEquals("Entertainment", entertainmentActual);
    }

    @Test
    void shouldReturnNullIfNoProductFound() {
        var product1 = new EntertainmentProduct("TV");
        var product2 = new EntertainmentProduct("PlayStation");
        var product3 = new ElectronicProduct("CoffeeMaker");
        var productList = new ArrayList<Product>(List.of(product1, product2));
        var productRepo = new ProductRepo(productList);
        var shopService = new ShopService(productRepo);
        try{
            shopService.getProductById(product3.getId());
            fail();
        } catch (RuntimeException e) {}

    }

    @Test
    void shouldReturnNullIfNoOrderFound() {
        var product1 = new EntertainmentProduct("TV");
        var product2 = new EntertainmentProduct("PlayStation");
        var product3 = new ElectronicProduct("CoffeeMaker");
        var product4 = new ElectronicProduct("Microwave");
        var product5 = new ElectronicProduct("Oven");
        var product6 = new ElectronicProduct("CellPhone");
        var productList = new ArrayList<Product>(List.of(product1, product2, product3, product4, product5, product6));
        var productRepo = new ProductRepo(productList);
        var shopService = new ShopService(productRepo);
        var newOrder = new Order(List.of(product1, product2, product3));
        try{
            shopService.getOrderById(newOrder.getId());
            fail();
        } catch (RuntimeException e) {}
    }

    @Test
    void shouldReturnProductByName() {
        var product1 = new EntertainmentProduct("TV");
        var product2 = new EntertainmentProduct("PlayStation");
        var product3 = new ElectronicProduct("CoffeeMaker");
        var product4 = new ElectronicProduct("Microwave");
        var product5 = new ElectronicProduct("Oven");
        var product6 = new ElectronicProduct("CellPhone");
        var productList = new ArrayList<Product>(List.of(product1, product2, product3, product4, product5, product6));
        var productRepo = new ProductRepo(productList);
        var shopService = new ShopService(productRepo);
        assertEquals(product1, shopService.getProductsByName("TV"));
    }

    @Test
    void shouldFailIfProductByNameDoesNotExist() {
        var product1 = new EntertainmentProduct("TV");
        var product2 = new EntertainmentProduct("PlayStation");
        var product3 = new ElectronicProduct("CoffeeMaker");
        var product4 = new ElectronicProduct("Microwave");
        var product5 = new ElectronicProduct("Oven");
        var product6 = new ElectronicProduct("CellPhone");
        var productList = new ArrayList<Product>(List.of(product1, product2, product3, product4, product5, product6));
        var productRepo = new ProductRepo(productList);
        var shopService = new ShopService(productRepo);
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> shopService.getProductsByName("Vase"))
                .withMessage("Product does not exist");
    }

}