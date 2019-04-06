package com.exmple.tests;


import com.example.extensions.listeners.AllureLoggingListener;
import com.example.extensions.listeners.TestLoggingListener;
import com.example.models.Product;
import com.exmple.TestBase;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.path.json.JsonPath;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.math.BigDecimal;
import java.util.*;

import static com.example.assertions.Conditions.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@Tag("catalog")
@Execution(ExecutionMode.SAME_THREAD)

@Feature("Catalog Service Tests")
@ExtendWith({AllureLoggingListener.class, TestLoggingListener.class})
public class CatalogServiceTests extends TestBase {

    public static Product testProduct;


    @BeforeAll
    static void setUp(){
        testProduct = new Product().setId("03fef6ac-1896-4ce8-bd69-b798f85c6e0b")
                .setName("Holy")
                .setDescription("Socks fit for a Messiah. You too can experience walking in water with these special edition beauties. Each hole is lovingly proggled to leave smooth edges. The only sock approved by a higher power.")
                .setImageUrl(Arrays.asList("/catalogue/images/holy_1.jpeg","/catalogue/images/holy_2.jpeg"))
                .setPrice(99.99)
                .setCount(1)
                .setTag(Arrays.asList("magic","action"));
    }



    @Test
    @Story("positive")
    void shouldGetListOfProducts(){
        List<Product> products = catalogService.getProducts()
                .shouldHave(statusCode(200))
                .shouldHave(header("Content-Type=" + userService.defaultContentType))
                .response.extract()
                .body().jsonPath().getList(".", Product.class);

        assertEquals(9, products.size());
        assertAll(
                ()-> assertEquals(testProduct.name, products.get(0).name),
                ()-> assertEquals(testProduct.description, products.get(0).description),
                ()-> assertEquals(testProduct.price, products.get(0).price),
                ()-> assertEquals(1, products.get(0).getCount())
        );
    }

    @Test
    @Story("negative")
    void shouldThrowParsingError(){
        List products = new ArrayList<>();
        assertThrows(IllegalStateException.class, ()->
                        /*products =*/ catalogService.getProducts()
                                .shouldHave(statusCode(200))
                                .response.extract().body().as(products.getClass()),
                "Cannot parse content to class java.util.ArrayList because no content-type was present in the response and no default parser has been set.");
    }

    @Test
    @Story("negative")
    void shouldThrowParsingError2(){
        List<Product> products;
        assertThrows(IllegalStateException.class, ()->
                        /*products =*/ Arrays.asList(catalogService.getProducts()
                                .shouldHave(statusCode(200))
                                .response.extract().body().as(Product[].class)),
                "Cannot parse content to class java.util.ArrayList because no content-type was present in the response and no default parser has been set.");
    }

    @Test
    @Story("positive")
    void shouldGetAllProducts(){
        assertThrows(IllegalStateException.class, ()-> catalogService.getProducts().shouldHave(statusCode(200))
                        .shouldHave(header("Content-Type=" + userService.defaultContentType))
                        .shouldHave(body(".", hasSize(9)))
                        .shouldHave(body(".[0].name", equalTo(testProduct.name))),
                "Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the response.");

    }

    @Test
    @Story("positive")
    void shouldGetProduct(){
        HashMap<String, Product> productMap = catalogService.getProduct(testProduct.getId())
                .shouldHave(statusCode(200))
                .shouldHave(header("Content-Type=" + userService.defaultContentType))
                .response.extract().body().jsonPath().get(".");
        //Variant-1 usng Jackson to map HashMap
        Product product = new ObjectMapper().convertValue(productMap, Product.class);
        //Variant-2 - using Gson to map HashMap
        //Gson gson = new Gson();
        //JsonElement jsonElement = gson.toJsonTree(productMap);
        //Product product = gson.fromJson(jsonElement, Product.class);
        assertEquals(testProduct.name, product.name);
        assertEquals(testProduct.description, product.description);
        assertEquals(testProduct.price, Double.valueOf(new BigDecimal(product.price).setScale(2, 2).toString()));
        assertEquals(1, product.getCount());
    }


    @Test
    @Story("positive")
    void shouldGetProductCount(){
        assertAll(()-> {
            JsonPath body = catalogService.getProductsCount()
                    .shouldHave(statusCode(200))
                    .shouldHave(header("Content-Type=" + userService.defaultContentType))
                    .response.extract().body().jsonPath();

                assertAll(
                        ()-> assertEquals(9, (Integer) body.get("size")),
                        ()-> assertNull(body.get("err"))
                );
            }
        );
    }

    @Test
    @Story("positive")
    void shouldGetTags(){
        JsonPath body = catalogService.getTags()
                .shouldHave(statusCode(200))
                .shouldHave(header("Content-Type=" + userService.defaultContentType))
                .response.extract().body().jsonPath();
        assertAll(
                ()-> assertEquals(11, (Integer) body.get("tags.size()")),
                ()-> assertNull(body.get("err"))
        );
    }
}
