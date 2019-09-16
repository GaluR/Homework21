package pl.javastart.products.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductList {
    private static Product product;


    private static List<Product> products;

    public ProductList() {
        products = new ArrayList<>();
        products.add(new Product("Mleko", 2.59, Category.FOOD));
        products.add(new Product("Kiełbaska", 12.59, Category.FOOD));
        products.add(new Product("Rolki", 125.99, Category.OTHERS));
        products.add(new Product("Pralka", 1009.99, Category.ITEMS));
        products.add(new Product("Sznurek", 5.59, Category.OTHERS));

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public static List<Product> getAll() {
        return products;
    }

    @Override
    public String toString() {
        return "Nazwa produktu: " + product +
                " Cena: " + products;
    }
}

