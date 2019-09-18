package pl.javastart.products.repository;

import org.springframework.stereotype.Repository;
import pl.javastart.products.model.Category;
import pl.javastart.products.model.Product;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    private static List<Product> products;

    public ProductRepository() {
        products = new ArrayList<>();
        products.add(new Product("Mleko", 2.59, Category.FOOD));
        products.add(new Product("Kiełbaska", 12.59, Category.FOOD));
        products.add(new Product("Rolki", 125.99, Category.OTHERS));
        products.add(new Product("Pralka", 1009.99, Category.ITEMS));
        products.add(new Product("Sznurek", 5.59, Category.OTHERS));

    }

    public static List<Product> getAll() {
        return products;
    }

    @Override
    public String toString() {
        return "Nazwa produktu: " + products +
                " Cena: " + products;
    }
}

