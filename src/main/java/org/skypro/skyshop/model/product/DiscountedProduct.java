package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private final int basePrice;
    private final int percentDiscount;
    private final UUID id;

    public DiscountedProduct(UUID id, String name, int basePrice, int percentDiscount) {
        super(name);
        this.id = id;
        this.basePrice = (int) (basePrice * (1 - percentDiscount / 100.0));
        this.percentDiscount = percentDiscount;

        if (basePrice <= 0) {
            throw new IllegalArgumentException("Цена должна быть больше нуля");
        }

        if (percentDiscount < 0 || percentDiscount > 100) {
            throw new IllegalArgumentException("Процент должен быть числом в диапазоне от 0 до 100 включительно");
        }
    }

    @Override
    public int getPrice() {
        return (int) (basePrice * (1 - percentDiscount / 100.0));
    }

    public int getPercentDiscount() {
        return percentDiscount;
    }

    @Override
    public String toString() {
        return getName() + ": " + getPrice() + " (" + getPercentDiscount() + "%)";
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
