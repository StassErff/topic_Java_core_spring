package org.skypro.skyshop.model.product;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;

public abstract class Product implements Searchable {
    private final String name;

    public Product(String name) {
        this.name = name;
        if (name.isBlank()) {
            throw new IllegalArgumentException("Навание продукта не должно быть пустым или null");
        }
    }

    public String getName() {
        return name;
    }

    public abstract int getPrice();

    @Override
    public boolean equals(Object a) {
        if (this == a) return true;
        if (a == null || getClass() != a.getClass()) return false;
        Product product = (Product) a;
        return Objects.equals(name, product.name);
    }

    public abstract boolean isSpecial();


}
