package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {
    private static final int FIX_PRICE = 50;
    private final UUID id;

    public FixPriceProduct(UUID id, String name) {
        super(name);
        this.id = id;
    }

    @Override
    public int getPrice() {
        return FIX_PRICE;
    }

    @Override
    public String toString() {
        return getName() + ": Фиксированная цена " + getPrice();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String getSearchTerm() {
        return getName();
    }

    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    public UUID getId() {
        return id;
    }
}
