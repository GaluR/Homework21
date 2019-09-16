package pl.javastart.products.controller;

import org.springframework.stereotype.Controller;
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
        if (!category.getName().equals(null) && !"".equals(category)) {
            switch (category) {
                case FOOD:
                    for (Product product : all) {
                        if (product.getCategory().equals(Category.FOOD)) {
                            result += product.toString() + "<br/>";
                        }
                    }
                    break;
                case OTHERS:
                    for (Product product : all) {
                        if (product.getCategory().equals(Category.OTHERS)) {
                            result += product.toString() + "<br/>";
                        }
                    }
                    break;
                case ITEMS:
                    for (Product product : all) {
                        if (product.getCategory().equals(Category.ITEMS)) {
                            result += product.toString() + "<br/>";
                        }
                    }
                    break;
                default:
                    for (Product product : all) {
                        result += product.toString() + "<br/>";
                    }
                    break;
            }
            return result;
        } else {
            for (Product product : all) {
                result += product.toString() + "<br/>";
            }
            return result;
        }
    }


    @RequestMapping("/products1")
    public double finallyPrice() {
        double price = productList.getProduct().getPrice();
        return price;

    }
}

