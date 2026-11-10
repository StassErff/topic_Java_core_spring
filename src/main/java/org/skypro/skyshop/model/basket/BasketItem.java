package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;

public class BasketItem {
    private final Product product;
    private final int amountProduct;

    public BasketItem(Product product, int amountProduct) {
        this.product = product;
        this.amountProduct = amountProduct;
    }

    public Product getProduct() {
        return product;
    }

    public int getAmountProduct() {
        return amountProduct;
    }
}
