package pl.javastart.products.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.javastart.products.model.Category;
import pl.javastart.products.model.Product;
import pl.javastart.products.repository.ProductRepository;

import java.util.List;

@Controller
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @ResponseBody
    @RequestMapping("/products")
    public String showAllProducts(@RequestParam(required = false) Category category) {
        List<Product> all = productRepository.getAll();
        String result = "";
        double sum = 0;
        switch (category) {
            case FOOD:
                result = getProducts(all, result, Category.FOOD);
                sum = getSum(all, sum, category);
                break;
            case OTHERS:
                result = getProducts(all, result, Category.OTHERS);
                sum = getSum(all, sum, category);
                break;
            case ITEMS:
                result = getProducts(all, result, Category.ITEMS);
                sum = getSum(all, sum, category);
                break;
            default:
                for (Product product : all) {
                    result += product.toString() + "<br/>";
                }
                for (int i = 0; i < all.size(); i++) {
                    sum += all.get(i).getPrice();
                }
                break;
        }
        return result + "<br/> Sumaryczna cena produktów z listy to: " + sum;

    }

    private String getProducts(List<Product> all, String result, Category items) { // metoda wyświetlająca poszczególne produkty z konkretnymi kategoriami
        for (Product product : all) {
            if (product.getCategory().equals(items)) {
                result += product.toString() + "<br/>";
            }
        }
        return result;
    }

    private double getSum(List<Product> all, double sum, Category food) { //metoda obliczająca sumaryczną cenę dla poszczególnych kategorii produktów
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getCategory().equals(food)) {
                sum += all.get(i).getPrice();
            }
        }
        return sum;
    }


    @RequestMapping("/add")  //metoda dodająca produkt do listy
    public String add(@RequestParam String nazwa, @RequestParam String cena, @RequestParam Category kategoria) {
        if (nazwa.isEmpty() || cena.isEmpty()) {
            return "redirect:err.html";
        } else {
            Product product = new Product(nazwa, Double.parseDouble(cena), kategoria);
            productRepository.getAll().add(product);
            return "redirect:success.html";
        }
    }
}

