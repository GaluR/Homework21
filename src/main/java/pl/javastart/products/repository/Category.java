package pl.javastart.products.repository;

import java.util.List;

public enum Category {
    FOOD("Artykuły spożywcze"),
    ITEMS("Artykuły gospodarstwa domowego"),
    OTHERS("Inne");

    private String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
