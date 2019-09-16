package pl.javastart.products.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.javastart.products.repository.Category;
import pl.javastart.products.repository.Product;
import pl.javastart.products.repository.ProductList;

import java.util.List;

@Controller
public class ProductController {

    private ProductList productList;

    public ProductController(ProductList productList) {
        this.productList = productList;
    }

    @ResponseBody
    @RequestMapping("/products")
    public String showAllProducts(@RequestParam Category category) {
        List<Product> all = ProductList.getAll();
        String result = "";
        double sum = 0;
        switch (category) {
            case FOOD:
                result = getProducts(all, result, Category.FOOD);
                sum = getSum(all, sum, Category.FOOD);
                break;
            case OTHERS:
                result = getProducts(all, result, Category.OTHERS);
                sum = getSum(all, sum, Category.OTHERS);
                break;
            case ITEMS:
                result = getProducts(all, result, Category.ITEMS);
                sum = getSum(all, sum, Category.ITEMS);
                break;
            default:
                for (Product product : all) {
                    result += product.toString() + "<br/>";
                }
                break;
        }
        return result + "<br/> Sumaryczna cena produktów z listy to: " + sum;
    }


    private String getProducts(List<Product> all, String result, Category items) {
        for (Product product : all) {
            if (product.getCategory().equals(items)) {
                result += product.toString() + "<br/>";
            }
        }
        return result;
    }

    private double getSum(List<Product> all, double sum, Category food) {
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getCategory().equals(food)) {
                sum += all.get(i).getPrice();
            }
        }
        sum *= 100;
        Math.round(sum);
        sum /= 100;

        return sum;
    }


    @RequestMapping("/add")
    public String add(@RequestParam String nazwa, @RequestParam String cena, @RequestParam Category kategoria) {
        if (nazwa.isEmpty() || cena.isEmpty()) {
            return "redirect:err.html";
        } else {
            Product product = new Product(nazwa, Double.parseDouble(cena), kategoria);
            ProductList.getAll().add(product);
            return "redirect:success.html";
        }
    }
}

